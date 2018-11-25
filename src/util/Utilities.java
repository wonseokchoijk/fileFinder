package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class Utilities {
	public static void showErrorMsg(Shell shell, String exceptionMessage) {
		
		MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
		box.setText(Const.MESSAGE_ERROR_OCCURS_TITLE);
		box.setMessage(Const.MESSAGE_ERROR_OCCURS_MSG + exceptionMessage);
		box.open();
	}

	public static void showInfoMsg(Shell shell, String message) {
		
		MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
		box.setText(Const.MESSAGE_INFO_TITLE);
		box.setMessage(message);
		box.open();
	}
	
	// 전회 검색 했던 폴더 패스를 기억해놓음. 다음에 다시 프로그램 실행했을 때 이게 존재하면 전회 검색했던 폴더를 초기 표시함
	public static void outputSaveFile(String path) {
		 try {

		      BufferedWriter out = new BufferedWriter
		    		    (new OutputStreamWriter(new FileOutputStream(Const.SAVE_FILE_PATH),"UTF-8"));

		      out.write(path); out.newLine();

		      out.close();

		    } catch (IOException e) {
		    	System.out.println("no conf directory exists.");
		    }
	}
	
	public static String inputSaveFile() {
		File file = new File(Const.SAVE_FILE_PATH);
		if (file.exists()) {
			// 파일을 읽어들여 File Input 스트림 객체 생성
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				
				// File Input 스트림 객체를 이용해 Input 스트림 객체를 생서하는데 인코딩을 UTF-8로 지정
				InputStreamReader isr;
				try {
					isr = new InputStreamReader(fis, "UTF-8");
					
					// Input 스트림 객체를 이용하여 버퍼를 생성
					BufferedReader br = new BufferedReader(isr);
		         
					String temp = "";
		            // 버퍼를 한줄한줄 읽어들여 내용 추출
		            try {
						while( (temp = br.readLine()) != null) {
						    File directory = new File(temp);
						    if (directory.exists()) {
						    	return temp;
						    } else {
						    	return "";
						    }
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
            return "";
		} else {
			return "";
		}
	}
}
