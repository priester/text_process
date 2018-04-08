package com.priester.utils;

import java.util.ArrayList;
import java.util.List;

import com.priester.sentencesfitler.PreHandle;

public class ContentUtil {
	
	public static String default_sentence_separator = "[。？?！!；;]";
	/**
	 * 将文章分割为句子 默认句子分隔符为：[，,。:：“”？?！!；;]
	 *
	 * @param document
	 * @return
	 */
	public static List<String> splitSentence(String document) {
		document = PreHandle.filterSentences(document);
		return splitSentence(document, default_sentence_separator);
	}

	/**
	 * 将文章分割为句子
	 * 
	 * @param document
	 *            待分割的文档
	 * @param sentence_separator
	 *            句子分隔符，正则表达式，如： [。:？?！!；;]
	 * @return
	 */
	public static List<String> splitSentence(String document, String sentence_separator) {
		List<String> sentences = new ArrayList<String>();
		for (String line : document.split("[\r\n]")) {
			line = line.trim();
			if (line.length() == 0)
				continue;
			for (String sent : line.split(sentence_separator)) // [，,。:：“”？?！!；;]
			{
				sent = sent.trim();
				if (sent.length() == 0)
					continue;
				sentences.add(sent);
			}
		}

		return sentences;
	}
}
