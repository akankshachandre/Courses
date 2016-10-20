package edu.ncsu.csc540.ui;

import java.util.List;

import edu.ncsu.csc540.model.Books;

public class BooksData {
	
	public static Object[][] getBookData(List<Books> books)
	{
		int n = books.size();
		Object booksData[][] = new Object[n][7];
		if(!books.isEmpty()){
			
			
				for(int i = 0;i<n;i++){
					booksData[i][0] = ((Books)books.get(i)).getIsbn();
					booksData[i][1] = ((Books)books.get(i)).getTitle();
					booksData[i][2] = ((Books)books.get(i)).getEdition();
					booksData[i][3] = ((Books)books.get(i)).getYearPublished();
					booksData[i][4] = ((Books)books.get(i)).getPublisher();
					booksData[i][5] = ((Books)books.get(i)).getResourceId();
					booksData[i][6] = ((Books)books.get(i)).getAuthor();
					
				}
			}
		
		
		
		return booksData;
		
	}

}
