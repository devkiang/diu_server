package com.appserver.dao;

import com.appserver.model.Goods;

import java.util.List;

public class GoodsDAOImp extends BaseDAOIpl<Goods> implements GoodsDAO{

	@Override
	public boolean addGoods(Goods goods) {
		try {
			this.save(goods);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateGoods(Goods goods) {
		try {
			this.update(goods);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteGoods(long goodsId) {
		try {
			this.delete(goodsId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Goods> getGoodsList(int page, int size) {
		String hql="from Goods as g";
		List<Goods> goodsList=(List<Goods>) this.findListByHqlAndPage(hql, page, size);
		return goodsList;
	}

	@Override
	public List<Goods> getGoodsListByHot(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> getGoodsListByRecommend(int page, int size) {
		String hql="from Goods as g";
		List<Goods> goodsList=(List<Goods>) this.findListByHqlAndPage(hql, page, size);
		return goodsList;
	}

	@Override
	public List<Goods> getGoodsListByHome() {
		
		return getGoodsList(1, 20);
	}

}
