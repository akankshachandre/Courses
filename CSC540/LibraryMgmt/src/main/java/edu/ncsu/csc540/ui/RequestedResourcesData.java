package edu.ncsu.csc540.ui;

import java.util.List;

import edu.ncsu.csc540.model.RequestedResource;

public class RequestedResourcesData {
	public static Object[][] getRequestedResources(List<RequestedResource> requests){
		int n = requests.size();
		RequestedResource reqRes = new RequestedResource();
		
		Object reqResources[][] = new Object[n][2];
		for(int i = 0;i<n;i++){
			reqRes = requests.get(i);
			reqResources[i][0] = reqRes.getTitle();
			reqResources[i][0] = reqRes.getTitle();
			
		}
		
		return reqResources;
		
	}

}
