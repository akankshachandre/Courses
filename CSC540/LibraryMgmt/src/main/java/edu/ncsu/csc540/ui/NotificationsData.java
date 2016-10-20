package edu.ncsu.csc540.ui;

import java.util.List;

import edu.ncsu.csc540.model.Notifications;

public class NotificationsData {
	public static Object[][] getNotifData(List<Notifications> notifs){
		int n = notifs.size();
		Object notifsData[][] = new Object[n][4];
		if(!notifs.isEmpty()){
			for(int i = 0; i < n; i++) {
				notifsData[i][0] = ((Notifications)notifs.get(i)).getResource_id();
				notifsData[i][1] = ((Notifications)notifs.get(i)).getPatron_unityid();
				notifsData[i][2] = ((Notifications)notifs.get(i)).getMessage();
				notifsData[i][3] = ((Notifications)notifs.get(i)).getNotification_date();
			}
		}
		return notifsData;
	}
}
