package com.globallogic.simple.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.globallogic.simple.utils.FileSystemUtils;

public class DataFile {

	private File file;

	public DataFile(String dataFilePath) {
		this.file = new File(dataFilePath);
	}

	public List<HashMap<String, String>> getDataTable() {
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String content = FileSystemUtils.getContents(file);
		String[] lines = content.split(System.getProperty("line.separator"));
		String[] keys = lines[0].split(",");
		for (int i = 1; i < lines.length; i++) {
			String[] values = lines[i].split(",");
			HashMap<String, String> iteration = new HashMap<String, String>();
			for (int j = 0; j < keys.length; j++) {
				if (j == keys.length -1) {
					keys[j] = StringUtils.trim(keys[j]);
					values[j] = StringUtils.trim(values[j]);
				}
				iteration.put(keys[j], values[j]);
			}
			result.add(iteration);
		}
		return result;
	}

}
