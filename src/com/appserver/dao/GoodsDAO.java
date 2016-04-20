package com.appserver.dao;

import com.appserver.model.Goods;

import java.util.List;


public interface GoodsDAO extends BaseDAO<Goods>{
	public boolean addGoods(Goods goods);
	public boolean updateGoods(Goods goods);
	public boolean deleteGoods(long goodsId);
	public List<Goods> getGoodsList(int page, int size);
	public List<Goods> getGoodsListByHot(int page, int size);
	public List<Goods> getGoodsListByRecommend(int page, int size);
	public List<Goods> getGoodsListByHome();
}
