package com.priester.sentencesfitler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static String base_path = "D:/csegment/";

	public static void main(String[] args) throws IOException {
		// Map<Integer, String> chapters = CharpterData.getChapters(0, 2);

		// System.out.println(chapters.get(1));

		// System.out.println(chapters.get(1));

		 writertitle(base_path, 0, 70000);
//		writerSentence(base_path, 0, 70000);
		// System.out.println("ok");
		// getEventWithTitle(base_path, 400, 500);

	}

	public static void writerSentence(String path, int beginId, int endId) throws IOException {
		Map<Integer, String> chapters = CharpterData.getChapters(beginId, endId);

		Map<String, Integer> keyWords = new LinkedHashMap<String, Integer>();

		int count = 0;

		for (Entry<Integer, String> en : chapters.entrySet()) {
			File file = new File(path + "chapter" + en.getKey());
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			List<String> sentences = CharpterData.splitSentence(en.getValue());
			// bw.write(en.getValue() + "\r\n ");
			// bw.write("关键词：" + HanlpSegment.getKeyWords(en.getValue()) + "\r\n
			// ");
			// for (String keyword : HanlpSegment.getKeyWords(en.getValue())) {
			// int value = keyWords.containsKey(keyword) ? keyWords.get(keyword)
			// + 1 : 1;
			// keyWords.put(keyword, value);
			// }

			for (int i = 0; i < sentences.size(); i++) {
				bw.write(HanlpSegment.segmentOutNuture(new HashSet<String>(), sentences.get(i)));
				if (i != sentences.size() - 1) {
					bw.write("\r\n");
				}
			}

			bw.close();
			fw.close();
			count++;

			if (count % 1000 == 0) {
				System.out.println("1000条");
			}

		}

		File file = new File(path + "keywords");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);

		for (Entry<String, Integer> en : keyWords.entrySet()) {
			if (en.getValue() > 1) {
				bw.write(en.getKey() + ":" + en.getValue());
				bw.write("\r\n");
			}
		}
		bw.close();
		fw.close();

		System.out.println(keyWords);
		System.out.println(keyWords.size());
	}

	public static void writertitle(String path, int beginId, int endId) throws IOException {
		Map<Integer, String> titles = TitleData.getTitles(beginId, endId);

		int count = 0;

		File file = new File(path + "title");
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);

		for (Entry<Integer, String> en : titles.entrySet()) {
//			String title = PreHandle.processTitle(en.getValue());
			String title = en.getValue();
			bw.write(title + "\r\n");
			// bw.write(en.getKey() + "," + title + "\r");
			// System.out.println(en.getKey() + "," + title + "\r");

			count++;
			if (count % 1000 == 0) {
				System.out.println("1000条");
			}
		}
		bw.close();
		fw.close();
	}

	public static void getEventWithTitle(String path, int beginId, int endId) throws IOException {
		Map<Integer, String> titles = TitleData.getTitles(beginId, endId);
		for (Entry<Integer, String> en : titles.entrySet()) {
			System.out.println(PreHandle.eventWithTitle(en.getValue()));
			System.out.println(HanlpSegment.segment(new HashSet<String>(), PreHandle.eventWithTitle(en.getValue())));
		}

	}

}
