package com.priester.sentencesfitler;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class TitleData {

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

	public static Map<Integer, String> getTitles(int beginId, int endId) {
		Connection conn = null;
		Statement stmt = null;
		Map<Integer, String> chapters = new HashMap<Integer, String>();
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id,title FROM announcements where id >= " + beginId + " and id <" + endId;

			ResultSet rs = stmt.executeQuery(sql);

			// 展开结果集数据库
			while (rs.next()) {
				int id = rs.getInt("id");
				Blob blob = rs.getBlob("title");
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
}