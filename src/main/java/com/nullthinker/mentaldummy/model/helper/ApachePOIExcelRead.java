package com.nullthinker.mentaldummy.model.helper;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOIExcelRead {
	public static StringBuilder reader(final String FILE_NAME) throws Exception {
		StringBuilder result = new StringBuilder();
		FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();
		DataFormatter formatter = new DataFormatter();
		try {
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					//getCellTypeEnum shown as deprecated for version 3.15
					//getCellTypeEnum ill be renamed to getCellType starting from version 4.0
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						result.append(currentCell.getStringCellValue()).append(",");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						
						result.append(formatter.formatCellValue(currentCell)).append(","); //  currentCell.getNumericCellValue() + ",");
					}
				}//END OF WHILEsitus11
				
				result.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			workbook.close();
		}
		return result;
	}
	public static void main(String[] args) throws Exception {
		System.out.println("start");
		String filename = "F:/Workspace/Tkart/FileUploder/WebContent/files/Test.xlsx";
		ApachePOIExcelRead.reader(filename);
	}
}
