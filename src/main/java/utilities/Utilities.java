package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.w2a.TestBase.Base;

public class Utilities extends Base 
{
	public Utilities() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String ScreenShotPath;
	
	public static String ScreenShotName;
	
	public static void captureScreenshot() throws IOException
	{
	File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	Date date = new Date();
	ScreenShotName = date.toString().replace(":", "_").replace(" ", "_")+".jpg";
	FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+ScreenShotName));
	}
	
/*Reading data row and column wise based on the n number of parameters passed in the method
	@DataProvider(name = "dp")
	public Object[][] getData(Method m)
	{
		String sheet = m.getName();
		int rows = excel.getRowCount(sheet);
		int cols = excel.getColumnCount(sheet);
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int row=2; row<=rows; row++)
		{
			for(int col=0; col<cols; col++)
			{
				data[row-2][col] = excel.getCellData(sheet, col, row);
			}
		}
		return data;
	}
*/	
	
	//Hashtable - Reading data based on the parameters exists in Object repository and parameters using Hashtable
	@DataProvider(name = "dp")
	public Object[][] getData(Method m)
	{
		String sheet = m.getName();
		int rows = excel.getRowCount(sheet);
		int cols = excel.getColumnCount(sheet);
		
		Object[][] data = new Object[rows-1][1]; // instead of cols, use just called col
		
		Hashtable<String,String> table = null;
		
		for(int row=2; row<=rows; row++)
		{
			table = new Hashtable<String,String>();
			
			for(int col=0; col<cols; col++)
			{
			table.put(excel.getCellData(sheet, col, 1), excel.getCellData(sheet, col, row));
			data[row-2][0] = table;
			}
		}
		return data;
	}
	
	public static boolean isTestRunnable(String testname, ExcelData excel)
	{
		String sheet_name = "Test_Suite";
		int rows = excel.getRowCount(sheet_name);
		for(int row=2; row<=rows; row++)
		{
			String TestCase = excel.getCellData(sheet_name, "TCID", row);
			if(TestCase.equalsIgnoreCase(testname))
			{
				String Run_Mode = excel.getCellData(sheet_name, "Run-Mode", row);
				if(Run_Mode.equalsIgnoreCase("Y"))
				{
					return true;
				}
				else
				{
					return false;
				}
			
			}
		}
		return false;
	}
}
