package com.icerno.frame.dao;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 操作数据库基类
 *
 * @author HWL
 */
@Repository("baseDao")
public class BaseDao {
    /** 不自动提交 */
    private static final Boolean AUTO_COMMIT_FALSE = false;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 根据条件查询数据（单条）
     *
     * @param sqlId SQL ID
     * @param obj 查询条件
     * @return 查询结果
     * @throws Exception 异常
     */
    public Object findForOne(String sqlId, Object obj) throws Exception {
        return sqlSessionTemplate.selectOne(sqlId, obj);
    }

    /**
     * 根据条件查询数据（列表）
     * @param sqlId SQL ID
     * @param obj 查询条件
     * @return 查询结果
     * @throws Exception 异常
     */
    public Object findForList(String sqlId, Object obj) throws Exception {
        return sqlSessionTemplate.selectList(sqlId, obj);
    }

    /**
     * 根据条件查询数据，返回Map
     *
     * @param sqlId SQL ID
     * @param obj 查询条件
     * @param key 键
     * @return 查询结果
     * @throws Exception 异常
     */
    public Map<String, Object> findForMap(String sqlId, Object obj, String key) throws Exception {
        return sqlSessionTemplate.selectMap(sqlId, obj, key);
    }

    /**
     * 新增数据
     *
     * @param sqlId SQL ID
     * @param obj 新增信息
     * @return 新增结果
     * @throws Exception 异常
     */
    public int insert(String sqlId, Object obj) throws Exception {
        return sqlSessionTemplate.insert(sqlId, obj);
    }

    /**
     * 更新数据
     *
     * @param sqlId SQL ID
     * @param obj 更新信息
     * @return 更新结果
     * @throws Exception 异常
     */
    public int update(String sqlId, Object obj) throws Exception {
        return sqlSessionTemplate.update(sqlId, obj);
    }

    /**
     * 更新数据
     *
     * @param sqlId SQL ID
     * @param obj 更新信息
     * @return 更新结果
     * @throws Exception 异常
     */
    public int delete(String sqlId, Object obj) throws Exception {
        return sqlSessionTemplate.delete(sqlId, obj);
    }

    /**
     * 批量新增数据
     *
     * @param sqlId SQL ID
     * @param objs 新增信息（列表）
     * @return 处理结果
     * @throws Exception 异常
     */
    public int batchInsert(String sqlId, List<?> objs) throws Exception {
        int result = 0;

        if (objs != null && objs.size() > 0) {
            SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, AUTO_COMMIT_FALSE);
            try {
                for (int i = 0, size = objs.size(); i < size; i++) {
                    sqlSession.insert(sqlId, objs.get(i));
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
                result = objs.size();
            } finally {
                sqlSession.close();
            }
        }

        return result;
    }

    /**
     * 批量更新数据
     *
     * @param sqlId SQL ID
     * @param objs 更新信息（列表）
     * @return 处理结果
     * @throws Exception 异常
     */
    public int batchUpdate(String sqlId, List<?> objs) throws Exception {
        int result = 0;

        if (objs != null && objs.size() > 0) {
            SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, AUTO_COMMIT_FALSE);

            try {
                for (int i = 0, size = objs.size(); i < size; i++) {
                    sqlSession.update(sqlId, objs.get(i));
                }

                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();

                result = objs.size();
            } finally {
                sqlSession.close();
            }
        }

        return result;
    }

    /**
     * 批量删除数据
     *
     * @param sqlId SQL ID
     * @param objs 删除信息（列表）
     * @return 处理结果
     * @throws Exception 异常
     */
    public int batchDelete(String sqlId, List<?> objs) throws Exception {
        int result = 0;

        if (objs != null && objs.size() > 0) {
            SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, AUTO_COMMIT_FALSE);

            for (int i = 0, size = objs.size(); i < size; i++) {
                sqlSession.delete(sqlId, objs.get(i));
            }

            sqlSession.flushStatements();
            sqlSession.commit();
            sqlSession.clearCache();

            result = objs.size();
        }

        return result;
    }

}
