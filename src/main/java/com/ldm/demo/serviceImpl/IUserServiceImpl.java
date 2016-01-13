package com.ldm.demo.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldm.demo.beans.User;
import com.ldm.demo.dao.UserMapper;
import com.ldm.demo.service.IUserService;

@Service
public class IUserServiceImpl implements IUserService {
	
	@Resource
	private UserMapper userDao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		userDao.deleteByPrimaryKey(record.getId());
//		String a = null ;
//		boolean b = a.length()>0;
		userDao.insert(record);
		return 0 ;
	}
	
	

	@Override
	public int insertSelective(User record) {
		
		// TODO Auto-generated method stub
		return 0;
		
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		
		// TODO Auto-generated method stub
		return 0;
		
	}

	@Override
	public int updateByPrimaryKey(User record) {
		
		// TODO Auto-generated method stub
		return 0;
		
	}
	
	

}
