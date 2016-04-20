package com.appserver.dao;

import com.appserver.model.SystemModel;

/**
 * Created by shikee_app03 on 16/4/20.
 */
public interface AppSystemDataDAO<T> extends BaseDAO<SystemModel>{
    public SystemModel getCurrAppSystemModel();
    public boolean addAppSystemData(SystemModel model);
}
