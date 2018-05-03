package kumo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.hankcs.hanlp.summary.TextRankKeyword;
import com.kennycason.kumo.WordFrequency;

public class Keywords {

	public static void main(String[] args) {
		String content = "“五一”楼市意外低迷。在刚刚结束的“五一”小长假中，尽管众多房企推出多种活动，但不同于去年同期的火热场面，今年“五一”楼市较为冷清。“五一”期间，恒大在沈阳推出“辽宁恒大超级品牌日”，同时推出恒大绿洲、恒大雅苑等八个楼盘，并推出享购房折上98折、成功认购另减3万元等多重优惠。在郑州，招商天地华府项目推出98折优惠；恒大山水城项目推出首付分期，首期仅5%，享额外96折上折；郑北孔雀城则推出一二期珍藏房源，下叠送小院，中叠送阳台，上叠送露台，买叠拼送停车位。房企的大力度活动并未换来高成交量。中原地产数据显示，从小长假前两天来看，一线城市合计网签商品房住宅360套，与清明小长假前两天基本持平，同比跌幅超过50%；二线城市网签量同比跌幅在30%左右。记者在广州看到，看房人明显减少。一位中介人士表示，“五一”前两天只带两波客人看了几套房，但是一套都没有成交。网易数据中心显示，在“五一”新推2434套的背景下，小长假前两天，广州新房仅成交281套，其中4月29日成交177套，30日成交104套。据广州中原研究发展部监测，2017年同期，广州新推1969套住宅中，总成交约1303套。中原地产首席分析师张大伟认为，相比2月受春节假期影响的市场低迷态势，3至4月全国主要城市成交量有所平稳，供应量明显上涨。目前大部分城市成交量环比上涨，但相比调控前，当前市场成交量依然处于低迷状态，很难说回暖。“4月下旬出现‘日光盘’现象的热门项目，或能引起购房者积极性的，还是限价项目。”张大伟说。有业内人士认为，虽然在严控下不存在市场回暖的基础，但刚需购房者近日因舆论影响入市积极性有所提高，出现部分催促入市的声音，应警惕楼市炒作与中介等代理人员过度营销引致房地产市场又一轮非理性“繁荣”。";
		Map<String, Float> keywordList = new TextRankKeyword().getTermAndRank(content, 50);
		System.out.println(keywordList);

		List<WordFrequency> words = new ArrayList<WordFrequency>();
		for (Entry<String, Float> en : keywordList.entrySet()) {
			words.add(new WordFrequency(en.getKey(), Math.round(en.getValue()*100)));
		}
		
		WordCloudUtils.buildWordCouldByWordFrequencies(400, 312, 0, 50, 10, words, new Color(0x080808), "D:\\test.png", new Color(0xABEDFF), new Color(0x00FF00), new Color(0xA020F0));
		
		
	}
}
