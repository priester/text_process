//package com.priester.stanf.test;
//
//import java.util.Properties;
//
//import edu.stanford.nlp.coref.CorefCoreAnnotations;
//import edu.stanford.nlp.coref.data.CorefChain;
//import edu.stanford.nlp.coref.data.Mention;
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.pipeline.Annotation;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.util.CoreMap;
//import edu.stanford.nlp.util.StringUtils;
//
//public class CorefDemo {
//	public static void main(String[] args) {
////		String text = "关于对南京多伦科技股份有限公司 2017年三季报相关事项的问询函 南京多伦科技股份有限公司： 我部关注到，2017年前三季度，公司营业收入和净利润出现大幅下滑，但公司未详细披露原因。为了便于投资者理解，根据本所《股票上市规则》第17.1条规定，请公司核实并补充披露下述事项。 一、三季报显示，公司实现营业收入3.86亿元，同比减少38.56%；实现归属于上市公司股东的净利润0.84亿元，同比减少65.63%。请公司详细披露主营业务的经营模式以及营业收入确认模式，并详细说明营业收入及净利润大幅下滑的原因。 二、三季报显示，公司应收账款期末余额3.15亿元，与2016年同期应收账款期末余额3.36亿元基本持平，但与本期营业收入大幅下滑不配比。请公司详细披露：（1）在营业收入大幅下滑的同时，应收账款余额基本持平的原因及合理性；（2）公司应收账款余额前十名形成的业务背景、客户名称、与公司关联关系、结算方式、平均账龄以及回款的具体安排。 三、三季报显示，公司预收账款期末余额为3.56亿元，与2016年同期预收账款期末余额3.54亿元基本持平，但与本期营业收入大幅下滑不配比。请公司详细披露：（1）在营业收入大幅下滑的同时，预收账款余额基本持平的原因及合理性；（2）公司预收账款余额前十名形成的业务背景、客户名称、与公司关联关系、结算方式以及确认收入的安排。 请你公司收函后立即披露本问询函，并于2017年11月8日之前履行相应的信息披露义务，同时以书面的方式回复我部。";
////		String text = "南京多伦科技股份有限公司： 我部关注到，2017年前三季度，公司营业收入和净利润出现大幅下滑，但公司未详细披露原因";
//		String text = "苹果公司很有名，这家公司的人都很努力";
//		args = new String[] { "-props", "StanfordCoreNLP-chinese.properties" };
//		Annotation document = new Annotation(text);
//		Properties props = StringUtils.argsToProperties(args);
//		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//		pipeline.annotate(document);
//		System.out.println("---");
//		System.out.println("coref chains");
//		for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
//			System.out.println("\t" + cc);
//		}
//		for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
//			System.out.println("---");
//			System.out.println("mentions");
//			for (Mention m : sentence.get(CorefCoreAnnotations.CorefMentionsAnnotation.class)) {
//				System.out.println("\t" + m);
//			}
//		}
//	}
//}
