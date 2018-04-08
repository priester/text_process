package com.priester.stanf.test;

import java.util.List;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

/**
 * Created by KrseLee on 2016/11/4.
 */
public class StanfordChineseNlpExample {

	public static void main(String[] args) {

		StanfordChineseNlpExample example = new StanfordChineseNlpExample();

		example.runChineseAnnotators();

	}

	public void runChineseAnnotators() {

		String text = "关于多伦科技股份有限公司的问询函。" + "关于对中信国安葡萄酒业股份有限公司发行股份购买资产暨关联交易预案信息披露的问询函。" + "关于潍坊亚星化学股份有限公司的问询函。"
				+ "圣达生物关于公司拟购买土地使用权的公告。" + "关于对中信国安葡萄酒业股份有限公司发行股份购买资产暨关联交易预案信息披露的问询函。" + "关于重大资产重组的进展公告。"
				+ "股东股份被质押的公告。" + "关于控股股东进行股票质押及部分解除质押的公告。" + "关于公开挂牌转让全资子公司西安天和军民融合创新技术研究有限公司部分股权的进展公告。"
				+ "关于公司股票交易异常波动的公告。" + "关于持股5%以上股东部分股份质押的公告。" + "2017年第四次临时股东大会决议公告。" + "2017年第一次临时股东大会决议公告。"
				+ "关于变更持续督导保荐机构和保荐代表人的公告。";
		Annotation document = new Annotation(text);
		StanfordCoreNLP corenlp = new StanfordCoreNLP("StanfordCoreNLP-chinese.properties");
		corenlp.annotate(document);
		parserOutput(document);
	}

	public void parserOutput(Annotation document) {
		// these are all the sentences in this document
		// a CoreMap is essentially a Map that uses class objects as keys and
		// has values with custom types
		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

		for (CoreMap sentence : sentences) {
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional token-specific methods
			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				// this is the text of the token
				String word = token.get(CoreAnnotations.TextAnnotation.class);
				// this is the POS tag of the token
				String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				// this is the NER label of the token
				String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

//				System.out.println(word + "\t" + pos + "\t" + ne);
			}

			// this is the parse tree of the current sentence
//			Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
//			System.out.println("语法树：");
//			System.out.println(tree.toString());

			// this is the Stanford dependency graph of the current sentence
			SemanticGraph dependencies = sentence
					.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
			System.out.println("依存句法：");
			System.out.println(dependencies.toString());
		}

		// This is the coreference link graph
		// Each chain stores a set of mentions that link to each other,
		// along with a method for getting the most representative mention
		// Both sentence and token offsets start at 1!
//		Map<Integer, CorefChain> graph = document.get(CorefCoreAnnotations.CorefChainAnnotation.class);
	}
}
