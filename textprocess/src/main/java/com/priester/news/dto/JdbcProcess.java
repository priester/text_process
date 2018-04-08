package com.priester.news.dto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.priester.news.pojo.News;
import com.priester.utils.DBUtil;

public class JdbcProcess {
	
	private static final Logger logger = LoggerFactory.getLogger(JdbcProcess.class);

	public static List<String> readerTitle(Connection conn) throws Exception {
		List<String> title = new ArrayList<String>();
		PreparedStatement ps = null;
		try {
			String sql = "SELECT title FROM negative_news WHERE id > 372 AND id <400";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				title.add(rs.getString(1));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			ps.close();
		}
		return title;
	}

	public static List<News> readerArt(Connection conn, int beginId, int endId) throws Exception {
		List<News> title = new ArrayList<News>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id,title,content FROM negative_news where id >= " + beginId + " AND id <" + endId;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				News news = new News();
				news.setId(rs.getInt(1));
				news.setTitle(rs.getString(2));
				news.setContent(rs.getString(3));
				title.add(news);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			rs.close();
			ps.close();
		}
		return title;
	}

	public static void saveKeyWords(Connection conn, List<News> newsList) throws Exception {
		PreparedStatement ps = null;

		try {
			String sql = "update negative_news set text_rank_keywords = ? where id =?";
			ps = conn.prepareStatement(sql);
			for (News news : newsList) {
				ps.setString(1, news.getKeyWords());
				ps.setInt(2, news.getId());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			ps.close();
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(readerTitle(DBUtil.getConnection()));
	}
}
