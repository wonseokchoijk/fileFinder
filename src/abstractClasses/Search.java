package abstractClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.widgets.Shell;

import bean.SearchResultBean;
import bean.SearchResultDetailBean;

import util.Const;
import util.ExtendFilenameFilter;

public abstract class Search {

	private String[] extendArray;
	private String path;
	private String keyword;
//	private Shell parentShell;
//	private Display parentDisplay;

	public Search (String extendArray[], String path, String keyword, Shell parentShell) throws FileNotFoundException {
		this.extendArray = extendArray;
		this.path = path;
		this.keyword = keyword;
//		this.parentShell = parentShell;
//		this.parentDisplay = parentShell.getDisplay();
	}

	public SearchResultBean searchPluralFiles() throws FileNotFoundException {

		SearchResultBean result = new SearchResultBean();

		File file  = new File(this.path);
		if (! file.exists()) {
			throw new FileNotFoundException(Const.MESSAGE_FOLDER_NOT_FOUND);
		}
		File arr[] = file.listFiles(new ExtendFilenameFilter(this.extendArray));
		
		// 폴더 및에 있는 모든 파일을 읽어온다

		if (arr == null || arr.length == 0) {
			throw new FileNotFoundException(Const.MESSAGE_NO_EXTEND_FILE);
		}

		for(int i=0 ; i<arr.length ; i++ ){
			if (! arr[i].exists()) {
//				throw new FileNotFoundException(Const.MESSAGE_FILE_NOT_FOUND);
				result.getFailedFileNameList().add(arr[i].getName());
				continue;
			}

			result.addFileNamesForSearch(arr[i].getName());
			
			List<SearchResultDetailBean> list = new LinkedList<SearchResultDetailBean>();
			try {
				list = searchOneFile(arr[i], this.keyword);
				result.getList().addAll(list);
				if (! list.isEmpty()) {
					result.addFileNamesResultForSearch(arr[i].getName());
				}
			} catch (Exception e) {
				// 에러 발생시 실패 파일 리스트에 추가.
				result.getFailedFileNameList().add(arr[i].getName());
			}
		}

		return result;
	}
	
	protected List<SearchResultDetailBean> searchOneFile(File file, String keyword) throws Exception {
		return new ArrayList<SearchResultDetailBean>();
	}
}
