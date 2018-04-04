package com.priester.stanf.test;

import java.io.PrintStream;
import java.util.Properties;

public class GrammarTree {
	private static final String basedir = "edu/stanford/nlp/models/segmenter/chinese";

	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream(System.out, true, "utf-8"));

		Properties props = new Properties();
		props.setProperty("sighanCorporaDict", basedir);
		// props.setProperty("NormalizationTable", "data/norm.simp.utf8");
		// props.setProperty("normTableEncoding", "UTF-8");
		// below is needed because CTBSegDocumentIteratorFactory accesses it
		props.setProperty("serDictionary", basedir + "/dict-chris6.ser.gz");
		if (args.length > 0) {
			props.setProperty("testFile", args[0]);
		}
		props.setProperty("inputEncoding", "UTF-8");
		props.setProperty("sighanPostProcessing", "true");

//		CRFClassifier<CoreLabel> segmenter = new CRFClassifier<>(props);
//		segmenter.loadClassifierNoExceptions(basedir + "/ctb.gz", props);
//		for (String filename : args) {
//			segmenter.classifyAndWriteAnswers(filename);
//		}
//
//		String sample = "stanford-nlpcore分词测试";
//		List<String> segmented = segmenter.segmentString(sample);
//		System.out.println(segmented);
	}
}
