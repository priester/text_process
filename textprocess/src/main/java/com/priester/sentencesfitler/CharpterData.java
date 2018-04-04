package com.priester.sentencesfitler;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharpterData {

	final static String default_sentence_separator = "[。？?！!；;]";

	public static Connection getConnection() throws Exception {

		String jdbcDriver = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost:3306/crawl";

		// 数据库的用户名与密码，需要根据自己的设置
		String USER = "root";
		final String PASS = "1234";

		// 注册 JDBC 驱动
		Class.forName(jdbcDriver);

		return DriverManager.getConnection(dbUrl, USER, PASS);

	}

	public static Map<Integer, String> getChapters(int beginId, int endId) {
		Connection conn = null;
		Statement stmt = null;
		Map<Integer, String> chapters = new HashMap<Integer, String>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id,detail,title FROM announcements where id >= " + beginId + " and id <" + endId+ " AND detail IS NOT NULL AND title IS NOT NULL";

			ResultSet rs = stmt.executeQuery(sql);

			// 展开结果集数据库
			while (rs.next()) {
				int id = rs.getInt("id");
				Blob blob = rs.getBlob("detail");
				if (null != blob) {
					String chapter = new String(blob.getBytes((long) 1, (int) blob.length()), "UTF-8");
					chapters.put(id, chapter);
				}
			}
			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return chapters;
	}

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
			for (String sent : line.split(sentence_separator )) // [，,。:：“”？?！!；;]
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