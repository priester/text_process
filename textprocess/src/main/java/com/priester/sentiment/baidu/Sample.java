package com.priester.sentiment.baidu;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import com.baidu.aip.nlp.AipNlp;

public class Sample {
	// 设置APPID/AK/SK
	public static final String APP_ID = "10465249";
	public static final String API_KEY = "hZB9U6YG6H5kCjCDU6wKZTmG";
	public static final String SECRET_KEY = "DvGjmXA5fMHCSLHbFnqHIoeTB0ToihbU";

	public static void main(String[] args) throws InterruptedException {
		// 初始化一个AipNlp
		AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
		// client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
		// client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理

		// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
		// 也可以直接通过jvm启动参数设置此环境变量
		// System.setProperty("aip.log4j.conf",
		// "path/to/your/log4j.properties");

		// 调用接口
		// String text = "百度是一家高科技公司";
		// JSONObject res = client.lexer(text, null);
		// System.out.println(res.toString(2));

		// 传入可选参数调用接口

		List<String> titles = Data.text;
		// 传入可选参数调用接口
		HashMap<String, Object> options = new HashMap<String, Object>();
		// 情感倾向分析
//		for (String title : titles) {
//			JSONObject res = client.sentimentClassify(title, options);
//			System.out.println(title);
//			System.out.println(res.getJSONArray("items"));
//			Thread.sleep(100);
//		}
		
		
		String text = "　电梯广告堪比印钞机，行业老大1年净赚60亿！突然，“小弟”宣战了  　　说到电梯广告，很多人第一个想到的就是分众传媒。这家2003年成立的传媒集团专注于楼宇媒体广告业务在营收仅过2亿元的时候，挑战行业巨无霸，对标千亿级公司，新潮传媒为何发起这场战斗？  　　一方面，对于新潮传媒来说这是生与死的问题。正如张继学所说，只有行业中的头部企业才能融到钱，成为这个行业的领跑者，死掉就不容易，所以“新潮要不停地奔跑，要保证前两名的市场地位。”  　　另一方面，恐怕也与公司背负的对赌协议有关。  　　2017年11月，欧普照明公告称，公司拟以自有资金出资1.5亿元投资成都新潮传媒集团股份有限公司。此次投资后，欧普照明持有新潮传媒3.03%股权。  　　初创型企业能拿到产业资本的融资，往往会有对赌承诺。新潮传媒的这一次融资也不例外。  　　公告显示，当出现下列情况，投资方可要求公司回购其持有的股份：  　　（1）不论任何主观或客观原因，目标公司不能在2022年12月31日前实现在包括上海证券交易所、深圳证券交易所、香港联合交易所、美国的全国性证交所等首次公开发行股票上市，或者被上市公司整体收购；  　　（2）原股东或公司实质性违反本协议及相关条款并造成投资者重大损失。  　　满足上述约定的回购条件时，投资方有权选择由新潮传媒进行回购，或将股权出售给其他第三方，其他股东应当做出，且新潮传媒有义务促使股东会同意公司回购或者同意投资方将股权出售给其他第三方的决议。  　　回购价格按照投资者全部出资额自从实际缴纳出资日起至公司实际支付回购价款之日按年利率8%计算的利息（不计算复利）。";
		JSONObject res = client.sentimentClassify(text, options);
		System.out.println(res.getJSONArray("items").getJSONObject(0).get("sentiment"));
		System.out.println(res.getJSONArray("items").getJSONObject(0).get("confidence").getClass());
		System.out.println(res);
	}
}