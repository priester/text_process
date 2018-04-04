package com.priester.sentiment.dictionary.filter;

import java.util.ArrayList;
import java.util.List;

import com.priester.sentiment.dictionary.SentimentWords;
import com.priester.utils.IOUtils;
import com.priester.utils.SolrClientUtil;

public class NegativeWordsFilter {
//	public static void main(String[] args) throws Exception {
//		List<String> words = SentimentWords.getNegativeWords();
//		List<String> result = new ArrayList<String>();
//		for (String word : words) {
//			String line = word.replace(",", "，") + "," + SolrClient.countNewsByWords(word);
//			result.add(line);
//			System.out.println(line);
//		}
//		IOUtils.write2txt(result,
//				"C:\\workspace\\sentencesfilter\\src\\main\\java\\com\\priester\\sentiment\\dictionary\\filter\\nagetive.csv");
//	}
	
	
	public static void main(String[] args) throws Exception {
		List<String> words = SentimentWords.getPositiveWords();
		List<String> result = new ArrayList<String>();
		for (String word : words) {
			String line = word.replace(",", "，") + "," + SolrClientUtil.countNewsByWords(word);
			result.add(line);
			System.out.println(line);
		}
		IOUtils.write2txt(result,
				"C:\\workspace\\sentencesfilter\\src\\main\\java\\com\\priester\\sentiment\\dictionary\\filter\\positive.csv");
	}
	
	
	
}
