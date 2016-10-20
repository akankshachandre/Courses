package edu.ncsu.csc540.ui;

import java.util.List;

import edu.ncsu.csc540.model.Cameras;

public class CamerasData {

	public static Object[][] getCameraData(List<Cameras> cameras)
	{
		int n = cameras.size();
		Object cameraData[][] = new Object[n][6];
		if(!cameras.isEmpty()){
			
			
				for(int i = 0;i<n;i++){
					cameraData[i][0] = ((Cameras)cameras.get(i)).getCameraId();
					cameraData[i][1] = ((Cameras)cameras.get(i)).getMake();
					cameraData[i][2] = ((Cameras)cameras.get(i)).getModel();
					cameraData[i][3] = ((Cameras)cameras.get(i)).getLensConfig();
					cameraData[i][4] = ((Cameras)cameras.get(i)).getAvailableMemory();
					cameraData[i][5] = ((Cameras)cameras.get(i)).getResourceId();
				}
			}
		
		
		
		return cameraData;
		
	}
}
