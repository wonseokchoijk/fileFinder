package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.swt.widgets.Shell;

import abstractClasses.Search;
import bean.SearchResultDetailBean;

public class SearchExcel extends Search {
	public SearchExcel(String extendArray[], String path, String keyword,
			Shell parentShell) throws FileNotFoundException {
		super(extendArray, path, keyword, parentShell);
	}

	// .txt 단일파일 검색
	@Override
	protected List<SearchResultDetailBean> searchOneFile(File excelFile,
			String keyword) throws Exception {

		// (?i) <- "찾을 문자열"에 대소문자 구분을 없애고
		// .* <- 문자열이 행의 어디에 있든지 찾을 수 있게
		String findStr = "(?i).*" + keyword + ".*";
		Pattern p = Pattern.compile(findStr);
		
		List<SearchResultDetailBean> resultList = new LinkedList<SearchResultDetailBean>();

		try {
			
			Workbook book = null; 
					
			try {
				book = WorkbookFactory.create(new FileInputStream(excelFile));
			} catch (FileNotFoundException e) {
				// 열려있는 파일의 경우 에러는 무시
				e.printStackTrace();
				return resultList;
			}

			FormulaEvaluator evaluator = book.getCreationHelper().createFormulaEvaluator();
            DecimalFormat df = new DecimalFormat();

			for (int sheetNum = 0; sheetNum < book.getNumberOfSheets(); sheetNum++) {
	            Sheet sheet = book.getSheetAt(sheetNum);
	            int lastRowIndex = sheet.getPhysicalNumberOfRows();
	            
	            for(int r=0;r<lastRowIndex;r++){
	                Row row = sheet.getRow(r);
	                if(row == null) { // 물리적인 갯수와 화면에 보이는 갯수는 차이가 난다. 화면에 보이는 갯수로 맞추려고 함.
	                    lastRowIndex++;
	                    continue;
	                }
	                int lastColIndex = row.getLastCellNum();

	                for(int c = 0;c<lastColIndex;c++){
	                    Cell cell = row.getCell(c);
	                    if(cell != null) {
	                    	String data = "";
	                        switch(cell.getCellType()){
	                            case Cell.CELL_TYPE_BOOLEAN:
	                                data = String.valueOf(cell.getBooleanCellValue());
	                                break;
	                            case Cell.CELL_TYPE_NUMERIC:
	                                if(DateUtil.isCellDateFormatted(cell)){
	                                   // DateUtil.
	                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	                                    data = formatter.format(cell.getDateCellValue());
	
	                                }else{
	                                    data = String.valueOf(cell.getNumericCellValue());
	                                }
	                                break;
	                            case Cell.CELL_TYPE_ERROR:
	                            	break;
	                            case Cell.CELL_TYPE_BLANK:
	                            	break;
	                            case Cell.CELL_TYPE_STRING:
	                                data = cell.getStringCellValue();
	                                break;
	                            case Cell.CELL_TYPE_FORMULA:
	                                if (!cell.toString().equals("")) {
	                                    if (evaluator.evaluateFormulaCell(cell) == Cell.CELL_TYPE_NUMERIC) {
	                                        data = df.format(cell.getNumericCellValue());
	                                    } else if (evaluator.evaluateFormulaCell(cell) == Cell.CELL_TYPE_STRING) {
	                                        data = cell.getStringCellValue();
	                                    } else if (evaluator.evaluateFormulaCell(cell) == Cell.CELL_TYPE_BOOLEAN) {
	                                        data = String.valueOf(cell.getBooleanCellValue());
	                                    } else {
	                                    }
	                                }
	                                break;
	                            default:
	                                continue;
	                        }

							Matcher m = p.matcher(data);
		                    if (! "".equals(data) && m.find()) {
		                    	Cell jamakNumCell = row.getCell(1);
		                    	Cell trackCell = row.getCell(2);
		                    	Cell startTimeCell = row.getCell(3);

		                    	String jamakNum = "";
		                    	if (jamakNumCell != null) {
		                    		if (jamakNumCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
		                    			jamakNum = String.valueOf((long)jamakNumCell.getNumericCellValue());
		                    		} else if (jamakNumCell.getCellType() == Cell.CELL_TYPE_STRING) {
		                    			jamakNum = jamakNumCell.getStringCellValue();
		                    		}
		                    	}

		                    	SearchResultDetailBean result = this.makeResultBean(excelFile,
		            					sheet.getSheetName(),
		            					cell.getColumnIndex(),
		            					row.getRowNum()+1,
		            					data,
		            					jamakNum,
		            					trackCell != null && trackCell.getCellType() == Cell.CELL_TYPE_STRING ? trackCell.getStringCellValue() : "",
            							startTimeCell != null && startTimeCell.getCellType() == Cell.CELL_TYPE_STRING ? startTimeCell.getStringCellValue() : ""
		                    			);
		            			resultList.add(result);
		                    }
	                    }
	                }
	            }
			}
            
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

		return resultList;
	}

	private SearchResultDetailBean makeResultBean(File excelFile, String sheetName, int colNum, int rowNum, String contentText, String jamakNum, String track, String startTime) {
		SearchResultDetailBean result = new SearchResultDetailBean();
		result.setFile(excelFile);
		result.setSheetName(sheetName);
		result.setColNum(this.colNumToName(colNum));
		result.setRowNum(String.valueOf(rowNum));
		result.setContentText(contentText);
		
		
		result.setJamakNum(jamakNum);
		result.setTrack(track);
		result.setStartTime(startTime);
		result.setContentText2(contentText);

		return result;
	}

	// 열 번호를 받아서 엑셀 열 문자열 A, B, C...로 변환
	private String colNumToName(int colNum) {

		StringBuffer sb = new StringBuffer();
		int cycleNum = colNum / 26;
		int withinNum = colNum - (cycleNum * 26);
		if (cycleNum > 0)
			sb.append((char) ((cycleNum - 1) + 'a'));
		sb.append((char) (withinNum + 'a'));
		return (sb.toString().toUpperCase());
	}
}
