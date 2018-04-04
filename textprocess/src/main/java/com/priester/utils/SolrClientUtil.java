package com.priester.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;

import com.alibaba.fastjson.JSONObject;
import com.priester.utils.HttpClientUtil;

public class SolrClientUtil {
	public static String baseUrl = null;

	static {
		Properties prop = new Properties();
		InputStream is = SolrClientUtil.class.getClassLoader().getResourceAsStream("application.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		baseUrl = prop.getProperty("solr.base.url");
	}

	public static String countNews(String queryCondition) throws ClientProtocolException, IOException {
		String url = baseUrl+"/negative_news/select?q="+queryCondition+"&wt=json";
		String request = HttpClientUtil.get(url);
		JSONObject object = JSONObject.parseObject(request);
		return object.getJSONObject("response").getString("numFound");
	}

	public static String countNewsByWords(String word) throws ClientProtocolException, IOException {
		String queryCondition = "title:\"" + word + "\" or content:\"" + word + "\"";
		queryCondition = URLEncoder.encode(queryCondition,"utf-8");
		return countNews(queryCondition);
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		System.out.println(countNewsByWords("起诉"));
	}
}
