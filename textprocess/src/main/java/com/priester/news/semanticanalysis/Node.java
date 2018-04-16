package com.priester.news.semanticanalysis;

import java.util.ArrayList;
import java.util.List;

import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;

public class Node {

	private CoNLLWord word;
	private List<Node> nextwords = new ArrayList<Node>();

	public Node() {
		super();
	}

	public CoNLLWord getWord() {
		return word;
	}

	public void setWord(CoNLLWord word) {
		this.word = word;
	}

	public List<Node> getNextwords() {
		return nextwords;
	}

	public void setNextwords(List<Node> nextwords) {
		this.nextwords = nextwords;
	}

}
