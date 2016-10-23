package cqupt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cqupt.exception.BuyException;
import cqupt.exception.PictureException;
import cqupt.po.Book;
import cqupt.po.Buy;
import cqupt.service.BuyService;
import cqupt.service.PictureService;
import cqupt.utils.GsonUtils;
@Controller
@RequestMapping("/buy")
public class BuyController {
	@Resource(name="buyServiceImp")
	private BuyService buyService;
	@Resource(name="pictureServiceImp")
	private PictureService pictureService;
	
	@RequestMapping(value="/addBuyTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String addBuyTest(@RequestParam("userid")int userid,@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Buy buy =new Buy();
		buy.setBookid(bookid);
		buy.setUserid(userid);
		try {
			int i=buyService.addBuy(buy);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BuyException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	/**
	 * 更新其实没用处
	 * @param userid
	 * @param bookid
	 * @return
	 */
	@RequestMapping(value="/updateBuyTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String updateBuyTest(@RequestParam("userid")int userid,@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Buy buy =new Buy();
		buy.setBookid(bookid);
		buy.setUserid(userid);
		try {
			int i=buyService.updateBuy(buy);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BuyException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/deleteBuyTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String deleteBuyTest(@RequestParam("userid")int userid,@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Buy buy =new Buy();
		buy.setBookid(bookid);
		buy.setUserid(userid);
		try {
			int i=buyService.deleteBuy(buy);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BuyException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/addBuy",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String addBuy(@RequestParam("Buy")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Buy buy=GsonUtils.getEntity(s,Buy.class);
		try {
			int i=buyService.addBuy(buy);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BuyException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/updateBuy",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updateBuy(@RequestParam("Buy")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Buy buy=GsonUtils.getEntity(s,Buy.class);
		try {
			int i=buyService.updateBuy(buy);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BuyException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/deleteBuy",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String deleteBuy(@RequestParam("Buy")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Buy buy=GsonUtils.getEntity(s,Buy.class);
		try {
			int i=buyService.deleteBuy(buy);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BuyException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectBookByUserId",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectBookByUserId(@RequestParam("userid")int userid){
		JsonObject jsonObject_=GsonUtils.getJsonObject();
		JsonArray jsonArray=new JsonArray();
		try {
			List<Book> book=buyService.selectBookidByUserid(userid);
			if(!book.isEmpty()){
				for(int i=0;i<book.size();i++){
					JsonObject jsonObject=GsonUtils.getJsonObject();
					JsonObject jsonObject1=GsonUtils.getJsonObject();
					List<String> list=pictureService.selectPictureByBookid(book.get(i).getBookid());
					
					jsonObject1.addProperty("url0",list.get(0));
					jsonObject1.addProperty("url1",list.get(1));
					jsonObject1.addProperty("url2",list.get(2));
					
					
					jsonObject.addProperty("bookid",book.get(i).getBookid());
					jsonObject.addProperty("bookage",book.get(i).getBookage());
					jsonObject.addProperty("bookname",book.get(i).getBookname());
					jsonObject.addProperty("category",book.get(i).getCategory());
					jsonObject.addProperty("department",book.get(i).getDepartment());
					jsonObject.addProperty("editor",book.get(i).getEditor());
					jsonObject.addProperty("money",book.get(i).getMoney());
					jsonObject.addProperty("state",book.get(i).getState());
					jsonObject.addProperty("uploaduserid",book.get(i).getUploaduserid());
					jsonObject.add("url",jsonObject1);
					jsonArray.add(jsonObject);
				}
				return jsonArray.toString();
			}else{
				jsonObject_.addProperty("msg","fail");
				return jsonObject_.toString();
			}
		} catch (BuyException e) {
			jsonObject_.addProperty("msg",e.getMessage());
			return jsonObject_.toString();
		} catch (PictureException e) {
			jsonObject_.addProperty("msg",e.getMessage());
			return jsonObject_.toString();
		}
	}
}
