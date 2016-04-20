package com.appserver.service;

import com.appserver.dao.AppSystemDataDAO;
import com.appserver.dao.GoodsDAO;
import com.appserver.dao.MenuDAO;
import com.appserver.model.Goods;
import com.appserver.model.Menu;
import com.appserver.model.SystemModel;
import com.appserver.toolkit.Config;
import com.appserver.toolkit.ResponseInfo;
import com.appserver.vo.GoodsBrief;

import java.util.ArrayList;
import java.util.List;


public class HomeServiceImp implements HomeService{

	private GoodsDAO goodsDAO;
	private MenuDAO menuDAO;
    private AppSystemDataDAO appDAO;

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}
    public void setAppDAO(AppSystemDataDAO appDAO) {
        this.appDAO = appDAO;
    }

	
	/////////////////////////////////////////////
    public HomeServiceImp(){
        System.out.println("fuck!"+this.getClass()+"init success!");
    }
	@Override
	public ResponseInfo getGoodsList() {
		ResponseInfo info=new ResponseInfo();
		List<Goods> goods=null;
		try {
			goods=goodsDAO.getGoodsListByHome();
			List<GoodsBrief> gbs=new ArrayList<GoodsBrief>();
			for (Goods oldGoods : goods) {
				GoodsBrief newGoods=new GoodsBrief();
				newGoods.setGid(oldGoods.getGoodsID());
				newGoods.setImageUrl(oldGoods.getImageUrls()[0] );
				newGoods.setPrice(oldGoods.getPrice());
				newGoods.setReturnPrice(oldGoods.getReturnPrice());
				newGoods.setTitle(oldGoods.getName());
				newGoods.setVipPrice(oldGoods.getVipPrice());
				gbs.add(newGoods);
			}
			info.setCode(Config.SuccessCode_Service);
			info.setReturnData(gbs);
		} catch (Exception e) {
			e.printStackTrace();
			info.setCode(Config.errorCode_Service);
			info.setDescription("服务器异常");
		}
		return info;
	}
	
	
	@Override
	public ResponseInfo getMenus() {
		ResponseInfo info=new ResponseInfo();
		List<Menu> menus=null;
		try {
			menus=menuDAO.getMenusList();
			info.setCode(Config.SuccessCode_Service);
			info.setReturnData(menus);
		} catch (Exception e) {
			e.printStackTrace();
			info.setCode(Config.errorCode_Service);
			info.setDescription("数据库异常");
		}
		return info;
	}

    @Override
    public ResponseInfo getAppData() {
        ResponseInfo info=new ResponseInfo();
        SystemModel result=new SystemModel();
        try {
            result=appDAO.getCurrAppSystemModel();
            info.setReturnData(result);
        }catch (Exception e){
            e.printStackTrace();
            info.setCode(Config.errorCode_Service);
            info.setDescription("服务器异常");
        }
        return info;
    }


}
