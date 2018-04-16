package com.priester.segment;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

public class HanlpSegment {

	public static List<Term> segment(Set<String> organNames, String text) {
		for (String organName : organNames) {
			CustomDictionary.add(organName, "nz 0");
		}

		List<Term> terms = HanLP.segment(text);

		for (String organName : organNames) {
			CustomDictionary.remove(organName);
		}

		return terms;
	}

	public static String segmentOutNuture(Set<String> organNames, String text) {
		for (String organName : organNames) {
			CustomDictionary.add(organName, "nz 1000");
			CustomDictionary.add("虚假宣传", "v 0");
			CustomDictionary.add("审核不实", "v 0");
		}
		
		String segment = "";
		List<Term> terms = HanLP.segment(text);
		
		CoNLLSentence sentence = HanLP.parseDependency(text);
        System.out.println(sentence);
        
        
        for (CoNLLWord word : sentence) {
        	if (word.CPOSTAG.equals("nz")) {
				System.out.println(word.DEPREL);
				System.out.println(word.LEMMA);

				System.out.println(word.HEAD.ID);

				while (word.HEAD.ID > word.ID) {
				System.out.println(word.HEAD.ID );
					
				System.out.println(sentence.getWordArray()[word.HEAD.ID-1]);
				System.out.println(word.HEAD.ID);
				word = sentence.getWordArray()[word.HEAD.ID];
				}
				System.out.println("................................");
			}
//        	System.out.println(sentence.getEdgeArray());
//        	System.out.println("................................");
        
        	
//        {
//            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
//        // 也可以直接拿到数组，任意顺序或逆序遍历
//        CoNLLWord[] wordArray = sentence.getWordArray();
//        for (int i = wordArray.length - 1; i >= 0; i--)
//        {
//            CoNLLWord word = wordArray[i];
//            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
//        }
//        // 还可以直接遍历子树，从某棵子树的某个节点一路遍历到虚根
//        CoNLLWord head = wordArray[12];
//        while ((head = head.HEAD) != null)
//        {
//            if (head == CoNLLWord.ROOT) System.out.println(head.LEMMA);
//            else System.out.printf("%s --(%s)--> ", head.LEMMA, head.DEPREL);
//        }
        
		
		for(Term t : terms){
			segment =segment + t.word + ' ';
		}
		segment = segment.substring(0, segment.length()-1);
		
		for (String organName : organNames) {
			CustomDictionary.remove(organName);
		}
		
	

		return segment;
	}

	public static void main(String[] args) {
//		String text = "11日，上海市公安局浦东分局官方微博发布信息称，2018年4月9日，善林（上海）金融信息服务有限公司法定代表人周某某因涉嫌违法犯罪，向公安机关投案自首，已依法立案侦查。";

		String text = "小明吃了个苹果。";
		
		Set<String> organNames = new HashSet<String>();
		organNames.add("乐视");
		organNames.add("南京银行");
		organNames.add("中泰创展控股有限公司");

		System.out.println(segmentOutNuture(organNames, text));
		
	}
}
