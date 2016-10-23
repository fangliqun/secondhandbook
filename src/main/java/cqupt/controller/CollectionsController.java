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

import cqupt.exception.BookException;
import cqupt.exception.CollectionsException;
import cqupt.exception.PictureException;
import cqupt.po.Book;
import cqupt.po.Collections;
import cqupt.service.CollectionsService;
import cqupt.service.PictureService;
import cqupt.utils.GsonUtils;

@Controller
@RequestMapping("/collections")
public class CollectionsController {
	@Resource(name="collectionsServiceImp")
	private CollectionsService collectionsService;
	@Resource(name="pictureServiceImp")
	private PictureService pictureService;
	
	@RequestMapping(value="/addCollectionsTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String addCollectionsTest(@RequestParam("userid")int userid,@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			List<Integer> list=collectionsService.selectBookidByUserid(userid);
			for(int a=0;a<list.size();a++){
				if(list.get(a)!=bookid){
					Collections collections=new Collections();
					collections.setBookid(bookid);
					collections.setUserid(userid);
					int i=collectionsService.addCollections(collections);
					if(i!=0){
						jsonObject.addProperty("msg","sucess");
						return jsonObject.toString();
					}else{
						jsonObject.addProperty("msg","fail");
						return jsonObject.toString();
					}
				}else{
					jsonObject.addProperty("msg","fail");
					return jsonObject.toString();
				}
			}
			return jsonObject.toString();
			
		} catch (CollectionsException e) {
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
	@RequestMapping(value="/updateCollectionsTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String updateCollectionsTest(@RequestParam("userid")int userid,@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Collections collections=new Collections();
		collections.setBookid(bookid);
		collections.setUserid(userid);
		try {
			int i=collectionsService.updateCollections(collections);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (CollectionsException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/deleteCollectionsTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String deleteCollectionsTest(@RequestParam("userid")int userid,@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Collections collections=new Collections();
		collections.setBookid(bookid);
		collections.setUserid(userid);
		try {
			int i=collectionsService.deleteCollections(collections);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (CollectionsException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/addCollections",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String addCollections(@RequestParam("Collections")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Collections collections=GsonUtils.getEntity(s,Collections.class);
		int bookid=collections.getBookid();
		int userid=collections.getUserid();
		try {
			//List<Integer> list=collectionsService.selectBookidByUserid(userid);
			//for(int a=0;a<=list.size();a++){
				//if(list.get(a)!=bookid){
					int i=collectionsService.addCollections(collections);
					if(i!=0){
						jsonObject.addProperty("msg","sucess");
					}else{
						jsonObject.addProperty("msg","fail");
					}
				//}else{
					jsonObject.addProperty("msg","已收藏");
				//}
			//}
			return jsonObject.toString();
		} catch (CollectionsException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/updateCollections",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updateCollections(@RequestParam("Collections")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Collections collections=GsonUtils.getEntity(s,Collections.class);
		try {
			int i=collectionsService.updateCollections(collections);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (CollectionsException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/deleteCollections",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String deleteCollections(@RequestParam("Collections")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Collections collections=GsonUtils.getEntity(s,Collections.class);
		try {
			int i=collectionsService.deleteCollections(collections);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (CollectionsException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectBookByUserId",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectBookByUserId(@RequestParam("userid")int userid){
		JsonObject jsonObject2=GsonUtils.getJsonObject();
		JsonArray array=new JsonArray();
	//	int userid=Integer.parseInt(userid_);
		try {
			List<Book> book=collectionsService.selectBookByUserid(userid);
			if(book.size()!=0){
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
					array.add(jsonObject);
				}
				return array.toString();
			}else{
				jsonObject2.addProperty("msg","fail");
				return jsonObject2.toString();
			}
		} catch (PictureException e) {
			jsonObject2.addProperty("msg",e.getMessage());
			return jsonObject2.toString();
		} catch (CollectionsException e) {
			jsonObject2.addProperty("msg",e.getMessage());
			return jsonObject2.toString();
		}
	}
}
