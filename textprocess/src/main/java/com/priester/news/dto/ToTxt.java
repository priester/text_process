package com.priester.news.dto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.priester.news.pojo.News;
import com.priester.utils.StripHtmlUtil;

public class ToTxt {
	public static void main(String[] args) throws Exception {
		String line = "";
		File file = new File("C:\\Users\\fany\\Desktop\\news_output_result-10万\\news_output_result.dat");
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
		String text = "";
		while ((line = br.readLine()) != null) {
			// System.out.println(line);
			News news = new News();
			if (line.matches(".*.*.*")) {
				// text.split("");
				if (!text.isEmpty()) {

					System.out.println(text);
					String[] tt = text.split("");
					System.out.println("................................" + tt.length);
					text = "";

					if (tt.length == 3) {

						news.setIntroduce(tt[0]);
						news.setTitle(tt[1]);
						news.setContent(tt[2]);
						news.setSource("xxxx");
						JdbcProcess.save(news);
					} else if (tt.length == 2) {

						news.setIntroduce(tt[0]);
						news.setTitle(tt[1]);
						news.setContent("");
						news.setSource("xxxx");
						JdbcProcess.save(news);
					}
				}
			}
			text = text + line;
		}

		// System.out.println(text.split(line).length);

	}
}
