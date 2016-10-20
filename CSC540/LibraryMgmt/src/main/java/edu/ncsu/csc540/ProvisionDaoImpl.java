package edu.ncsu.csc540;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import edu.ncsu.csc540.model.Patrons;


public class ProvisionDaoImpl implements ProvisionDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void resetDatabase() {
		String sql = "BEGIN\n"
				+ "FOR c IN (SELECT table_name FROM user_tables) LOOP\n"
				+ "EXECUTE IMMEDIATE ('DROP TABLE \"' || c.table_name || '\" CASCADE CONSTRAINTS');\n"
				+ "END LOOP;\n" + "END;\n";
		Connection conn = null;
		ResultSet executeQuery;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			executeQuery = ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void checkCourses(Patrons patron) {
		// TODO Auto-generated method stub
		
	}
}
