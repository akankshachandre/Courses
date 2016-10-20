CREATE OR REPLACE PROCEDURE Soft_delete_Queue_Spot_At_Noon AS
BEGIN

-- Soft delete from the camera queue
UPDATE CAMERA_QUEUE
    SET DELETED = 1
WHERE CAMERA_QUEUE_GUID IN (SELECT CAMERA_QUEUE_GUID
  FROM (SELECT *
    FROM CAMERA_QUEUE b
    WHERE b.PICKUP_DATE >= trunc(sysdate) AND
         b.PICKUP_DATE < trunc(sysdate) + 1
    ORDER BY b.REQUEST_DATE)
  WHERE rownum = 1);

END Soft_delete_Queue_Spot_At_Noon;
/

BEGIN
DBMS_SCHEDULER.CREATE_JOB (
   job_name             => 'SoftdeleteQueueSpotAtNoon',
   job_type             => 'STORED_PROCEDURE',
   job_action           => 'Soft_delete_Queue_Spot_At_Noon',
   start_date           => '15-NOV-03 10.00.00AM',
   repeat_interval      => 'FREQ=WEEKLY; INTERVAL=1; BYDAY=FRI;',
   end_date             => '15-DEC-25 12.00.00PM',
   enabled              =>  TRUE,
   comments             => 'UpdateNotifications');
END;
/


CREATE OR REPLACE PROCEDURE update_notifications AS
BEGIN
-- Log Notifications for Resources due in 3 days
INSERT INTO NOTIFICATIONS (RESOURCE_ID, PATRON_UNITYID, MESSAGE, NOTIFICATION_DATE)
SELECT rh.RESOURCE_ID, rh.UNITYID, 'This Resource is Due in 3 Days.', sysdate
FROM RENTAL_HISTORY rh
  INNER JOIN RESOURCES r
  ON r.RESOURCE_ID = rh.RESOURCE_ID
WHERE rh.DUE_DATE >= sysdate + 3
      AND rh.DUE_DATE < sysdate + 3 + 1 / 24
      AND rh.CHECKED_IN IS NULL
      AND r.TYPE <> 'Room';

-- Log Notifications for Resources due in 24 hours
INSERT INTO NOTIFICATIONS (RESOURCE_ID, PATRON_UNITYID, MESSAGE, NOTIFICATION_DATE)
SELECT rh.RESOURCE_ID, rh.UNITYID, 'This Resource is Due in 24 Hours.', sysdate
FROM RENTAL_HISTORY rh
  INNER JOIN RESOURCES r
  ON r.RESOURCE_ID = rh.RESOURCE_ID
WHERE rh.DUE_DATE >= sysdate + 1
      AND rh.DUE_DATE < sysdate + 1 + 1 / 24
      AND rh.CHECKED_IN IS NULL
      AND r.TYPE <> 'Room';

-- Log Notifications for Resources 30 days late
INSERT INTO NOTIFICATIONS (RESOURCE_ID, PATRON_UNITYID, MESSAGE, NOTIFICATION_DATE)
SELECT rh.RESOURCE_ID, rh.UNITYID, 'This Resource is 30 Days Late.', sysdate
FROM RENTAL_HISTORY rh
  INNER JOIN RESOURCES r
  ON r.RESOURCE_ID = rh.RESOURCE_ID
WHERE rh.DUE_DATE >= sysdate - 30
      AND rh.DUE_DATE < sysdate - 30 + 1 / 24
      AND rh.CHECKED_IN IS NULL
      AND r.TYPE <> 'Room';

-- Log Notifications for Resources 60 days late
INSERT INTO NOTIFICATIONS (RESOURCE_ID, PATRON_UNITYID, MESSAGE, NOTIFICATION_DATE)
SELECT rh.RESOURCE_ID, rh.UNITYID, 'This Resource is 60 Days Late.', sysdate
FROM RENTAL_HISTORY rh
  INNER JOIN RESOURCES r
  ON r.RESOURCE_ID = rh.RESOURCE_ID
WHERE rh.DUE_DATE >= sysdate - 60
      AND rh.DUE_DATE < sysdate - 60 + 1 / 24
      AND rh.CHECKED_IN IS NULL
      AND r.TYPE <> 'Room';

-- Log Notifications for Resources 90 days late
INSERT INTO NOTIFICATIONS (RESOURCE_ID, PATRON_UNITYID, MESSAGE, NOTIFICATION_DATE)
SELECT rh.RESOURCE_ID, rh.UNITYID, 'This Resource is 90 Days Late.', sysdate
FROM RENTAL_HISTORY rh
  INNER JOIN RESOURCES r
  ON r.RESOURCE_ID = rh.RESOURCE_ID
WHERE rh.DUE_DATE >= sysdate - 90
      AND rh.DUE_DATE < sysdate - 90 + 1 / 24
      AND rh.CHECKED_IN IS NULL
      AND r.TYPE <> 'Room';
      
-- Mark hold on accounts that are 90 days past due
UPDATE STUDENTS
SET HOLD = 1
WHERE STD_UNITYID IN (
  SELECT rh.UNITYID
  FROM RENTAL_HISTORY rh
    INNER JOIN RESOURCES r
    ON r.RESOURCE_ID = rh.RESOURCE_ID
  WHERE rh.DUE_DATE >= sysdate - 90
        AND rh.DUE_DATE < sysdate - 90 + 1 / 24
        AND rh.CHECKED_IN IS NULL
        AND r.TYPE <> 'Room'
);
      
END update_notifications;
/

BEGIN
DBMS_SCHEDULER.CREATE_JOB (
   job_name             => 'UpdateNotifications',
   job_type             => 'STORED_PROCEDURE',
   job_action           => 'Update_Notifications',
   start_date           => '15-NOV-03 08.00.00PM',
   repeat_interval      => 'FREQ=HOURLY',
   end_date             => '15-DEC-25 12.00.00PM',
   enabled              =>  TRUE,
   comments             => 'UpdateNotifications');
END;
/