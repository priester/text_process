package com.priester.utils;

public class StripHtmlUtil {

	public static String stripHtml(String content) {

		content = content.replaceAll("(?i)(<SCRIPT)[\\s\\S]*?((</SCRIPT>)|(/>))", "");
		content = content.replaceAll("<p .*?>", "\r\n");

		content = content.replaceAll("<br\\s*/?>", "\r\n");

		content = content.replaceAll("\\<.*?>", "");

		content = content.replaceAll("&nbsp;", "");
		
		content = content.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
		
		content = content.replaceAll("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]", "");
		
		
		
		content = content.replaceAll("[^\u4e00-\u9fa5]{4}+", "");
		// content = HTMLDecoder.decode(content);
		content = content.replaceAll("  　　", "");
		content = content.replaceAll(" 　　", "");
		return content;
	}
}
