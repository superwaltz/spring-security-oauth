package com.liam.oauth2.service;


import com.liam.oauth2.repository.BaseRepository;

/**
 * @author: liangzy
 * @date: 2019/03/15 下午3:17
 * @desc:
 */
public abstract class BaseService<T>  {

    public abstract BaseRepository getRepository();

    public boolean save(T t){
        return getRepository().save(t);
    }

    public Integer saveRespId(T t) {
        return getRepository().saveRespId(t);
    }

    public boolean update(T t,Integer id) {
        return getRepository().update(t,id);
    }

    public boolean delete(Integer id){
        return getRepository().delete(id);
    }

}
