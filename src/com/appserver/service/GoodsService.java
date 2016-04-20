package com.appserver.service;


import com.appserver.toolkit.ResponseInfo;

public interface GoodsService {
	public enum GoodsListType {  
	    GoodsListTypeHot, //热门
	    GoodsListTypeNew,//新品
	    GoodsListTypeAll,//全部
	    GoodsListTypeRecommend//推荐
	};  
	
	public ResponseInfo getGoodsDetil(Object goodsId);
	
	public ResponseInfo getGoodsListWithType(GoodsListType type, int page);
}
