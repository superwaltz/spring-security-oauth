package com.liam.oauth2.repository;

import com.liam.oauth2.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @author: liangzy
 * @date: 2019/03/15 上午11:43
 * @desc:
 */

public abstract class BaseRepository extends BaseJdbcDaoSupport {

    @Autowired
    public BaseRepository (DataSource dataSource) {
        setDataSource(dataSource);
    }

    public abstract String getTableName();

    @Transactional(rollbackFor = Exception.class)
    public <T> boolean save(T t){
        boolean flag = false;
        BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(t);
        String sql = buildSqlInsert(sqlParameterSource,getTableName());
        if (StringUtil.isNotEmpty(sql)) {
            int ret = getNamedParameterJdbcTemplate().update(sql, sqlParameterSource);
            flag = ret > 0;
        }
        return flag;
    }

    /**
     * @author: liangzy
     * @date: 18-11-19 上午11:03
     * @param: [T]
     * @return: java.lang.Integer
     * @desc: 保存并返回id
     */
    @Transactional(rollbackFor = Exception.class)
    public <T> Integer saveRespId(T t) {
        Integer id = null;
        BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(t);
        String sqlInsert = buildSqlInsert(sqlParameterSource, getTableName());
        if (StringUtil.isNotEmpty(sqlInsert)) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            getNamedParameterJdbcTemplate().update(sqlInsert, sqlParameterSource, keyHolder);
            id = keyHolder.getKey().intValue();
        }
        return id;
    }


    @Transactional(rollbackFor = Exception.class)
    public <T> boolean update(T t,Integer id) {
        BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(t);
        String sql = buildSqlUpdate(sqlParameterSource, getTableName(), null) + " where id = " + id;
        boolean flag = false;
        if (StringUtil.isNotEmpty(sql)) {
            int ret = getNamedParameterJdbcTemplate().update(sql, sqlParameterSource);
            flag = ret > 0;
        }
        return flag;
    }

    /**
     * @author: liangzy
     * @date: 18-11-21 下午2:20
     * @param: [id, tableName]
     * @return: boolean
     * @desc: 根据id删除
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer id){
        boolean flag;
        String sql = "DELETE FROM "+getTableName()+" WHERE ID = "+id;
        int ret = getJdbcTemplate().update(sql);
        flag = ret > 0;
        return flag;
    }

}
