package com.example.demo.controller;


import org.dozer.DozerBeanMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CustomResponse;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;
import com.example.demo.utilties.Constants;

import ch.qos.logback.core.status.Status;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@PostMapping("/register")
	public String addNewUser(@RequestBody User user) {
		return service.adduser(user);
	}
	
//	@PostMapping("/login")
//	public String login(@RequestBody ) {
//		try {
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
//	@PostMapping("/editUser")
//	public ResponseEntity<?> editUser(@RequestParam("user_id") Integer Id){
//		JSONObject jsonObject = new JSONObject();
//		HttpStatus status = HttpStatus.OK;
//		try {
//			
//		}
//	}

	@GetMapping("/getAllUser")
	public ResponseEntity<?> getAllUser(){
		JSONObject jsonObject = new JSONObject();
		HttpStatus status = HttpStatus.OK;
		try {
			jsonObject = service.getAllUsers();
			if(jsonObject.containsKey(status)) {
				if(jsonObject.get(status).equals("Error")) {
					status =HttpStatus.BAD_REQUEST;
				}
				
			}
			logger.info("successfully fetched user info");
			
		}
		catch (Exception e){
			logger.error("Error in UsersController.getAllUsers():: ", e.getMessage());
			throw e;
		
		}
//		JSONObject customResponse = new JSONObject();
//		customResponse.put("response", jsonObject);
		CustomResponse customResponse = new CustomResponse(jsonObject);
		return new ResponseEntity<>(customResponse,status);
	}
	
	@GetMapping("/getUserByName")
	public ResponseEntity<?> getUserByName(@RequestParam("name") String name){
		JSONObject jsonObject = new JSONObject();
		HttpStatus status = HttpStatus.OK;
		try {
			jsonObject = service.getUserByname(name);
			if(jsonObject.containsKey(status)) {
				if(jsonObject.get(status).equals("Error")) {
					status = HttpStatus.BAD_REQUEST;
				}
			}
			logger.info("successfully fetched user info of :: "+name);
		}
		catch (Exception e){
			logger.error("Error in UserController.getUserByName():: ", e.getMessage());
			throw e;
		}
//		JSONObject customResponse = new JSONObject();
//		customResponse.put("response", jsonObject);
		CustomResponse customResponse = new CustomResponse(jsonObject);
		return new ResponseEntity<>(customResponse,status);
		
	}
	@GetMapping("/getUserById")
	public ResponseEntity<?> getUserById(@RequestParam("Id") int Id){
		JSONObject jsonObject = new JSONObject();
		HttpStatus status = HttpStatus.OK;
		try {
			jsonObject = service.getUserById(Id);
			if(jsonObject.containsKey(status)) {
				if(jsonObject.get(status).equals("Error")) {
					status = HttpStatus.BAD_REQUEST;
				}
			}
			logger.info("successfully fetched user info of :: "+Id);
		}
		catch (Exception e){
			logger.error("Error in UserController.getUserById():: ", e.getMessage());
			throw e;
		}
//		JSONObject customResponse = new JSONObject();
//		customResponse.put("response", jsonObject);
		CustomResponse customResponse = new CustomResponse(jsonObject);
		return new ResponseEntity<>(customResponse,status);
	}
	@GetMapping("/isUserNameExist")
	public ResponseEntity<?> isUserNameExist(@RequestParam("name") String name){
		JSONObject jsonObject = new JSONObject();
		HttpStatus status = HttpStatus.OK;
		try {
			jsonObject = service.isUserNameExist(name);
			if(jsonObject.containsKey(status)) {
				if(jsonObject.get(status).equals("Error"));
				status = HttpStatus.BAD_REQUEST;
				 
			}
		}
		catch (Exception e) {
			throw e;
		}
//		JSONObject customResponse = new JSONObject();
//		customResponse.put("response", jsonObject);
		CustomResponse customResponse = new CustomResponse(jsonObject);
		return new ResponseEntity<>(customResponse,status);
	}
		
	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUSer(@RequestParam("Id") Integer Id, @RequestBody User userDto){
		JSONObject jsonObject = new JSONObject();
		HttpStatus status = HttpStatus.OK;
		try {
			User user = new User();
			mapper.map(userDto, user);
			jsonObject = service.updateUser(Id,user);
			if(jsonObject.containsKey("status")) {
				if(jsonObject.get("status").equals("Error")) {
					status = HttpStatus.BAD_REQUEST;					
				}
				
			}logger.info("successfully fetched user info of :: "+Id);
		}
		catch (Exception e){
			logger.error("Error in UserController.updateUser():: ", e.getMessage());
			throw e;
		}
		CustomResponse customResponse = new CustomResponse(jsonObject);
		return new ResponseEntity<>(customResponse,status);
		
	}
	
//	@PostMapping(value = "/downloadUserList")
//	public ResponseEntity<?> downloadUserList(
//			@RequestParam(value = "pagination", required = false ,defaultValue = "true")Boolean paginationFlag,
//			@RequestParam(value = "page", required = false , defaultValue = "1") Integer pageNo,
//			@RequestParam(value = "per_page", required = false, defaultValue = "10") Integer pageSize)
//	{
//		JSONObject jsonObject = new JSONObject();
//		boolean exportFlag = false;
//		HttpStatus status = HttpStatus.OK;
//		try {
//			jsonObject = service.downloadUserList(exportFlag, paginationFlag, pageNo, pageSize);
//			if(jsonObject.containsKey(status)) {
//				if(jsonObject.get(status).equals("Error")) {
//					status = HttpStatus.BAD_REQUEST;
//				}
//			}
//		}
//		catch (Exception e) {
//			throw e;
//		}
//		CustomResponse customResponse = new CustomResponse(jsonObject);
//		return new ResponseEntity<>(customResponse,status);
//		
//	}
	
	@PostMapping(value = "/kuchbhi")
	public ResponseEntity<?> kuchBhi(){
		JSONObject jsonObject = new JSONObject();
		HttpStatus status = HttpStatus.OK;
		JSONObject obj = new JSONObject();
		
		jsonObject.put(Constants.RESPONSE, "success");
		jsonObject.put(Constants.MESSAGE,"kuch bhi");
		obj.put(Constants.DATA, jsonObject);
		
		CustomResponse customResponse = new CustomResponse(obj);
		return new ResponseEntity<>(customResponse,status);
		
	}
}
