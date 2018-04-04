package com.priester.sentencesfitler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

public class HanlpSegment {

	public static List<Term> segment(Set<String> organNames, String text) {
		for (String organName : organNames) {
			CustomDictionary.add(organName, "org 0");
		}

		List<Term> terms = HanLP.segment(text);

		for (String organName : organNames) {
			CustomDictionary.remove(organName);
		}

		return terms;
	}
	
	public static String segmentOutNuture(Set<String> organNames, String text) {
//		for (String organName : organNames) {
//			CustomDictionary.add(organName, "nz 0");
//		}
		
		String segment = "";
		List<Term> terms = HanLP.segment(text);
		
		for(Term t : terms){
			segment =segment + t.word + ' ';
		}
		segment = segment.substring(0, segment.length()-1);
		
//		for (String organName : organNames) {
//			CustomDictionary.remove(organName);
//		}
		return segment;
	}
	

	public static List<String> getKeyWords(String chapter) {
		List<String> keywordList = HanLP.extractKeyword(chapter, 20);
		List<String> vkeywordList = new ArrayList<String>();
		for (String keyWords : keywordList) {
			if (segment(new HashSet<String>(), chapter).toString().contains(keyWords + "/v")) {
				vkeywordList.add(keyWords);
			}
		}
		return vkeywordList;
	}

	public static void main(String[] args) {

		String text = "证券代码：300281 " + "证券简称：金明精机 " + "公告编号：2017-067 " + "广东金明精机股份有限公司 " + "关于公司财务总监辞职的公告 "
				+ "本公司及董事会全体人员保证信息披露的内容真实、准确和完整，没有虚假记载、误导性陈述或者重大遗漏。 "
				+ "广东金明精机股份有限公司(以下简称“公司”) 董事会于近日 收到公司财务总监曾广克先生的书面辞职报告，曾广克先生因达到法定退休年龄，申请辞去公司财务总监职务，曾广克先生辞职后，不再担任公司任何职务。"
				+ "根据《深圳证券交易所创业板股票上市规则》等相关法规规定，曾广克先生辞职报告自送达公司董事会之日起生效。 " + "曾广克先生原定任期至第三届董事会届满之日（即2019年11月 15 日）。"
				+ "截止本公告披露之日，曾广克先生持有公司股份 112,456 股。" + "公司董事会对曾广克先生在任职期间所做的贡献表示衷心的感谢！" + "特此公告。 "
				+ "广东金明精机股份有限公司董事会 二零一七年十一月六日";

		Set<String> organNames = new HashSet<String>();
		organNames.add("广东金明精机股份有限公司");

		System.out.println(segment(organNames, text));

		List<String> keywordList = HanLP.extractKeyword(text, 20);
		for (String keyWords : keywordList) {
			if (segment(organNames, text).toString().contains(keyWords + "/v")) {
				System.out.println(keyWords);
			}
		}

	}
}
