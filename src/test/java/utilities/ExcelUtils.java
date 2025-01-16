package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;   
	String path;
	

	public ExcelUtils(String path) {
		// TODO Auto-generated constructor stub
		this.path=path;
	}

	public int getRowCount(String sheetName) throws IOException 
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;		
	}
	
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try{
		data = formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}

	public void setCellData(String sheetName, String header, String data[]) throws IOException {
	    File xlfile = new File(path);
	    if (!xlfile.exists()) {
	        workbook = new XSSFWorkbook();
	        fo = new FileOutputStream(path);
	        workbook.write(fo);
	        fo.close();
	    }

	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);

	    if (workbook.getSheetIndex(sheetName) == -1)
	        workbook.createSheet(sheetName);
	    sheet = workbook.getSheet(sheetName);

	    // Find the column index for the header
	    Row headerRow = sheet.getRow(0);
	    int headerColumnIndex = -1;
	    if (headerRow != null) {
	        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
	            if (headerRow.getCell(i).getStringCellValue().equals(header)) {
	                headerColumnIndex = i;
	                break;
	            }
	        }
	    }

	    // If header column doesn't exist, create a new column
	    if (headerColumnIndex == -1) {
	        headerColumnIndex = headerRow == null ? 0 : headerRow.getLastCellNum();
	        if (headerRow == null) 
	            headerRow = sheet.createRow(0);
	        headerRow.createCell(headerColumnIndex).setCellValue(header);
	    }

	    // Write data to the found or newly created column
	    for (int i = 1; i <= data.length; i++) {
	        Row row = sheet.getRow(i);
	        if (row == null)
	            row = sheet.createRow(i);
	        Cell cell = row.getCell(headerColumnIndex);
	        if (cell == null)
	            cell = row.createCell(headerColumnIndex);
	        cell.setCellValue(data[i - 1]);
	    }

	    fo = new FileOutputStream(path);
	    workbook.write(fo);
	    workbook.close();
	    fi.close();
	    fo.close();
	}

}
