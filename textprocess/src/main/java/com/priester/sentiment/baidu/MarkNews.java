package com.priester.sentiment.baidu;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;
import com.priester.news.dto.JdbcProcess;
import com.priester.news.pojo.News;
import com.priester.utils.DBUtil;

public class MarkNews {
	static int begingId = 64000;
	static int endId = 80000;
	static int batchSize = 1000;

	public static final String APP_ID = "10465249";
	public static final String API_KEY = "hZB9U6YG6H5kCjCDU6wKZTmG";
	public static final String SECRET_KEY = "DvGjmXA5fMHCSLHbFnqHIoeTB0ToihbU";

	public static void main(String[] args) throws Exception {

		// 初始化一个AipNlp
		AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 传入可选参数调用接口
		HashMap<String, Object> options = new HashMap<String, Object>();

		Connection conn = DBUtil.getConnection();
		conn.setAutoCommit(false);
		for (int i = 0; i < (endId - begingId) / batchSize + 1; i++) {

			PreparedStatement ps = null;
			try {
				System.out.println(begingId + i * batchSize);
				System.out.println(begingId + (i + 1) * batchSize);
				List<News> newsList = JdbcProcess.readerArt(conn, begingId + i * batchSize,
						begingId + (i + 1) * batchSize);
				String sql = "update p_news set sentiment = ?, confidence = ? where id =?";
				ps = conn.prepareStatement(sql);
				for (News news : newsList) {
					JSONObject resp = null;
					try {
						String text = news.getTitle() + "\n" + news.getContent();
						if (text.length() > 1000) {
							text = text.substring(0, 1000);
						}
						
						resp = client.sentimentClassify(text, options);
						JSONObject res = resp.getJSONArray("items").getJSONObject(0);
						int sentiment = res.getInt("sentiment");
						Double confidence = res.getDouble("confidence");
						ps.setString(1, String.valueOf(sentiment));
						ps.setBigDecimal(2, new BigDecimal(confidence));
						ps.setInt(3, news.getId());
						ps.addBatch();
						ps.executeBatch();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(resp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					conn.commit();
					ps.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				System.out.println("ok 10");
			}
		}
	}
}
