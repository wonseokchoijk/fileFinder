package bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchResultBean {

	/** 검색대상 파일이름 리스트 */
	private List<String> fileNamesForSearch = new LinkedList<String>();

	/** 검색결과 키워드 발견 파일이름 리스트 */
	private List<String> fileNamesResultForSearch = new LinkedList<String>();
	
	/** 검색결과 리스트 */
	private List<SearchResultDetailBean> list = new LinkedList<SearchResultDetailBean>();

	/** 검색실패 파일이름 리스트 */
	private List<String> failedFileNameList = new ArrayList<String>();

	public List<String> getFailedFileNameList() {
		return failedFileNameList;
	}

	public void setFailedFileNameList(List<String> failedFileNameList) {
		this.failedFileNameList = failedFileNameList;
	}

	public List<String> getFileNamesForSearch() {
		return fileNamesForSearch;
	}

	public void addFileNamesForSearch(String fileName) {
		this.fileNamesForSearch.add(fileName);
	}

	public List<String> getFileNamesResultForSearch() {
		return fileNamesResultForSearch;
	}

	public void addFileNamesResultForSearch(String fileName) {
		this.fileNamesResultForSearch.add(fileName);
	}

	public List<SearchResultDetailBean> getList() {
		return list;
	}

	public void setList(List<SearchResultDetailBean> list) {
		this.list = list;
	}
}
