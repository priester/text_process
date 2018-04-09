package com.priester.news.pojo;

public class News {
	private int id;
	private String title;
	private String content;
	private String source;
	private String url;
	private String introduce;
	private String keyWords;
	private String sentiment;
	private String confidence;

	public News() {
		super();
	}

	public News(String title, String content, String source, String url, String introduce) {
		super();
		this.title = title;
		this.content = content;
		this.source = source;
		this.url = url;
		this.introduce = introduce;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", content=" + content + ", source=" + source + ", url=" + url + ", introduce="
				+ introduce + "]";
	}

}
