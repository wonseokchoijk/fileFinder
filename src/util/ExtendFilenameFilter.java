package util;

import java.io.File;
import java.io.FilenameFilter;

public class ExtendFilenameFilter implements FilenameFilter {
	private String[] extendArray;

	public ExtendFilenameFilter(String[] extendArray){
		this.extendArray = extendArray;
	}

	@Override
	public boolean accept(File dir, String name) {

		for (String extend: this.extendArray) {
			if (extend != null && ! name.startsWith(".") && name.endsWith(extend)) {
				return true;
			}
		}
		return false;
	}
}
