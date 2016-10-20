package edu.ncsu.csc540.ui;

import java.util.List;

import edu.ncsu.csc540.model.Books;

public class EbookData {
	public static Object[][] getEbookData(List<Books> eBooks)
	{
		int n = eBooks.size();
		Object eBooksData[][] = new Object[n][7];
		if(!eBooks.isEmpty()){
			
			
				for(int i = 0;i<n;i++){
					eBooksData[i][0] = ((Books)eBooks.get(i)).getIsbn();
					eBooksData[i][1] = ((Books)eBooks.get(i)).getTitle();
					eBooksData[i][2] = ((Books)eBooks.get(i)).getEdition();
					eBooksData[i][3] = ((Books)eBooks.get(i)).getYearPublished();
					eBooksData[i][4] = ((Books)eBooks.get(i)).getPublisher();
					eBooksData[i][5] = ((Books)eBooks.get(i)).getResourceId();
					eBooksData[i][6] = ((Books)eBooks.get(i)).getAuthor();
				}
			}
		
		
		
		return eBooksData;
		
	}
}
