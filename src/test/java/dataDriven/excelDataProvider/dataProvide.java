package dataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvide {
	
	DataFormatter formatter = new DataFormatter();
	
	@Test(dataProvider="driveTest")
	public void testCaseData(String greeting,String communication,String id)
	{
		System.out.println(greeting+communication+id);
	}
	
	//multiple sets of data to our tests
	//array
	//5 sets of data as 5 arrays from datat provider to your test
	//then your test will run 5 times separate sets of data(arrays)
	@DataProvider(name="driveTest")
	public Object[] [] getData() throws IOException
	{
	
		FileInputStream fis = new FileInputStream("C:\\Users\\R2D2\\Desktop\\lol\\know.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
	
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowCount =sheet.getPhysicalNumberOfRows();
		XSSFRow row= sheet.getRow(0);
		int colcount =row.getLastCellNum();
		Object data [] [] = new Object[rowCount-1][colcount];
		for(int i=0; i<rowCount-1 ; i++)
		{
			row =sheet.getRow(i+1);
			for(int j=0; j<colcount; j++)
			{
				XSSFCell cell =row.getCell(j);
				
				data[i][j] = formatter.formatCellValue(cell);
			}
		}
		return data;
		
		
		
		
	}

}
