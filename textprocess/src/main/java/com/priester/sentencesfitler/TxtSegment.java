package com.priester.sentencesfitler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.priester.utils.IOUtils;

public class TxtSegment {
	public static void main(String[] args) throws Exception {
		List<String> result = new ArrayList<String>();
		List<String> lines =  IOUtils.read2List("D:/chapter/title");
		for(String line : lines) {
			result.add(HanlpSegment.segment(new HashSet<String>(), line).toString());
		}
		IOUtils.write2txt(result,"D:/chapter/title2");
	}
}
