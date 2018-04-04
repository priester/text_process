package com.priester.segment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

public class HanlpSegment {

	public static List<Term> segment(Set<String> organNames, String text) {
		for (String organName : organNames) {
			CustomDictionary.add(organName, "nz 0");
		}
		
		List<Term> terms = HanLP.segment(text);
		
		for (String organName : organNames) {
			CustomDictionary.remove(organName);
		}

		return terms;
	}
	
	public static String segmentOutNuture(Set<String> organNames, String text) {
//		for (String organName : organNames) {
//			CustomDictionary.add(organName, "nz 0");
//		}
		
		String segment = "";
		List<Term> terms = HanLP.segment(text);
		
		for(Term t : terms){
			segment =segment + t.word + ' ';
		}
		segment = segment.substring(0, segment.length()-1);
		
//		for (String organName : organNames) {
//			CustomDictionary.remove(organName);
//		}
		
	

		return segment;
	}
	

	public static void main(String[] args) {
		String text = "首次公开发行股票并在创业板上市招股意向书";  
		
		Set<String> organNames = new HashSet<String>();
		organNames.add("鹏元征信有限公司");
		
		System.out.println(segmentOutNuture(organNames,text));
	}
}
