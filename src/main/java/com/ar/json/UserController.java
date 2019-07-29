package com.ar.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.admin.md5;
import com.ar.admin.service.ManagerService;
import com.ar.json.bean.AddPoint;
import com.ar.json.bean.TotalPoint;
import com.ar.json.bean.User;
import com.ar.json.service.UserService;
@Controller
public class UserController {
	@Autowired
    private UserService userService;
	
	@RequestMapping(value="/userlogin",method = RequestMethod.POST, consumes="application/json",produces="application/json")	
	public @ResponseBody String userlogin(@RequestBody User user){
		String UserPWD_md5 = md5.md5(user.getUserPWD());
		boolean result = userService.login(user.getUserAccount(), UserPWD_md5.toUpperCase());
		if(result) {
			return "{\"result\":\"0\",\"message\":\"success\"}";			
		}else {
			return "{\"result\":\"1\",\"message\":\"failed\"}";			
		}
		
	}
	
	@RequestMapping(value="/addUser",method = RequestMethod.POST, consumes="application/json",produces="application/json")
	public @ResponseBody String addUser(@RequestBody User user) {		
		boolean result = userService.checkUser(user.getUserAccount());
		if(result) {
			return "{\"result\":\"1\",\"message\":\"duplicate\"}";			
		}else {
			userService.addUser(user);
			return "{\"result\":\"0\",\"message\":\"success\"}";			
		}		
	}
	
	@RequestMapping(value="/checkUser/{username}",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String checkUser(@PathVariable("username") String username){
		boolean result = userService.checkUser(username);
		if(result) {
			return "{\"result\":\"1\",\"message\":\"duplicate\"}";			
		}else {			
			return "{\"result\":\"0\",\"message\":\"success\"}";			
		}	
	}
	
	@RequestMapping(value="/addPoint",method = RequestMethod.POST, consumes="application/json",produces="application/json")
	public @ResponseBody String addPoint(@RequestBody AddPoint addPoint) {
		if(addPoint!=null && addPoint.getUserAccount()!="" && addPoint.getPoint()!="") {
			boolean checkUserResult = userService.checkUser(addPoint.getUserAccount());
			if(checkUserResult) {
				boolean addUserPoint=userService.updateUserPoint(addPoint.getUserAccount(),Integer.valueOf(addPoint.getPoint()));
				if(addUserPoint) {
					return "{\"result\":\"0\",\"message\":\"success\"}";
				}else {
					return "{\"result\":\"1\",\"message\":\"failed\"}";
				}				
			}else {
				return "{\"result\":\"1\",\"message\":\"user not exits\"}";			
			}	
		}else {
			return "{\"result\":\"1\",\"message\":\"parameter is null\"}";
		}			
	}
}
