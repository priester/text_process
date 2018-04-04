package com.priester.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

	public static List<String> read2List(String path) throws Exception {
		String line = null;
		List<String> lines = new ArrayList<String>();
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
		br.close();
		fis.close();
		return lines;
	}

	public static boolean write2txt(List<String> args, String path) {
		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for (String line : args) {
				bw.write(line + "\n");
			}
			return true;
		} catch (Exception e) {

		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
	}
}
