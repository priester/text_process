package com.priester.sentencesfitler;

public class PreHandle {

	public static String filterSentences(String chapter) {
		chapter = chapter.replaceAll("证券代码：\\d{6}", "");
		chapter = chapter.replaceAll("公告编号：\\d{4}-\\d{3}", "");
		chapter = chapter.replaceAll("证券简称：[\u4e00-\u9fa5]{2,5}\\s", "");
		// 上证公函【2017】2283号
		chapter = chapter.replaceAll("上证公函【\\d{4}】\\d{2,5}号", "");
		return chapter;
	}

	public static String processTitle(String title) {
		title = title.replaceAll("^[\u4e00-\u9fa5]{2,6}:", "");
		title = title.replaceAll("^\\d{6}:", "");
		title = title.replaceAll("\\(\\d{4}/\\d{1,2}/\\d{1,2}\\)", "");
		return title;
	}
	
	public static String eventWithTitle(String title) {
		title = title.replaceAll("^[\u4e00-\u9fa5]{2,6}:", "");
		title = title.replaceAll("^\\d{6}:", "");
		title = title.replaceAll("\\(\\d{4}/\\d{1,2}/\\d{1,2}\\)", "");
		title = title.replaceAll("\\(.*\\)", "");
		
//		title = title.replaceAll(".*:", "");
		title = title.replaceAll("\\d{4}年", "");
		title = title.replaceAll("(关于|的|提示性|情况|非公开|非公|有关)", "");
		title = title.replaceAll("(第.*(次|届)|本次)", "");
		title = title.replaceAll("(关注函|公告|问询函|信息|法律意见书|自查报告|可行性分析报告|报告|反馈意见)", "");
		title = title.replaceAll("(披露|回复|文件修订|告知函|说明|文件|书)", "");
		
		title = title.replaceAll("[\u4e00-\u9fa5]{4,14}公司", "");
		return title;
	}
	

	// chapter2 = chapter2.replaceAll("证券代码：[0-9]?[0-9]:[0-9][0-9] 证券简称：.*
	// 公告编号：[0-9]?[0-9]:[0-9][0-9]-[0-9]?[0-9]:[0-9][0-9]", "");
	// chapter2 = chapter2.replaceAll("证券代码： ", "");

	public static void main(String[] args) {
		// String chapter2 = "8888 证券代码：300391 证券简称：康跃科技 公告编号： 2017-072
		// 上证公函【2017】2283号(2012/01/02)";
		// chapter2 = chapter2.replaceAll("证券代码：\\d{6}", "");
		// chapter2 = chapter2.replaceAll("公告编号： \\d{4}-\\d{3}", "");
		// chapter2 = chapter2.replaceAll("证券简称：[\u4e00-\u9fa5]{2,5}\\s", "");
		// chapter2 = chapter2.replaceAll("上证公函【\\d{4}】\\d{2,5}号", "");
		// chapter2 = chapter2.replaceAll("\\(\\d{4}/\\d{1,2}/\\d{1,2}\\)", "");
		String txt = "关于多伦科技股份有限公司的问询函";
		txt = eventWithTitle(txt);
		System.out.println(txt);
	}
}
