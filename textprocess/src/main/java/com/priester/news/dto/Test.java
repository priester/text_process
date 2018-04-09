package com.priester.news.dto;

import com.priester.utils.StripHtmlUtil;

public class Test {
public static void main(String[] args) {
	String txt = "              .ct_hqimg {margin: 10px 0;} .hqimg_wrapper {text-align: center;} .hqimg_related {position: relative; height: 37px; overflow: hidden; background-color: #f6f6f6; text-align: center; font-size: 0; } .hqimg_related span {line-height: 37px; padding-left: 10px; color: #000; font-size: 18px; } .hqimg_related a {line-height: 37px; font-size: 15px; color: #000; } .hqimg_related .to_page {float: left; } .hqimg_related .to_page a {padding-left: 28px; } .hqimg_related .hotSe {display: inline-block; *display: inline; *zoom: 1; width: 11px; height: 11px; padding-top: 8px; background: url(//n.sinaimg.cn/780c44e8/20150702/hqimg_hot.gif) no-repeat; } .hqimg_related .hqimg_client {position: absolute; right: 25px; top: 0; padding-left: 18px; }                        热点栏目          自选股     数据中心     行情中心     资金流向     模拟交易        客户端         国有控股磷矿企业将借壳群兴玩具群兴玩具4月2日晚公告，继续推进控股权转让和重大资产重组事项。本次股权转让的拟受让方系国有控股企业，主要从事磷矿开采、加工，受让方拟受让群兴投资持有的公司股份1.4亿股，占群兴投资持有公司股份的53.02%，占公司总股本的23.78%。如股权转让获得实施，受让方将成为公司控股股东，国有资产管理部门将成为公司实际控制人。公告显示，公司拟向受让方发行股份购买资产，标的资产预估值70亿-90亿元，股票发行价格不超过每股8.05元。如该重大资产重组实施，受让方将完成借壳重组上市。公司股票4月3日起复牌。               ";
	System.out.println(StripHtmlUtil.stripHtml(txt));
	}
}
