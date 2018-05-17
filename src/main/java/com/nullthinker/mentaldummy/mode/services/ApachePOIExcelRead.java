package com.nullthinker.mentaldummy.mode.services;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ApachePOIExcelRead {
	public List<StringBuilder> reader(final String FILE_NAME) throws Exception {
		List<StringBuilder> result = new ArrayList<>();
		FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet datatypeSheet = (Sheet) workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();
		DataFormatter formatter = new DataFormatter();
		StringBuilder data = null;
		try {
			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				data = new StringBuilder();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					//getCellTypeEnum shown as deprecated for version 3.15
					//getCellTypeEnum ill be renamed to getCellType starting from version 4.0
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						data.append(currentCell.getStringCellValue()).append("-|-");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						data.append(formatter.formatCellValue(currentCell)).append("-|-"); //  currentCell.getNumericCellValue() + ",");
					}
					
				}//END OF WHILEsitus11
				result.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			workbook.close();
		}
		return result;
	}
}
