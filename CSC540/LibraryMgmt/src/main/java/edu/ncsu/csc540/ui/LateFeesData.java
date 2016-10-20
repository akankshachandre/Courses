package edu.ncsu.csc540.ui;

import java.util.List;

import edu.ncsu.csc540.model.LateFees;

public class LateFeesData {
	
	public static Object[][]  getLateFeesData(List<LateFees> lateFeesList){
		int n = lateFeesList.size();
		LateFees lateFees = null;
		Object[][] lateFeesData = new Object[n][2];
		for(int i = 0;i<n;i++){
			lateFees = lateFeesList.get(i);
			lateFeesData[i][0] = lateFees.getFees();
			lateFeesData[i][1] = lateFees.getResource_Id();
			
			
		}
		
		return lateFeesData;
		
	}

}
