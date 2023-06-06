package com.ustc.oep.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YuJianhua
 * @create 2023-06-01 15:35
 */
public class ListToStringTypeHandler extends BaseTypeHandler<List<String>> {
    //将List<String>转换为String存入数据库
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        String text = parameter.stream().collect(Collectors.joining(","));
        ps.setString(i, text);
    }

    //将数据库中的字段转换为List<String>
    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String text = rs.getString(columnName);
        return convertToList(text);
    }

    //将数据库中的字段转换为List<String>
    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String text = rs.getString(columnIndex);
        return convertToList(text);
    }

    //将数据库中的字段转换为List<String>
    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String text = cs.getString(columnIndex);
        return convertToList(text);
    }

    private List<String> convertToList(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        return Arrays.asList(text.split(","));
    }
}
