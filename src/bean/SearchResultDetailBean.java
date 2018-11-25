package bean;

import java.io.File;

public class SearchResultDetailBean {

	private File file;
	
	private String sheetName;
	
	private String colNum;
	
	private String rowNum;
	
	private String contentText;

	private String jamakNum;
	
	private String track;
	
	private String startTime;
	
	private String contentText2;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getColNum() {
		return colNum;
	}

	public void setColNum(String colNum) {
		this.colNum = colNum;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getJamakNum() {
		return jamakNum;
	}

	public void setJamakNum(String jamakNum) {
		this.jamakNum = jamakNum;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getContentText2() {
		return contentText2;
	}

	public void setContentText2(String contentText2) {
		this.contentText2 = contentText2;
	}
}
