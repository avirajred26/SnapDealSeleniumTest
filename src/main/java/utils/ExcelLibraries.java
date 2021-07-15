package utils;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibraries {
	


	static XSSFWorkbook workBook;
	static String reporter;
	public static int iActiveRow=1;
	static int rowCount;
	static int cellCount;
	static int iSetRow=1;
	static Sheet currentSheet;



	static XSSFCell cell;
	public static String testCaseName,testData ,strTestCaseName,strColName;
	
	
	//Setting Excel Output 
	public static void getTestName(String testCase) {
		testCaseName = testCase;
		
	}
	
	public static void setExcelOutput(String ColumnName,String Output) throws Throwable {
		
		workBook = new XSSFWorkbook();
		try{
			FileInputStream fin=new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/"
					+ "config/dataFile.xlsx");
			workBook=(XSSFWorkbook) WorkbookFactory.create(fin);
		}
		catch(Exception e){
			System.out.println(e);
		}
			
		currentSheet = workBook.getSheet("Sheet2");
		
		try {
			rowCount = currentSheet.getRow(0).getLastCellNum();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	      for(int i =1;i<=rowCount;i++) {
	    	
	    	 
	    	 if(currentSheet.getRow(0).getCell(i)!=null) {
	    		 
	    		 if(currentSheet.getRow(0).getCell(i).toString().equalsIgnoreCase(testCaseName)) {
	    			 
	 	    		
	 	    		
	 	    		if(currentSheet.getRow(iActiveRow)==null) {
	 	    			currentSheet.createRow(iActiveRow);
	 	    		}
	 	    		
	 	    		if(currentSheet.getRow(iActiveRow).getCell(i-1)==null) {
	 	    			currentSheet.getRow(iActiveRow).createCell(i-1);
	 	    		}
	 	    		
	 	    		if(currentSheet.getRow(iActiveRow).getCell(i)==null) {
	 	    			currentSheet.getRow(iActiveRow).createCell(i);
	 	    		}
	 	    		
	    			try { 
	 	    		 currentSheet.getRow(iActiveRow).getCell(i-1).setCellValue(ColumnName);// Setting Column Name
	 	    		 
	 	    		  currentSheet.getRow(iActiveRow).getCell(i).setCellValue(Output); // Setting Output 
	    			}
	    			catch(Exception e) {
	    				e.printStackTrace();
	    			}
	 	    		  break;
	 	    	  }
	    	 }else {
	    		 i=i+1;// Null
	    	 }
	    	  
	      }
	      
	      iActiveRow=iActiveRow+1;
		FileOutputStream fout = new FileOutputStream(System.getProperty("user.dir")+ "/src/main/java/"
				+ "config/dataFile.xlsx");
		workBook.write(fout);
		fout.close();
	}
	
	
	//Getting Excel Output with same Test case Name & Different test case Name
	public static String getExcelOutput(String ColumnName) throws Throwable {
		
		workBook = new XSSFWorkbook();
		try{
			FileInputStream fin=new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/"
					+ "config/dataFile.xlsx");
			workBook=(XSSFWorkbook) WorkbookFactory.create(fin);
		}
		catch(Exception e){
			System.out.println(e);
		}
			
	
		currentSheet = workBook.getSheet("Sheet2");
		
		try {
			cellCount = currentSheet.getRow(0).getLastCellNum();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

		if(ColumnName.contains(";")) {
		String[] strSplitCol = ColumnName.split(";");
		testCaseName=  strSplitCol[0];
	      ColumnName =  strSplitCol[1];
	      System.out.println(testCaseName+ "   -------    "+ColumnName);
		}

	      for(int i =0;i<=cellCount;i++) {
	    	  
				if(currentSheet.getRow(0).getCell(i)!=null) {
					  if(currentSheet.getRow(0).getCell(i).toString().equalsIgnoreCase(testCaseName)) {
			    		  rowCount = currentSheet.getLastRowNum();
			    		
				    		for(int j=1;j<=rowCount;j++) {
				    			String testValue = currentSheet.getRow(j).getCell(i-1).toString();
				    			if(testValue.equalsIgnoreCase(ColumnName)) {
				    				testData=currentSheet.getRow(j).getCell(i).toString();
				    				break;
				    			}
				    		}
				    		break;
			    	  } 
				}else {
					i=i+1;
				}

	      }
	     iSetRow = iSetRow+1;
		FileOutputStream fout = new FileOutputStream(System.getProperty("user.dir")+ "/src/main/java/"
				+ "config/dataFile.xlsx");
		workBook.write(fout);
		fout.close();
		testCaseName = reporter;
		return testData;
	}
	
	
	public static String getTestColValue(String ColumnName) throws Throwable {
		
		workBook = new XSSFWorkbook();
		try{
			FileInputStream fin=new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/"
					+ "config/dataFile.xlsx");
			workBook=(XSSFWorkbook) WorkbookFactory.create(fin);
		}
		catch(Exception e){
			System.out.println(e);
		}
			
	
		currentSheet = workBook.getSheet("Sheet1");
		
		try {
			cellCount = currentSheet.getRow(0).getLastCellNum();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	      for(int i =0;i<=cellCount;i++) {
	    	  
	    	 
	    	  if(currentSheet.getRow(0).getCell(i).toString().equalsIgnoreCase(ColumnName)) {
	    		  rowCount = currentSheet.getLastRowNum();
	    		
		    		for(int j=1;j<=rowCount;j++) {
		    			String testValue = currentSheet.getRow(j).getCell(0).toString();
		    			if(testValue.equalsIgnoreCase(testCaseName)) {
		    				testData=currentSheet.getRow(j).getCell(i).toString();
		    				break;
		    			}
		    		}
		    		break;
	    	  } 
	      }
	 
		FileOutputStream fout = new FileOutputStream(System.getProperty("user.dir")+ "/src/main/java/"
				+ "config/dataFile.xlsx");
		workBook.write(fout);
		fout.close();
		return testData;
	}
	

	
	public static void resetCount() {
		iActiveRow=1;
	}
	
	
}
