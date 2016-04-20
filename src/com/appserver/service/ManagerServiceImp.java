package com.appserver.service;

import com.appserver.dao.GoodsDAO;
import com.appserver.dao.MenuDAO;
import com.appserver.model.Goods;
import com.appserver.model.Menu;
import com.appserver.toolkit.Config;
import com.appserver.toolkit.ResponseInfo;
import com.appserver.toolkit.Model;
import com.appserver.toolkit.VerifyUtil;

import java.util.List;


public class ManagerServiceImp implements ManagerService{

	
	private GoodsDAO goodsDAO;
	private MenuDAO menuDAO;
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}
	
	
	
	
	
	
	
	@Override
	public ResponseInfo addGoods(Goods goods)
	{
		ResponseInfo error=new ResponseInfo();
		if(goods.getImageUrls()==null||goods.getImageUrls().length<1){
			error.setCode(Config.errorCode_Service);
			error.setDescription("至少上传一张图片");
			return error;
		}else if(!VerifyUtil.Stringverify(goods.getName(), 3, 50, Model.DEFAULT)){
			error.setCode(Config.errorCode_Service);
			error.setDescription("商品名包含非法字符");
			return error;
		}else if(Double.parseDouble(goods.getPrice())<=0){
			error.setCode(Config.errorCode_Service);
			error.setDescription("请输入正确的商品价格");
			return error;
		}
		
		boolean result=goodsDAO.addGoods(goods);
		if(result!=true){
			error.setDescription("添加错误");
			error.setCode(Config.errorCode_Service);
		}else{
			error.setCode(Config.SuccessCode_Service);
		}
		return error;
	}
	@Override
	public ResponseInfo updateGoods(Goods goods) {
		ResponseInfo error=new ResponseInfo();
		if(goods.getImageUrls()==null||goods.getImageUrls().length<1){
			error.setCode(Config.errorCode_Service);
			error.setDescription("至少上传一张图片");
			return error;
		}else if(!VerifyUtil.Stringverify(goods.getName(), 3, 50, Model.DEFAULT)){
			error.setCode(Config.errorCode_Service);
			error.setDescription("商品名包含非法字符");
			return error;
		}else if(Double.parseDouble(goods.getPrice())<=0){
			error.setCode(Config.errorCode_Service);
			error.setDescription("请输入正确的商品价格");
			return error;
		}else if(goods.getGoodsID()<1){
			error.setCode(Config.errorCode_Service);
			error.setDescription("非法请求");
			return error;
		}
		
		boolean result=goodsDAO.updateGoods(goods);
		if(result!=true){
			error.setDescription("修改错误");
			error.setCode(Config.errorCode_Service);
		}else{
			error.setCode(Config.SuccessCode_Service);
		}
		return error;
	}
	
	
	@Override
	public ResponseInfo addMenu(Menu menu) {
		ResponseInfo error=new ResponseInfo();
		if(VerifyUtil.StringIsEmpty(menu.getTitle())){
			error.setDescription("至少传入一个标题");
			error.setCode(Config.errorCode_Service);
			return error;
		}else if(VerifyUtil.StringIsEmpty(menu.getImageUrl())){
			error.setDescription("必须传入菜单图标URL地址");
			error.setCode(Config.errorCode_Service);
			return error;
		}
		try {
			menuDAO.save(menu);
		} catch (Exception e) {
			e.printStackTrace();
			error.setDescription("服务器异常");
			error.setCode(Config.errorCode_Service);
			return error;
		}
		error.setCode(Config.SuccessCode_Service);
		return error;
	}
	@Override
	public ResponseInfo updateMenu(Menu menu) {
		ResponseInfo error=new ResponseInfo();
		if(menu.getMid()<1){
			error.setDescription("非法请求");
			error.setCode(Config.errorCode_Service);
			return error;
		}else if(VerifyUtil.StringIsEmpty(menu.getTitle())){
			error.setDescription("至少传入一个标题");
			error.setCode(Config.errorCode_Service);
			return error;
		}else if(VerifyUtil.StringIsEmpty(menu.getImageUrl())){
			error.setDescription("必须传入菜单图标URL地址");
			error.setCode(Config.errorCode_Service);
			return error;
		}
		try {
			menuDAO.update(menu);
		} catch (Exception e) {
			e.printStackTrace();
			error.setDescription("服务器异常");
			error.setCode(Config.errorCode_Service);
			return error;
		}
		error.setCode(Config.SuccessCode_Service);
		return error;
	}
	@Override
	public ResponseInfo deleteMenu(long mId) {
		ResponseInfo error=new ResponseInfo();
		if(mId<1){
			error.setDescription("非法请求");
			error.setCode(Config.errorCode_Service);
			return error;
		}
		
		try {
			menuDAO.delete(mId);
		} catch (Exception e) {
			e.printStackTrace();
			error.setDescription("服务器异常");
			error.setCode(Config.errorCode_Service);
			return error;
		}
		error.setCode(Config.SuccessCode_Service);
		return error;
	}
	
	
	@Override
	public ResponseInfo getMenus() {
		ResponseInfo error=new ResponseInfo();
		List<Menu> menus=menuDAO.getMenusList();
		error.setCode(Config.SuccessCode_Service);
		error.setReturnData(menus);
		return error;
	}
}
