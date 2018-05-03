package com.priester.news.semanticanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
			CoNLLWord word = sentence.word[i - 1];
			Node node = nodes.get(i);
			node.setWord(word);
			node.setId(i);
			nodes.get(word.HEAD.ID).getNextwords().add(node);
			// System.out.println(word.NAME + " " + word.HEAD.ID + " " +
			// nodes.get(word.HEAD.ID).getNextwords().size());
		}

		// for (CoNLLWord word : words) {
		// Node node = new Node();
		// if (word.HEAD.ID == 0) {
		//
		// node.setWord(word);
		// node.setId(word.ID);
		// }

		// CoNLLWord[] words = sentence.word;
		// for (CoNLLWord word : words) {
		// Node node = new Node();
		// if (word.HEAD.ID == 0) {
		//
		// node.setWord(word);
		// node.setId(word.ID);
		// }
		//
		// for (CoNLLWord word2 : words) {
		// if (word2.HEAD.ID == word.ID) {
		// Node node2 = new Node();
		// node2.setWord(word);
		// node2.setId(word.ID);
		// node.addNextWords(node2);
		// }
		// }
		// }

		return nodes;
	}

	public Set<CoNLLWord> getAllWords(String text, Set<String> organNames, int id) {
		HashMap<Integer, Node> nodes = parse(text, organNames);
		Set<CoNLLWord> words = new HashSet<CoNLLWord>();
		List<Integer> ids = new ArrayList<Integer>();

		List<Integer> wids = new ArrayList<Integer>();
		wids.add(id);

		Set<CoNLLWord> result = new HashSet<CoNLLWord>();

		List<Node> ns = nodes.get(id).getNextwords();

		for (Node n : ns) {
			result.add(n.getWord());
			if (n.getNextwords().size() > 0) {
				for (Node n2 : n.getNextwords()) {
					result.add(n2.getWord());
					if (n2.getNextwords().size() > 0) {
						for (Node n3 : n2.getNextwords()) {
							result.add(n3.getWord());
							List<Node> n4s = n3.getNextwords();
							if (n4s.size() > 0) {
								for (Node n4 : n4s) {
									result.add(n4.getWord());
									List<Node> n5s = n4.getNextwords();
									if (n5s.size() > 0) {
										for (Node n5 : n5s) {
											result.add(n5.getWord());
										}
									}
								}
							}

						}
					}
				}
			}
		}

		// while (wids != null) {
		//
		// for (Integer wid : wids) {
		//
		// if (nodes.get(wid).getNextwords().size() > 0) {
		//
		// for (Node node : nodes.get(wid).getNextwords()) {
		// ids.add(node.getId());
		//
		// wids.add(node.getId());
		// }
		// } else {
		// wids = null;
		// }
		// }
		// }

		// List<Integer> ids = nodes.get(id).getNextwords().stream().map(item ->
		// item.getId())
		// .collect(Collectors.toList());
		// for (Entry<Integer, Node> en : nodes.entrySet()) {
		// words.add(en.getValue().getWord());
		// }

		return result;
	}

	public Set<String> getwords(String text, Set<String> organNames, int id) {
		Set<CoNLLWord> words = getAllWords(text, organNames, id);
		Set<String> ws = new HashSet<String>();
		for (CoNLLWord co : words) {
			ws.add(co.NAME);
		}
		return ws;
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
