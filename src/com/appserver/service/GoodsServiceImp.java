package com.appserver.service;

import com.appserver.dao.GoodsDAO;
import com.appserver.model.Goods;
import com.appserver.toolkit.Config;
import com.appserver.toolkit.ResponseInfo;
import com.appserver.toolkit.VerifyUtil;

import java.util.List;


public class GoodsServiceImp implements GoodsService{

	private GoodsDAO goodsDAO;
	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}

	@Override
	public ResponseInfo getGoodsDetil(Object goodsId) {
		ResponseInfo error=new ResponseInfo();
		if(VerifyUtil.StringIsEmpty((String)goodsId)){
			error.setCode(Config.errorCode_Service);
			error.setDescription("商品ID不能为空");
			return error;
		}
		Long lgId;
		try {
			lgId = Long.parseLong((String)goodsId);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			error.setCode(Config.errorCode_Service);
			error.setDescription("传来错误的商品ID");
			return error;
		}
		Goods g=null;
		try {
			g=goodsDAO.findById(lgId);
			error.setCode(Config.SuccessCode_Service);
			error.setReturnData(g);
		} catch (Exception e) {
			e.printStackTrace();
			error.setCode(Config.errorCode_Service);
			error.setDescription("获取出错");
		}
		return error;
	}

	@Override
	public ResponseInfo getGoodsListWithType(GoodsListType type, int page) {
		int size=20;
		ResponseInfo error=new ResponseInfo();
		if(type==null){
			error.setCode(Config.errorCode_Service);
			error.setDescription("列表类型不能为空");
			return error;
		}
		List<Goods> result=null;
		try {
			if(type==GoodsListType.GoodsListTypeAll){
				result=goodsDAO.getGoodsList(page, size);
			}else if(type==GoodsListType.GoodsListTypeHot){
				result=goodsDAO.getGoodsListByHot(page, size);
			}else if(type==GoodsListType.GoodsListTypeNew){
				result=goodsDAO.getGoodsList(page, size);
			}else if(type==GoodsListType.GoodsListTypeRecommend){
				result=goodsDAO.getGoodsListByRecommend(page, size);
			}
		} catch (Exception e) {
			e.printStackTrace();
			error.setCode(Config.errorCode_Service);
			error.setReturnData("数据获取失败");
			return error;
		}
		
		error.setCode(Config.SuccessCode_Service);
		error.setReturnData(result);
		return error;
	}

}
