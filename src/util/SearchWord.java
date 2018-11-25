package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.eclipse.swt.widgets.Shell;

import bean.SearchResultDetailBean;

import abstractClasses.Search;

public class SearchWord extends Search {
	public SearchWord(String extendArray[], String path, String keyword,
			Shell parentShell) throws FileNotFoundException {
		super(extendArray, path, keyword, parentShell);
	}

	// .txt 단일파일 검색
		@Override
		protected List<SearchResultDetailBean> searchOneFile(File wordFile,
				String keyword) throws Exception {
			// (?i) <- "찾을 문자열"에 대소문자 구분을 없애고
			// .* <- 문자열이 행의 어디에 있든지 찾을 수 있게
			String findStr = "(?i).*" + keyword + ".*";

			List<SearchResultDetailBean> resultList = new LinkedList<SearchResultDetailBean>();
		
			try {

				String resultString = "";
				String[] textArray = null;

				if(wordFile.getName().endsWith(".doc") || wordFile.getName().endsWith(".DOC")) {
					FileInputStream fs = new FileInputStream(wordFile);

					fs = new FileInputStream(wordFile);
					HWPFDocument doc = null;

					try {
						doc = new HWPFDocument(fs);
					} catch (IOException e) {
						// 열려있는 파일의 경우 에러는 무시
						e.printStackTrace();
						return resultList;
					} 
//					catch (IllegalArgumentException e) {
//						if ("The document is really a RTF file".equals(e.getMessage())) {
//							
//							try {
//							//   rtf 파일의 경우
//							//   FileInputStream is = new FileInputStream("a.rtf");
//							RTFEditorKit kit = new RTFEditorKit();
//							Document rtfDoc = kit.createDefaultDocument();
//							kit.read(fs, rtfDoc, 0);
//
//							String plainText = rtfDoc.getText(0, rtfDoc.getLength());
//							System.out.println("text:" + new String(plainText.getBytes("8859_1"),"KSC5601"));
//							} catch (Exception e1) {
//							// TODO: handle exception
//								e1.printStackTrace();
//							}
//							
//						}
//						
//					}
				

					resultString = doc.getText().toString();
					textArray = resultString.split("\r");
				} else if(wordFile.getName().endsWith(".docx") || wordFile.getName().endsWith(".DOCX")){

//					FileInputStream fs = new FileInputStream(wordFile);
//		            OPCPackage d = OPCPackage.open(fs);
					InputStream in = new FileInputStream(wordFile);  
					
					POITextExtractor textExtractor = null;
					try {
						textExtractor = ExtractorFactory.createExtractor(in);
					} catch (IllegalArgumentException e) {
						// 열려있는 파일의 경우 에러는 무시
						e.printStackTrace();
						return resultList;
					}

					XWPFWordExtractor extractor = (XWPFWordExtractor) textExtractor;
//		            XWPFDocument d = new XWPFDocument(in);

//	                XWPFWordExtractor xw = new XWPFWordExtractor(d);

					resultString = extractor.getText();
					textArray = resultString.split("\n");
	            }

				if (textArray != null) {
					Pattern p = Pattern.compile(findStr); 

					for (int i = 0; i < textArray.length; i++) {
						Matcher m = p.matcher(textArray[i]);

						if (m.find()) {
							SearchResultDetailBean result = new SearchResultDetailBean();
							result.setFile(wordFile);
//							result.setRowNum(String.valueOf(i+1));
							result.setContentText(textArray[i]);
							resultList.add(result);
						}
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new Exception();
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}

			return resultList;
		}
}
