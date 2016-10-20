package edu.ncsu.csc540.ui;

import java.util.List;

import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.ConferenceProceedings;

public class ConfProceedingData {
	
	public static Object[][] getConfProceedingData(List<ConferenceProceedings> confProceedings)
	{
		int n = confProceedings.size();
		Object confProceedingsData[][] = new Object[n][6];
		if(!confProceedings.isEmpty()){
			
			
				for(int i = 0;i<n;i++){
					confProceedingsData[i][0] = ((ConferenceProceedings)confProceedings.get(i)).getConfNum();
					confProceedingsData[i][1] = ((ConferenceProceedings)confProceedings.get(i)).getTitle();
					confProceedingsData[i][2] = ((ConferenceProceedings)confProceedings.get(i)).getConferenceName();
					confProceedingsData[i][3] = ((ConferenceProceedings)confProceedings.get(i)).getYearPublished();
					confProceedingsData[i][4] = ((ConferenceProceedings)confProceedings.get(i)).getResource_id();
					confProceedingsData[i][5] = ((ConferenceProceedings)confProceedings.get(i)).getAuthor();
					
					
				}
			}
		
		
		
		return confProceedingsData;
		
	}
}
