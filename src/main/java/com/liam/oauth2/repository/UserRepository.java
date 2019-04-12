package com.liam.oauth2.repository;

import com.liam.oauth2.entity.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * @author: liangzy
 * @date: 2019/03/15 上午11:47
 * @desc:
 */
@Repository
public class UserRepository extends BaseRepository<User> {

    private static String tableName ="u_user";

    public UserRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

}
