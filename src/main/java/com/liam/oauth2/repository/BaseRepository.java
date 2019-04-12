package com.liam.oauth2.repository;

import com.liam.oauth2.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author: liangzy
 * @date: 2019/03/15 上午11:43
 * @desc:
 */

public abstract class BaseRepository<T> extends BaseJdbcDaoSupport {
    private final String SQL = "SELECT * FROM " + getTableName() + " WHERE 1=1 ";

    @Autowired
    public BaseRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public abstract String getTableName();

    /**
     * @author: liangzy
     * @date: 19-4-11 下午2:27
     * @param: [t]
     * @return: boolean
     * @desc: 保存
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean save(T t) {
        boolean flag = false;
        BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(t);
        String sql = buildSqlInsert(sqlParameterSource, getTableName());
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
    public Integer saveRespId(T t) {
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
    public boolean update(T t, Integer id) {
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
    public boolean delete(Integer id) {
        String sql = "DELETE FROM " + getTableName() + " WHERE ID = " + id;
        int ret = getJdbcTemplate().update(sql);
        return ret > 0;
    }


    public T find(Integer id) {
        String sql = SQL + "  AND id =" + id;
        List<T> list = getNamedParameterJdbcTemplate().query(sql, new BeanPropertyRowMapper());
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }


    public T find(String key, String value) {
        String sql = SQL + " and " + key + "=" + value;
        List<T> list = getNamedParameterJdbcTemplate().query(sql, new BeanPropertyRowMapper(getEntityClass()));
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }


    public List<T> list() {
        return getNamedParameterJdbcTemplate().query(SQL, new BeanPropertyRowMapper(getEntityClass()));
    }

    public List<T> list(String key, String value) {
        String sql = SQL + " and " + key + "=" + value;
        return getNamedParameterJdbcTemplate().query(sql, new BeanPropertyRowMapper(getEntityClass()));
    }

}
