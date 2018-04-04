package com.priester.sentiment.dictionary;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class SentimentWords {

	private static List<String> positiveWords;
	private static List<String> negativeWords;

	public static List<String> getPositiveWords() {
		if (positiveWords == null) {
			try {
				initPositiveWords();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return positiveWords;
	}

	public static List<String> getNegativeWords() {
		if (negativeWords == null) {
			try {
				initNegativeWords();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return negativeWords;
	}

	public static void initPositiveWords() throws Exception {
		List<String> positiveWordsMap = new LinkedList<String>();
		InputStream is = SentimentWords.class.getResourceAsStream("/positiveWords.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
		String line;
		while ((line = br.readLine()) != null) {
			positiveWordsMap.add(line);
		}
		positiveWords = positiveWordsMap;
		is.close();
		br.close();
	}

	public static void initNegativeWords() throws Exception {
		List<String> negativeWordsMap = new LinkedList<String>();
		InputStream is = SentimentWords.class.getResourceAsStream("/negativeWords.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
		String line;
		while ((line = br.readLine()) != null) {
			negativeWordsMap.add(line);
		}
		negativeWords = negativeWordsMap;
		is.close();
		br.close();
	}

}
