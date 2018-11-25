package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.swt.widgets.Shell;

import bean.SearchResultDetailBean;

import abstractClasses.Search;

public class SearchText extends Search{

	public SearchText (String extendArray[], String path, String keyword, Shell parentShell) throws FileNotFoundException {
		super(extendArray, path, keyword, parentShell);
	}

	// .txt 단일파일 검색
	@Override
	protected List<SearchResultDetailBean> searchOneFile(File textFile, String keyword) throws Exception {

		List<SearchResultDetailBean> resultList = new LinkedList<SearchResultDetailBean>();
		
	    // (?i) <- "찾을 문자열"에 대소문자 구분을 없애고
	    // .*   <- 문자열이 행의 어디에 있든지 찾을 수 있게
	    String findStr = "(?i).*" + keyword + ".*";
	    int lineNumber = 1;       // 행 번호

	    try {

	    	Reader reader = new InputStreamReader(new FileInputStream(textFile),"UTF-8");
			BufferedReader in = new BufferedReader(reader);
			String s;

			String oldJamakNum = "";
			String oldStartTime = "";

			Pattern p = Pattern.compile(findStr); 
			
			while ((s = in.readLine()) != null) {

				String[] textArray = s.split("\t");
	    	
				if (textArray != null && textArray.length == 4) {
					if (textArray[0] != null && ! "".equals(textArray[0]) && textArray[1] != null && ! "".equals(textArray[1])) {
						oldJamakNum = textArray[0];
						oldStartTime = textArray[1];
					}
				}
			
				Matcher m = p.matcher(s); 
			
				if (m.find()) {
					SearchResultDetailBean result = new SearchResultDetailBean();
					result.setFile(textFile);
					result.setRowNum(String.valueOf(lineNumber));
					result.setContentText(s);
					
					if (textArray != null && textArray.length >= 4) {
						
						if (textArray.length == 4) {
							result.setJamakNum(textArray[0]);
							result.setTrack("");
							result.setStartTime(textArray[1]);
							result.setContentText2(textArray[3]);
						} else if (textArray.length == 6) {
							result.setJamakNum(oldJamakNum);
							result.setTrack("");
							result.setStartTime(oldStartTime);
							result.setContentText2(textArray[5]);
						}
					}
				
					resultList.add(result);
				}


				lineNumber++; // 행 번호 증가
			}
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		    throw new Exception();
		} catch (PatternSyntaxException e) { // 정규식에 에러가 있다면
			e.printStackTrace();
		    throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		    
	    return resultList;
	}
}
