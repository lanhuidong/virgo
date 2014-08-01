package com.nexusy.virgo.data.dao.mybatis;

import com.nexusy.virgo.data.dao.ToDoListDao;
import com.nexusy.virgo.data.model.ToDoList;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author lan
 * @since 2014-08-01
 */
@Repository
public class ToDoListDaoMybatis extends SqlSessionDaoSupport implements ToDoListDao {

    @Override
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public void save(ToDoList toDoList) {
        getSqlSession().insert("com.nexusy.virgo.data.mapper.ToDoListMapper.insertToDoList", toDoList);
    }

    @Override
    public ToDoList get(Long id) {
        return getSqlSession().selectOne("com.nexusy.virgo.data.mapper.ToDoListMapper.selectToDoList", id);
    }
}
