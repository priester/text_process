package com.priester.news.keywods;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hankcs.hanlp.HanLP;
import com.priester.news.dto.JdbcProcess;
import com.priester.news.pojo.News;
import com.priester.utils.DBUtil;

public class KeyWordsRunnable implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(KeyWordsRunnable.class);

	private static int BATCH_SIZE = 100;
	private Map<String, Integer> keyWordMap;
	private int beginId;
	private int endId;
	private CountDownLatch countDownLatch;

	public KeyWordsRunnable(int beginId, int endId, Map<String, Integer> keyWordMap, CountDownLatch countDownLatch) {
		super();
		this.beginId = beginId;
		this.endId = endId;
		this.keyWordMap = keyWordMap;
		this.countDownLatch = countDownLatch;
	}

	private String getKeyWords(String news) {
		List<String> keywordList = HanLP.extractKeyword(news, 20);
		return StringUtils.join(keywordList.toArray(), " ");
	}

	private void saveKeywords(int beginId, int endId) throws Exception {
		Connection conn = DBUtil.getConnection();
		conn.setAutoCommit(false);
		List<News> newsList = JdbcProcess.readerArt(conn, beginId, endId);
		for (News news : newsList) {

			String keywordsStr = getKeyWords(news.getTitle() + "/r/n" + news.getContent());
			news.setKeyWords(keywordsStr);

			String[] keywords = keywordsStr.split(" ");
			for (String keyWord : keywords) {
				keyWordMap.put(keyWord, keyWordMap.containsKey(keyWord) ? keyWordMap.get(keyWord) + 1 : 1);
			}
		}
		JdbcProcess.saveKeyWords(conn, newsList);
	}

	public void run() {
		int batchBeginId = beginId;
		while (batchBeginId < endId) {
			int batchEndId = batchBeginId + BATCH_SIZE < endId ? batchBeginId + BATCH_SIZE : endId;
			try {
				saveKeywords(batchBeginId, batchEndId);
			} catch (Exception e) {
				logger.error(e.getMessage());
			} finally {
				batchBeginId = batchBeginId + BATCH_SIZE;
				countDownLatch.countDown();
			}
		}
	}
}
