package com.liam.oauth2.repository;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.JdbcUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BaseJdbcDaoSupport extends NamedParameterJdbcDaoSupport {

    public <T> BeanPropertySqlParameterSource[] getBeanPropertySqlParameterSources(List<T> list) {
        BeanPropertySqlParameterSource[] sqlParameterSources = new BeanPropertySqlParameterSource[list.size()];
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            sqlParameterSources[i] = new BeanPropertySqlParameterSource(t);
        }
        return sqlParameterSources;
    }

    public String buildSqlWhere(Map<String, Object> map) {
        return buildSqlWhere(map, new String[0]);
    }

    public String buildSqlWhere(Map<String, Object> map, String[] ignore) {
        List<String> ignoreList = getIgnoreList(ignore);

        StringBuilder stringBuilder = new StringBuilder();

        for (String paramName : map.keySet()) {
            if (!ignoreList.contains(paramName)) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(" AND ").append(paramName).append("=:").append(paramName);
                } else {
                    stringBuilder.append(" WHERE ").append(paramName).append("=:").append(paramName);
                }
            }
        }

        return stringBuilder.toString();
    }

    public String buildSqlWhere(BeanPropertySqlParameterSource sqlParameterSource) {
        return buildSqlWhere(sqlParameterSource, new String[0]);
    }

    public String buildSqlWhere(BeanPropertySqlParameterSource sqlParameterSource, String[] ignore) {
        List<String> ignoreList = Arrays.asList(ignore);

        StringBuilder stringBuilder = new StringBuilder();
        String[] paraNames = sqlParameterSource.getReadablePropertyNames();
        for (String paramName : paraNames) {
            Object value = sqlParameterSource.getValue(paramName);

            if (value != null && !paramName.equals("class") && !ignoreList.contains(paramName)) {
                String strValue = "" + value;
                if (!strValue.equals("")) {
                    if (stringBuilder.length() > 0) {
                        stringBuilder.append(" AND ").append(paramName).append("=:").append(paramName);
                    } else {
                        stringBuilder.append(" WHERE ").append(paramName).append("=:").append(paramName);
                    }
                }
            }
        }

        return stringBuilder.toString();
    }


    public String buildSqlInsert(Map<String, Object> map, String tableName) {
        return buildSqlInsert(map, tableName, new String[0]);
    }


    public String buildSqlInsert(Map<String, Object> map, String tableName, String[] ignore) {
        List<String> ignoreList = getIgnoreList(ignore);

        StringBuilder sqlBuilder = new StringBuilder().append("INSERT INTO ").append(tableName);
        StringBuilder nameBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();

        for (String paramName : map.keySet()) {
            if (!ignoreList.contains(paramName)) {
                buildInsertPair(nameBuilder, valueBuilder, paramName);
            }
        }

        if (nameBuilder.length() > 0) {
            nameBuilder.append(")");
            valueBuilder.append(")");
        }

        return sqlBuilder.append(nameBuilder.toString()).append(" VALUES")
                .append(valueBuilder.toString()).toString();
    }

    public String buildSqlInsert(BeanPropertySqlParameterSource sqlParameterSource, String tableName) {
        return buildSqlInsert(sqlParameterSource, tableName, new String[0]);
    }

    public String buildSqlInsert(BeanPropertySqlParameterSource sqlParameterSource, String tableName, String[] ignore) {
        List<String> ignoreList = getIgnoreList(ignore);

        StringBuilder sqlBuilder = new StringBuilder().append("INSERT INTO ").append(tableName);
        StringBuilder nameBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();

        String[] paraNames = sqlParameterSource.getReadablePropertyNames();
        for (String paramName : paraNames) {
            if (ignoreList.contains(paramName)) continue;

            Object value = sqlParameterSource.getValue(paramName);
            int type = sqlParameterSource.getSqlType(paramName);

            if (value != null && type != JdbcUtils.TYPE_UNKNOWN) {
                String strValue = "" + value;
                if (!strValue.equals("")) {
                    buildInsertPair(nameBuilder, valueBuilder, paramName);
                }
            }
        }

        if (nameBuilder.length() > 0) {
            nameBuilder.append(")");
            valueBuilder.append(")");
        }

        return sqlBuilder.append(nameBuilder.toString()).append(" VALUES")
                .append(valueBuilder.toString()).toString();
    }

    public String buildSqlUpdate(Map<String, Object> map, String tableName) {
        return buildSqlUpdate(map, tableName, new String[0]);
    }

    public String buildSqlUpdate(Map<String, Object> map, String tableName, String[] ignore) {
        List<String> ignoreList = getIgnoreList(ignore);

        StringBuilder sqlBuilder = new StringBuilder().append("UPDATE ").append(tableName).append(" SET");
        StringBuilder stringBuilder = new StringBuilder();

        for (String paramName : map.keySet()) {
            if (ignoreList.contains(paramName)) continue;
            buildUpdatePair(stringBuilder, paramName, map.get(paramName));
        }

        return sqlBuilder.append(stringBuilder.toString()).toString();
    }

    public String buildSqlUpdate(BeanPropertySqlParameterSource sqlParameterSource, String tableName) {
        return buildSqlUpdate(sqlParameterSource, tableName, new String[0]);
    }

    public String buildSqlUpdate(BeanPropertySqlParameterSource sqlParameterSource, String tableName, String[] ignore) {
        List<String> ignoreList = getIgnoreList(ignore);

        StringBuilder sqlBuilder = new StringBuilder().append("UPDATE ").append(tableName).append(" SET");
        StringBuilder stringBuilder = new StringBuilder();

        String[] paraNames = sqlParameterSource.getReadablePropertyNames();
        for (String paramName : paraNames) {
            if (ignoreList.contains(paramName)) continue;

            Object value = sqlParameterSource.getValue(paramName);
            int type = sqlParameterSource.getSqlType(paramName);

            if (value != null && type != JdbcUtils.TYPE_UNKNOWN) {
                buildUpdatePair(stringBuilder, paramName, value);
            }
        }


        return sqlBuilder.append(stringBuilder.toString()).toString();
    }

    public <T> T requiredSingleResult(List<T> results) {
        if (results.size() > 0) {
            return results.get(0);
        }

        return null;
    }

    private List<String> getIgnoreList(String[] ignore) {
        if (ignore == null) ignore = new String[0];
        return Arrays.asList(ignore);
    }

    private void buildInsertPair(StringBuilder nameBuilder, StringBuilder valueBuilder, String paramName) {
        if (nameBuilder.length() > 0) {
            nameBuilder.append(", ").append(paramName);
            valueBuilder.append(", :").append(paramName);
        } else {
            nameBuilder.append("(").append(paramName);
            valueBuilder.append("(:").append(paramName);
        }
    }

    private void buildUpdatePair(StringBuilder stringBuilder, String paramName, Object value) {
        String strValue = "" + value;
        if (!strValue.equals("")) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(" , ").append(paramName).append("=:").append(paramName);
            } else {
                stringBuilder.append(" ").append(paramName).append("=:").append(paramName);
            }
        }
    }

    public String formatString(String s) {
        return String.format("'%s'", s);
    }

    public String formatLike(String s) {
        return "%" + s + "%";
    }
}