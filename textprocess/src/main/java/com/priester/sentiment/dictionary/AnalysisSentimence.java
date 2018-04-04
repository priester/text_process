package com.priester.sentiment.dictionary;

import java.util.ArrayList;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

public class AnalysisSentimence {
	public static List<String> getPositionWords(List<String> content) {
		List<String> postionwords = SentimentWords.getPositiveWords();
		List<String> words = new ArrayList<String>();
		for (String postionword : postionwords) {
			if (content.contains(postionword)) {
				words.add(postionword);
			}
		}
		return words;
	}

	public static List<String> getNegativeWords(List<String> content) {
		List<String> negativewords = SentimentWords.getNegativeWords();
		List<String> words = new ArrayList<String>();
		for (String negativeword : negativewords) {
			if (content.contains(negativeword)) {
				words.add(negativeword);
			}
		}
		return words;
	}

	public static void main(String[] args) throws Exception {

//		List<String> titles = JdbcProcess.readerTitle();
//		for (String title : titles) {
//			List<Term> termList = HanLP.segment(title);
////			System.out.println(termList);
//			List<String> words = new ArrayList<String>();
//			// List<String> words = termList.stream().map(term ->
//			// term.word).collect(Collectors.toList());
//			for (Term t : termList) {
//				words.add(t.word);
//			}
//			System.out.print(title);
//			System.out.print("--N:" +getNegativeWords(words));
//			System.out.print("--P:"+ getPositionWords(words));
//			System.out.println();
//
//		}
		
		List<Term> termList = HanLP.segment("佳句人尽其材友邻不合格");
		System.out.println(termList);
	}
}
