package edu.ncsu.csc540.ui;

import java.util.List;

import edu.ncsu.csc540.model.RentalHistory;;

public class CheckedOutResourcesData {
	
	public static Object[][] getCCData(List<RentalHistory> CCResources){
		int n = CCResources.size();
		Object CCResourcesData[][] = new Object[n][3];
		if(!CCResources.isEmpty()){
			for(int i = 0; i < n; i++) {
				CCResourcesData[i][0] = ((RentalHistory)CCResources.get(i)).getResource_id();
				CCResourcesData[i][1] = ((RentalHistory)CCResources.get(i)).getChecked_out();
				CCResourcesData[i][2] = ((RentalHistory)CCResources.get(i)).getDue_date();
			}
		}
		return CCResourcesData;
	}
}
