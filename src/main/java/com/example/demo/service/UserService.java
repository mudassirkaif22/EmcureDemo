package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UsersDao;
import com.example.demo.entity.CustomResponse;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.utilties.Constants;
import com.example.demo.utilties.ExcelFileExportUtility;
import com.example.demo.utilties.LabelUtility;
import com.example.demo.utilties.MessageUtility;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	private final static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	MessageUtility msg;
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	private UsersDao dao;
	
	public String adduser(User user) {
		 repo.save(user);
		return "User added successfully";
	}
	public JSONObject getAllUsers() {
		JSONObject jsonObject = new JSONObject();
		try {
			List<User> allUser = repo.findAll();
			JSONObject obj = new JSONObject();
			obj.put("allUser", allUser);
			jsonObject.put(Constants.RESPONSE, "success" );
			jsonObject.put(Constants.DATA, obj);
		}
		catch (Exception e) {
			logger.error("UserService.getAllUsers(): Error while getting all users " + e.getMessage());
			jsonObject.put(Constants.RESPONSE, "failed");
			jsonObject.put(Constants.MESSAGE,"Error while fetching all user");
		}
		return jsonObject ;
	}
	
	public JSONObject getUserByname(String name) {
		JSONObject jsonObject = new JSONObject();
		try {
			Object user = repo.findByName(name);
			JSONObject obj = new JSONObject();
			obj.put("user_data",user );
			jsonObject.put(Constants.RESPONSE, "success");
			jsonObject.put(Constants.DATA, obj);
		}
		catch (Exception e) {
			jsonObject.put(Constants.RESPONSE,"failed");
			jsonObject.put(Constants.MESSAGE,"Error while getting User By Name" );
		}
		return jsonObject;
	}
	public JSONObject getUserById(int Id) {
		JSONObject jsonObject = new JSONObject();
		try {
			Object user = repo.findById(Id);
			JSONObject obj = new JSONObject();
			obj.put("user_details", user);
			jsonObject.put(Constants.RESPONSE, "success");
			jsonObject.put(Constants.DATA, obj);
			
		} catch (Exception e) {
			jsonObject.put(Constants.RESPONSE, "failed");
			jsonObject.put(Constants.MESSAGE,"error while fetching user by Id");
		}
		return jsonObject;
		
	}
	public JSONObject isUserNameExist(String name) {
		JSONObject jsonObject = new JSONObject();
		try {
			if(name!=null) {
				User user = repo.findByName(name);
				if(user!=null) {
					jsonObject.put(Constants.MESSAGE, "Username Exist");
				}
				else {
					jsonObject.put(Constants.MESSAGE, "Username does not Exist");
				}
				jsonObject.put(Constants.RESPONSE, msg.getText("success.msg"));
			}
			
		} catch (Exception e) {
			throw e;
		}
		return jsonObject;
	}
	
	
	public boolean isUserNameExist(Integer Id) {
		return repo.existsById(Id);
	}
	public User getUserById(Integer Id) {
		return repo.getOne(Id);
	}
	
	public static boolean isNullOrEmpty(String toTest) {
        return (toTest == null || toTest.isEmpty());
    }
	
	public JSONObject updateUser(Integer Id, User user) {
		JSONObject jsonObject = new JSONObject();
		try {
			if(isUserNameExist(Id)) {
				User userUpdate = getUserById(Id);
					if(!StringUtils.isEmpty(user.getName())) {
					userUpdate.setName(user.getName());
					}
					if(!StringUtils.isEmpty(user.getPassword())) {
						userUpdate.setPassword(user.getPassword());
					}
					if(!StringUtils.isEmpty(user.getRole())) {
						userUpdate.setRole(user.getRole());
					}
					User updateUser = repo.save(userUpdate);
					if(updateUser !=null) {
						jsonObject.put(Constants.RESPONSE, "success");
						jsonObject.put(Constants.MESSAGE, "user updated successfully");
						JSONObject obj = new JSONObject();
						obj.put("user_name", userUpdate.getName());
						jsonObject.put(Constants.DATA, obj);
					}
			}
			else {
				jsonObject.put(Constants.RESPONSE, "failed");
				jsonObject.put(Constants.MESSAGE, "Invalid user");
			}
		} catch (Exception e) {
			logger.error("UserService.updateUser():: Error while updating the user", e.getMessage());
			jsonObject.put(Constants.MESSAGE, "Error while updating the user");
	}
		return jsonObject;
}
//	public JSONObject downloadUserList(boolean exportFlag,boolean paginationFlag,Integer pageNo,Integer pageSize) {
//		JSONObject jsonObject = new JSONObject();
//		LinkedHashMap map = getUserId();
//		try {
//			JSONArray arrForAppend;
//			JSONObject objForAppend;
//			Map<String, Object> dataMap = this.dao.downlaodUserList(exportFlag, paginationFlag, pageNo, pageSize);
//			List<Object> userList = (List<Object>) dataMap.get("user_List");
//			Iterator<Object> itr = userList.iterator();
//			for (arrForAppend = new JSONArray(); itr.hasNext(); arrForAppend.add(objForAppend)) {
//				Object[] object = (Object[]) itr.next();
//				objForAppend = new JSONObject();
//				objForAppend.put("user_name", object[0].toString());
//				objForAppend.put("user_role", object[1].toString());
//				objForAppend.put("user_password", object[2].toString());
//				if(arrForAppend != null && exportFlag) {
//					String sheetName = Constants.USER_EXCEL_SHEET_NAME;
//					Map<String, String> labelMap = LabelUtility.getLabelsForUserExportFile();
//					JSONObject json = new JSONObject();
//					json.put(Constants.DATA,arrForAppend);
//					json.toString();
//					ByteArrayInputStream stream = ExcelFileExportUtility.generateExcelFile(sheetName, labelMap, json);
//				}
//			}
//		}
//		catch (Exception e ) {
//			throw e;
//		}
//		return jsonObject;
//	}
	public LinkedHashMap<Integer, String> getUserId() {
		LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
		List<User> roles = repo.findAll();
		if (roles.size() > 0) {
			roles.forEach(obj -> {
				map.put(obj.getId(), obj.getName());
			});
		}
		httpSession.setAttribute(Constants.USER_ID_WITH_ROLE_MAP, map);
		return map;
	}
	
}
