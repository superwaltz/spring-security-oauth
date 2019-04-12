package com.liam.oauth2.repository;

import com.liam.oauth2.entity.Client;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * @author: liangzy
 * @date: 2019/03/15 上午11:47
 * @desc:
 */
@Repository
public class ClientRepository extends BaseRepository<Client> {

    private static String tableName ="oauth_client_details";

    public ClientRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

}
