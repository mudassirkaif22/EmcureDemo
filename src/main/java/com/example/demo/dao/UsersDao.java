package com.example.demo.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

import jakarta.transaction.Transactional;
import jakarta.websocket.Session;

@Repository("usersDa0")
@Transactional

public class UsersDao {
	@Autowired
    private SessionFactory sessionFactory;
//		public Map<String,String> downloadUserList(boolean exportFlag, boolean paginationFlag, Integer pageNo, Integer pageSize){
//			Session session = null;
//	        Map<String, Object> map = new LinkedHashMap<>();
//	        List<User> userList = new ArrayList<>();
//	        session =sessionFactory.openSession();
//	        
//	  
//	}
}
