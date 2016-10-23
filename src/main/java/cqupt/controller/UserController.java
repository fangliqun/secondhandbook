package cqupt.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import cqupt.exception.UserException;
import cqupt.po.User;
import cqupt.service.UserService;
import cqupt.utils.GsonUtils;
@Controller
@RequestMapping("/user")
public class UserController {
	//@Autowired
	@Resource(name="userServiceImp")
	private UserService userService;
	
	//@RequestMapping(value = "/addUserTest",还有个请求参数   这是返回格式produces ={"text/html;charset=UTF-8"}/*"application/json;charset=UTF-8"*/,method = RequestMethod.GET)
	@RequestMapping(value = "/addUserTest",produces ="application/json;charset=UTF-8",method = RequestMethod.GET)
	@ResponseBody
	public String addUserTest(@RequestParam("username")String username,@RequestParam("password")String password){
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		System.out.println(username+password);
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			User user_=userService.selectUserByUsername(username);
			if(user_!=null){
				jsonObject.addProperty("msg","username已被注册");
				return jsonObject.toString();
			}else{
				int i = userService.addUser(user);
				if(i!=0){
					jsonObject.addProperty("msg","sucess");
					return jsonObject.toString();
				}else{
					jsonObject.addProperty("msg","fail");
					return jsonObject.toString();
				}
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/loginTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String loginTest(@RequestParam("username")String username,@RequestParam("password")String password){
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			User user_=userService.login(user);
			if(user_!=null){
				return GsonUtils.EntityToJson(user_);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/deleteUserTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String deleteUserTest(@RequestParam("userid")int userid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			int i=userService.deleteUser(userid);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/modifyUserTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String modifyUserTest(@RequestParam("sex")String sex){//这里需要给我一个userid，我才能判断修改哪一个
		JsonObject jsonObject=GsonUtils.getJsonObject();
		User user=new User();
		user.setSex(sex);
		user.setUserid(17);
		try {
			int i=userService.modifyUser(user);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	
	@RequestMapping(value="/getUserInfoByUserIdTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String getUserInfoByUserIdTest(@RequestParam("userid")int userid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			User user=userService.getUserInfoByUserId(userid);
			if(user!=null){
				return GsonUtils.EntityToJson(user);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectUserByUsernameTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectUserByUsernameTest(@RequestParam("username")String username){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			User user=userService.selectUserByUsername(username);
			if(user!=null){
				return GsonUtils.EntityToJson(user);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/addUser",produces ="application/json;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String addUser(@RequestParam("User")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		User user=GsonUtils.getEntity(s,User.class);
		try {
			User user_=userService.selectUserByUsername(user.getUsername());
			if(user_!=null){
				jsonObject.addProperty("msg","username已被注册");
				return jsonObject.toString();
			}else{
				int i = userService.addUser(user);
				if(i!=0){
					jsonObject.addProperty("msg","sucess");
					return jsonObject.toString();
					//return GsonUtils.EntityToJson(user_);返回只能是int
				}else{
					jsonObject.addProperty("msg","fail");
					return jsonObject.toString();
				}
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/login",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("User")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		User user=GsonUtils.getEntity(s,User.class);
		try {
			User user_=userService.login(user);
			if(user_!=null){
				return GsonUtils.EntityToJson(user_);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/deleteUser",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String deleteUser(@RequestParam("userid")int userid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			int i=userService.deleteUser(userid);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/modifyUser",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String modifyUser(@RequestParam("User")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		User user=GsonUtils.getEntity(s,User.class);
		try {
			int i=userService.modifyUser(user);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/getUserInfoByUserId",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String getUserInfoByUserId(@RequestParam("userid")int userid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			User user=userService.getUserInfoByUserId(userid);
			if(user!=null){
				return GsonUtils.EntityToJson(user);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectUserByUsername",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String selectUserByUsername(@RequestParam("username")String username){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			User user=userService.selectUserByUsername(username);
			if(user!=null){
				return GsonUtils.EntityToJson(user);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (UserException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
}
