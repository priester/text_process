package com.priester.news.keywods;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import com.priester.utils.IOUtils;
import com.priester.utils.ThreadPoolProxy;

public class SaveKeyWords {

	public static void main(String[] args) throws InterruptedException {
		int begingId = 0;
		int endId = 3000;
		int threadcount = 5;
		CountDownLatch countDownLatch = new CountDownLatch(threadcount);

		ThreadPoolProxy proxy = new ThreadPoolProxy(threadcount, threadcount, 0);
		Map<String, Integer> keywordMap = new ConcurrentHashMap<String, Integer>();

		for (int i = 0; i < threadcount; i++) {
			System.out.println(i * (endId - begingId) / threadcount);
			System.out.println((i + 1) * (endId - begingId) / threadcount);
			Runnable runnable = new KeyWordsRunnable(i * (endId - begingId) / threadcount,
					(i + 1) * (endId - begingId) / threadcount, keywordMap, countDownLatch);
			proxy.executeTask(runnable);
		}
		countDownLatch.await();
		proxy.shurtdown();

		IOUtils.write2txt(keywordMap,
				"C:\\Users\\fany\\git\\textprocess\\src\\main\\java\\com\\priester\\news\\keywods\\keywords.csv");
	}
}
