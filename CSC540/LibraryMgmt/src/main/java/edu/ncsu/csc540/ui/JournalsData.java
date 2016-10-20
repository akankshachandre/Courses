package edu.ncsu.csc540.ui;

import java.util.List;

import edu.ncsu.csc540.model.Journals;
import edu.ncsu.csc540.model.RentalHistory;

public class JournalsData {
	public static Object[][] getJournalData(List<Journals> journals)
	{
		int n = journals.size();
		Object jounralsData[][] = new Object[n][6];
		if(!journals.isEmpty()){
			for(int i = 0;i<n;i++) {

				
					
					jounralsData[i][0] = ((Journals)journals.get(i)).getIssn();
					jounralsData[i][1] = ((Journals)journals.get(i)).getResource_id();
					jounralsData[i][2] = ((Journals)journals.get(i)).getTitle();
					jounralsData[i][3] = ((Journals)journals.get(i)).getYearPublished();
					jounralsData[i][4] = ((Journals)journals.get(i)).getAuthor();
					jounralsData[i][5] = ((Journals)journals.get(i)).getHostLibrary();


					
				}
			}
		return jounralsData;
		
	}
}
