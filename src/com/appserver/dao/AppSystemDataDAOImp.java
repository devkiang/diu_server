package com.appserver.dao;

import com.appserver.model.SystemModel;

/**
 * Created by shikee_app03 on 16/4/20.
 */
public class AppSystemDataDAOImp<SYstemModel> extends  BaseDAOIpl<SystemModel> implements AppSystemDataDAO<SystemModel>{

    @Override
    public SystemModel getCurrAppSystemModel() {
        SystemModel result= (SystemModel) this.findByHQL("select top 1 from table order by id");
        return result;
    }

    @Override
    public boolean addAppSystemData(SystemModel model) {
        return false;
    }

}
