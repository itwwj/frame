package com.gitee.frame.bootmybatis.plugin;

import com.gitee.frame.bootmybatis.entity.StatusEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 可以在bean中配置全局枚举处理插件 也可以在mapper映射文件中指定插件比如：
 *  插入：#{xxxx,typeHandler=com.gitee.frame.bootmybatis.plugin.EnumPlugin}
 *  查询：在resultMap中指定
 *  <result column="xxx" property="xxxxx" typeHandler="com.gitee.frame.bootmybatis.plugin.EnumPlugin"/>
 *
 *
 * @author jie
 */
public class EnumPlugin implements TypeHandler<StatusEnum> {

    /**
     * 定义当前数据如何保存到数据库
     *
     * @param preparedStatement
     * @param i
     * @param statusEnum
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, StatusEnum statusEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, statusEnum.getStatus().toString());
    }

    /**
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public StatusEnum getResult(ResultSet resultSet, String s) throws SQLException {

        int status = resultSet.getInt(s);
        return StatusEnum.getStatus(status);
    }

    /**
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public StatusEnum getResult(ResultSet resultSet, int i) throws SQLException {
        int status = resultSet.getInt(i);
        return StatusEnum.getStatus(status);
    }

    /**
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public StatusEnum getResult(CallableStatement callableStatement, int i) throws SQLException {
        int status = callableStatement.getInt(i);
        return StatusEnum.getStatus(status);
    }
}
