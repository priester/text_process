package com.priester.news.semanticanalysis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.dictionary.CustomDictionary;

public class ParseSemantic {

	public static HashMap<Integer, Node> parse(String text, Set<String> organNames) {
		for (String organName : organNames) {
			CustomDictionary.add(organName, "nz 1000");
		}

		CoNLLSentence sentence = HanLP.parseDependency(text);

		HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

		for (int i = 0; i < sentence.word.length + 1; i++) {
			nodes.put(i, new Node());
		}

		for (int i = 1; i < sentence.word.length + 1; i++) {
			CoNLLWord word = sentence.word[i-1];
			Node node = nodes.get(i);
			node.setWord(word);

			nodes.get(word.HEAD.ID).getNextwords().add(node);
			
			System.out.println(word.NAME + " "+ word.HEAD.ID +"  "+nodes.get(word.HEAD.ID).getNextwords().size());

		}

		return nodes;
	}

	public static void main(String[] args) {
		String text = "小明吃了个苹果。";

		Set<String> organNames = new HashSet<String>();
		organNames.add("乐视");
		organNames.add("南京银行");
		organNames.add("中泰创展控股有限公司");
		System.out.println(parse(text, organNames).get(2).getNextwords().size());
	}

}
