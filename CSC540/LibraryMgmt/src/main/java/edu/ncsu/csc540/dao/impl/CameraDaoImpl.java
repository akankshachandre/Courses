package edu.ncsu.csc540.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.ncsu.csc540.dao.CameraDao;
import edu.ncsu.csc540.model.Cameras;
import edu.ncsu.csc540.model.Patrons;

@Repository("cameraDao")
public class CameraDaoImpl implements CameraDao {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Cameras> getAllCameras() {
		String sql = "SELECT * FROM Cameras";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Cameras> cams = (List<Cameras>) jdbcTemplate.query(sql,
				new Object[] {}, new BeanPropertyRowMapper(Cameras.class));

		return cams;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String requestCamera(int resourceId, Patrons patron,
			Date pickUpDate) {

		String sql = "INSERT INTO CAMERA_QUEUE (pickup_date, request_date, unityid, resource_id, camera_queue_guid, deleted) "
				+ "VALUES (?, current_date, ?, ?, sys_guid(), 0)";

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(pickUpDate);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		if(calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY)
			return "Pick up date is not a Friday";
		else if (cameraAlreadyRequested(resourceId, patron, pickUpDate)) 
			return "You have already requested the camera";			
		else {
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql, pickUpDate, patron.getUnityid(),
					resourceId);
			return "Request added";
		}
	}

	private boolean cameraAlreadyRequested(int resourceId, Patrons patron,
			Date pickUpDate) {
		String sql = "SELECT cq.unityid FROM CAMERA_QUEUE cq "
				+ "WHERE cq.RESOURCE_ID = ? AND cq.PICKUP_DATE >= trunc(?) "
				+ "AND cq.PICKUP_DATE < trunc(?) + 1 AND cq.UNITYID = ? AND deleted = 0 "
				+ "ORDER BY REQUEST_DATE";

		
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<String> strings = jdbcTemplate.queryForList(sql, new Object[] {
				(Integer)resourceId, pickUpDate, pickUpDate, patron.getUnityid() },
				String.class);
		if (strings != null && strings.size() != 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean isCameraAvailable(int resourceId) {
		String checkSQL = "SELECT c.RESOURCE_ID FROM RENTAL_HISTORY rh "
				+ "INNER JOIN CAMERAS c ON c.RESOURCE_ID = rh.RESOURCE_ID "
				+ "WHERE rh.CHECKED_IN IS NULL AND c.RESOURCE_ID = ?";

		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Integer> strings = jdbcTemplate.queryForList(checkSQL,
				new Object[] { (Integer)resourceId }, Integer.class);

		if (strings.size() != 0) {
			return false;
		}

		return true;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String checkoutCamera(int resourceId, Patrons patron) {
		String patronId = patron.getUnityid();
		Calendar calendar = Calendar.getInstance();

		if (!isCameraAvailable(resourceId))
			return "Camera not available";
		else if (!isPatronAtTopOfQueue(resourceId, patronId))
			return "You are not at the head of the queue to check out the camera";
		else if(!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
				&& calendar.get(Calendar.HOUR_OF_DAY) > 8
				&& calendar.get(Calendar.HOUR_OF_DAY) <= 12)){
			return  "Current time is not a valid camera check out time";
		}
		else {
			// Setup the return date to be the next Thurs at 6pm
			calendar.add(Calendar.DAY_OF_YEAR, 6);
			calendar.set(Calendar.HOUR_OF_DAY, 18);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Date dueDate = calendar.getTime();

			String sql = "INSERT INTO Rental_History (CHECKED_OUT, DUE_DATE, RESOURCE_ID, UNITYID) "
					+ "VALUES (sysdate, ?, ?, ?)";
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(sql, dueDate,resourceId,
					patronId);
			return "You have successfully checked out camera";
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean isPatronAtTopOfQueue(int resourceId, String patronId) {
		String sql = "SELECT cq.unityid FROM CAMERA_QUEUE cq "
				+ "WHERE cq.RESOURCE_ID = ? AND cq.PICKUP_DATE >= trunc(sysdate) "
				+ "AND cq.PICKUP_DATE < trunc(sysdate) + 1 AND deleted = 0 "
				+ "ORDER BY REQUEST_DATE";
		
		List<String> strings = jdbcTemplate.queryForList(sql,
				new Object[] { (Integer)resourceId }, String.class);
		if (strings.size() > 0 && strings.get(0).equals(patronId)) {
			return true;
		}
		return false;
	}

	/**
	 * @return Returns the patron's position in the queue or null if they are
	 *         not in the queue yet.
	 */
	@Override
	public Integer getRequestPosition(int resourceId, Patrons patron,
			Date pickUpDate) {
		String sql = "SELECT cq.unityid FROM CAMERA_QUEUE cq "
				+ "WHERE cq.RESOURCE_ID = ? AND cq.PICKUP_DATE >= trunc(?) "
				+ "AND cq.PICKUP_DATE < trunc(?) + 1 ORDER BY REQUEST_DATE";

		
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<String> strings = jdbcTemplate.queryForList(sql, new Object[] {
				(Integer)resourceId, pickUpDate, pickUpDate }, String.class);

		if (strings == null) {
			return null;
		}

		Integer count = 1;
		for (String string : strings) {
			if (string.equals(patron.getUnityid())) {
				return count;
			}
			count++;
		}

		return null;
	}

	@Override
	public int checkinCamera(int resource_id, Patrons p) {
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String todayDate = format.format(today);
		
		int checkin = 0;
		
		String sql = "UPDATE Rental_history set checked_in = TO_DATE(?,'yyyy-mm-dd hh24:mi:ss') where unityId = ? and resource_id = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		checkin = jdbcTemplate.update(sql, todayDate,p.getUnityid(),resource_id);
		
		return checkin;
	}
}
