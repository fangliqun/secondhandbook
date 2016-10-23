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
import cqupt.exception.PictureException;
import cqupt.exception.SaleException;
import cqupt.exception.UserException;
import cqupt.po.Book;
import cqupt.po.Sale;
import cqupt.po.User;
import cqupt.service.BookService;
import cqupt.service.PictureService;
import cqupt.service.SaleService;
import cqupt.service.UserService;
import cqupt.utils.GsonUtils;
@Controller
@RequestMapping("/sale")
public class SaleController {
	@Resource(name="saleServiceImp")
	private SaleService saleService;
	
	@Resource(name="bookServiceImp")
	private BookService bookService;
	
	@Resource(name="pictureServiceImp")
	private PictureService pictureService;
	
	@Resource(name="userServiceImp")
	private UserService userService;
	
	@RequestMapping(value="/addSaleTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String addSaleTest(@RequestParam("userid")int userid,@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Sale sale=new Sale();
		sale.setBookid(bookid);
		//sale.setUserid(userid);
		try {
			int i=saleService.addSale(sale);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (SaleException e) {
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
	@RequestMapping(value="/updateSaleTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String updateSaleTest(@RequestParam("userid")int userid,@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Sale sale=new Sale();
		sale.setBookid(bookid);
		//sale.setUserid(userid);
		try {
			int i=saleService.updateSale(sale);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (SaleException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/deleteSaleTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String deleteSaleTest(@RequestParam("userid")int userid,@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Sale sale=new Sale();
		sale.setBookid(bookid);
		//sale.setUserid(userid);
		try {
			int i=saleService.deleteSale(sale);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (SaleException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/addSale",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String addSale(@RequestParam("Sale")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Sale sale=GsonUtils.getEntity(s,Sale.class);
		sale.setDeliverstate(0);
		sale.setPaystate(0);
		sale.setTakestate(0);
		
		try {
			Book book=new Book();
			book.setState("出售");
			book.setBookid(sale.getBookid());
			bookService.updateBook(book);
			
			int i=saleService.addSale(sale);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (SaleException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/updateSale",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updateSale(@RequestParam("Sale")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Sale sale=GsonUtils.getEntity(s,Sale.class);
		try {
			int i=saleService.updateSale(sale);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (SaleException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/deleteSale",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String deleteSale(@RequestParam("Sale")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Sale sale=GsonUtils.getEntity(s,Sale.class);
		try {
			int i=saleService.deleteSale(sale);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (SaleException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectSale",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectSale(@RequestParam("userid")int userid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		JsonArray jsonArray=new JsonArray();
		try {
			List<Sale> sale=saleService.selectSaleByUserid(userid);
			for(int i=0;i<sale.size();i++){
				JsonObject jsonObject_=GsonUtils.getJsonObject();
				Book book=bookService.selectBookByBookId(sale.get(i).getBookid());
				
				List<String> list=pictureService.selectPictureByBookid(sale.get(i).getBookid());
				JsonObject url=GsonUtils.getJsonObject();
				url.addProperty("url0",list.get(0));
				url.addProperty("url1",list.get(1));
				url.addProperty("url2",list.get(2));
				
				jsonObject_.addProperty("bookid",book.getBookid());
				jsonObject_.addProperty("bookage",book.getBookage());
				jsonObject_.addProperty("bookname",book.getBookname());
				jsonObject_.addProperty("category",book.getCategory());
				jsonObject_.addProperty("department",book.getDepartment());
				jsonObject_.addProperty("editor",book.getEditor());
				jsonObject_.addProperty("money",book.getMoney());
				jsonObject_.addProperty("state",book.getState());
				
				User user1=userService.getUserInfoByUserId(book.getUploaduserid());
				jsonObject_.addProperty("uploaduserid",book.getUploaduserid());
				jsonObject_.addProperty("uploadusername",user1.getUsername());
				User user2=userService.getUserInfoByUserId(sale.get(i).getBuyuserid());
				jsonObject_.addProperty("buyuserid",sale.get(i).getBuyuserid());
				jsonObject_.addProperty("buyusername",user2.getUsername());
				
				jsonObject_.add("url",url);
				
				
				jsonObject_.addProperty("paystate",sale.get(i).getPaystate());
				jsonObject_.addProperty("deliverstate",sale.get(i).getDeliverstate());
				jsonObject_.addProperty("takestate",sale.get(i).getTakestate());
				
				jsonArray.add(jsonObject_);
			}
			return jsonArray.toString();
		} catch (SaleException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		} catch (PictureException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		} catch (UserException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
}
