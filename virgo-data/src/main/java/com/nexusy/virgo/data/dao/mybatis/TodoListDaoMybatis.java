package com.nexusy.virgo.data.dao.mybatis;

import com.nexusy.virgo.data.dao.TodoListDao;
import com.nexusy.virgo.data.model.TodoList;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author lan
 * @since 2014-08-05
 */
@Repository
public class TodoListDaoMybatis extends SqlSessionDaoSupport implements TodoListDao {

    @Override
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public void save(TodoList todoList) {
        getSqlSession().selectOne("com.nexusy.virgo.data.mapper.TodoListMapper.insertTodoList", todoList);
    }

    @Override
    public TodoList findTodoList(Long id) {
        return getSqlSession().selectOne("com.nexusy.virgo.data.mapper.TodoListMapper.selectTodoList", id);
    }
}
