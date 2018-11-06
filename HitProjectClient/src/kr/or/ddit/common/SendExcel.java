package kr.or.ddit.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

import kr.or.ddit.corApply.CorDetailVO;

public class SendExcel {
	public void xlsxWiter(List<CorDetailVO> list) {
		
		// 워크북 생성
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 워크시트 생성
		HSSFSheet sheet = workbook.createSheet();
		// 행 생성
		HSSFRow row = sheet.createRow(0);
		// 쎌 생성
		HSSFCell cell;
		
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 7000);
		sheet.setColumnWidth(3, 10000);
		sheet.setColumnWidth(4, 10000);
		sheet.setColumnWidth(5, 10000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 10000);
		
		HSSFFont font= workbook.createFont();
	    font.setFontHeightInPoints((short)10);
	    font.setFontName("Arial");
	    font.setColor(IndexedColors.WHITE.getIndex());
	    font.setBold(true);
	    font.setItalic(false);
	    
		// 헤더 정보 구성
		cell = row.createCell(0);
		cell.setCellValue("이름");

		cell = row.createCell(1);
		cell.setCellValue("성별");

		cell = row.createCell(2);
		cell.setCellValue("전화번호");

		cell = row.createCell(6);
		cell.setCellValue("경력");
		
		cell = row.createCell(5);
		cell.setCellValue("희망");
		
		cell = row.createCell(4);
		cell.setCellValue("주소");
		
		cell = row.createCell(3);
		cell.setCellValue("메일");
		
		cell = row.createCell(7);
		cell.setCellValue("소개");

		CorDetailVO vo = null;
		
		String gender = "";
		// 리스트의 size 만큼 row를 생성
		for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {
			vo = list.get(rowIdx);

			row = sheet.createRow(rowIdx + 1);

			cell = row.createCell(0);
			cell.setCellValue(vo.getMem_name());
			if (vo.getJmem_regno().charAt(6) == '1' || vo.getJmem_regno().charAt(6) == '3') {
				gender = "남자";
			} else {
				gender = "여자";
			}
			cell = row.createCell(1);
			cell.setCellValue(gender);

			cell = row.createCell(2);
			cell.setCellValue(vo.getJmem_tel());

			cell = row.createCell(3);
			cell.setCellValue(vo.getMem_mail());
			
			cell = row.createCell(4);
			cell.setCellValue(vo.getJmem_addr());
			
			cell = row.createCell(5);
			cell.setCellValue(vo.getMy_hope());

			cell = row.createCell(6);
			cell.setCellValue(vo.getMy_career());
			
			cell = row.createCell(7);
			cell.setCellValue(vo.getMy_intro());

		}

		File path = new File("");
		
		// 입력된 내용 파일로 쓰기
		File file = new File(path.getAbsolutePath()  + "/Excel/구인기업리스트.xls");
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null)
					workbook.close();
				if (fos != null)
					fos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
