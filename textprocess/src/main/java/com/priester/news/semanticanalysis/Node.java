package com.priester.news.semanticanalysis;

import java.util.ArrayList;
import java.util.List;

import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;

public class Node {

	private int id;
	private CoNLLWord word;
	private List<Node> nextwords = new ArrayList<Node>();

	public Node() {
		super();
	}

	public Node(int id, CoNLLWord word) {
		super();
		this.id = id;
		this.word = word;
	}

	public Node(List<Node> nextwords) {
		super();
		this.nextwords = nextwords;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void addNextWords(Node node) {
		this.nextwords.add(node);
	}

}
