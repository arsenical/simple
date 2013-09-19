package com.globallogic.simple.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class FileSystemUtils {
	
	/**
	 * Adds path of the project directory with a slash ('/') on the end.
	 * 
	 * @param relativePath
	 *            - path from the project directory, like 'data' or 'reports';
	 * 
	 * @return absolute path;
	 */
	public static String addAbsolutePath(String relativePath) {
		String result = relativePath;
		try {
			result = new File(".").getCanonicalPath() + File.separator + relativePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Fetch the entire contents of a text file, and return it in a String. This style of implementation does not throw
	 * Exceptions to the caller.
	 * 
	 * @param file
	 *            is a file which already exists and can be read.
	 */
	public static String getContents(File file) {
		String content = "";

		try {
			BufferedReader input = new BufferedReader(new FileReader(file));
			try {
				content = IOUtils.toString(input);
			} finally {
				input.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}
}
