package com.ie.Pulse.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
	
	static String  TYPE="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	
	public static List<String> getSheetNames(MultipartFile file)
	{
		try {
			XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());

			List<String> sheetNames = new ArrayList<String>();
			for (int i=0; i<wb.getNumberOfSheets(); i++) {
				
					sheetNames.add( wb.getSheetName(i) );
			}
			return sheetNames;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	

	
	

}
