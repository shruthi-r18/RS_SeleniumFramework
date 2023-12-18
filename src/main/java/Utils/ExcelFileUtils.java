package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



//.xls excel file uses the standard binary format and was used in earlier versions of Excel.
//XLS files are readable by all Microsoft Excel versions

//.xlsx excel file Uses the updated XML format and has been the default format for new documents 
//since Excel 2007. XLSX files are only readable by Excel 2007 and later versions


//libraries to use- 
//apache poi API -xlsx
//poi - xls

public class ExcelFileUtils {

	String filepath_xlsx = "C:\\Users\\shrut\\OneDrive\\Desktop\\newxlsxfile.xlsx";
	String filepath_xls = "C:\\Users\\shrut\\OneDrive\\Desktop\\newfile.xls";
	

	public static void readExcelFile_xlsx(String filepath_xlsx) throws IOException {

		File f = new File(filepath_xlsx);

		FileInputStream fileInput = new FileInputStream(f);

		XSSFWorkbook wb = new XSSFWorkbook(fileInput);
		XSSFSheet s = wb.getSheet("StudentDetails");

		for (int i = 0; i <= s.getLastRowNum(); i++) {

			XSSFRow row = s.getRow(i); 
			for (int j = 0; j <= row.getLastCellNum(); j++) {

				System.out.print(row.getCell(j)+"   ");

			}
			System.out.println();
		}
		wb.close();

	}

	public static void writeExcelFile_xlsx(String filepath_xlsx) throws IOException {
		 File f = new File(filepath_xlsx);
		 String str[] = {"rahul","dasha","tamanvi","aarav","nikki"};
		 
		 XSSFWorkbook book = new XSSFWorkbook();
		 XSSFSheet sheet = book.createSheet("StudentDetails");
		 
		 for(int i =0;i<5;i++) {
		 XSSFRow row = sheet.createRow(i);
		   
		 row.createCell(i).setCellValue(str[i]);
		 }
		 
		 FileOutputStream fileoutput = new FileOutputStream(f);
		 book.write(fileoutput);
		 book.close();
	}

	
	
	public static void readExcelFile_xls(String filepath_xls) throws IOException {

		File f = new File(filepath_xls);

		FileInputStream fileInput = new FileInputStream(f);

		HSSFWorkbook wb = new HSSFWorkbook(fileInput);
		HSSFSheet s = wb.getSheet("StudentDetails");

		for (int i = 0; i <= s.getLastRowNum(); i++) {

			HSSFRow row = s.getRow(i); 
			for (int j = 0; j <= row.getLastCellNum(); j++) {

				System.out.print(row.getCell(j)+"   ");

			}
			System.out.println();
		}
		wb.close();

	}

	public static void writeExcelFile_xls(String filepath_xls) throws IOException {
		 File f = new File(filepath_xls);
		 String str[] = {"rahul","dasha","tamanvi","aarav","nikki"};
		 
		 HSSFWorkbook book = new HSSFWorkbook();
		 HSSFSheet sheet = book.createSheet("StudentDetails");
		 
		 for(int i =0;i<5;i++) {
		 HSSFRow row = sheet.createRow(i);
		   
		 row.createCell(i).setCellValue(str[i]);
		 }
		 
		 FileOutputStream fileoutput = new FileOutputStream(f);
		 book.write(fileoutput);
		 book.close();
	}

	
}
