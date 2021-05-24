package com.rjgc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@Slf4j
public class DBUtils {
    private static final String PREFIX_LOG = "【自定义DB工具】";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public boolean executeSql(String sql) {
        log.info(PREFIX_LOG + "执行sql:" + sql);
        SqlSession session = getSqlSession();
        try (PreparedStatement pst = session.getConnection().prepareStatement(sql)) {
            int result = pst.executeUpdate();
            return result == 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeSqlSession(session);
        }
        return false;
    }

    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        log.info(PREFIX_LOG + "执行sql:" + sql);
        SqlSession session = getSqlSession();
        return session.getConnection().prepareStatement(sql);
    }

    /**
     * 获取sqlSession
     *
     * @return
     */
    private SqlSession getSqlSession() {
        return SqlSessionUtils.getSqlSession(sqlSessionTemplate.getSqlSessionFactory(),
                sqlSessionTemplate.getExecutorType(), sqlSessionTemplate.getPersistenceExceptionTranslator());
    }

    /**
     * 关闭sqlSession
     *
     * @param session
     */
    private void closeSqlSession(SqlSession session) {
        SqlSessionUtils.closeSqlSession(session, sqlSessionTemplate.getSqlSessionFactory());
    }
}
