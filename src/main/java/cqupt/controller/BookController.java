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
import cqupt.po.Book;
import cqupt.po.Sale;
import cqupt.service.BookService;
import cqupt.service.PictureService;
import cqupt.service.SaleService;
import cqupt.service.impl.PictureServiceImp;
import cqupt.service.impl.SaleServiceImp;
import cqupt.utils.GsonUtils;

@Controller
@RequestMapping("/book")
public class BookController {
	@Resource(name="bookServiceImp")
	private BookService bookService;
	@Resource(name="pictureServiceImp")
	private PictureService pictureService;
	
	
	@RequestMapping(value="/addBookTest",produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String addBookTest(@RequestParam("username")String bookname,@RequestParam("editor")String editor){//这里有问题？参数改成String？在下面转化成实体
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Book book=new Book();
		book.setBookname(bookname);
		book.setEditor(editor);
		book.setState("待出售");
		try {
			int i=bookService.addBook(book);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/updateBookTest", produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String updateBookTest(@RequestParam("category")String category){//需要具体的bookid
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Book book=new Book();
		book.setCategory(category);
		book.setBookid(2);
		try {
			int i=bookService.updateBook(book);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/deleteBookTest", produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String deleteBookTest(@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			int i=bookService.deleteBook(bookid);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectBookByBookIdTest", produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectBookByBookIdTest(@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			Book book=bookService.selectBookByBookId(bookid);
			if(book!=null){
				return GsonUtils.EntityToJson(book);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectBookByCateoryTest", produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectBookByCateoryTest(@RequestParam("category")String category){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			List<Book> book=bookService.selectBookByCateory(category);
			if(book.size()!=0){
				return GsonUtils.EntityToJson(book);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectBookByBooknameAndEditorTest", produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectBookByBooknameAndEditorTest(@RequestParam("bookname")String bookname,@RequestParam("editor")String editor){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Book book=new Book();
		book.setBookname(bookname);
		book.setEditor(editor);
		try {
			List<Book> book_=bookService.selectBookByBooknameAndEditor(book);
			if(book_.size()!=0){
				return GsonUtils.EntityToJson(book_);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectAllBookTest", produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectAllBookTest(){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			List<Book> book=bookService.selectAllBook();
			if(book.size()!=0){
				return GsonUtils.EntityToJson(book);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 添加书，把书信息和图片上传并且加入到sale中
	 * @param s
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="/addBook",produces= "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String addBook(@RequestParam("Book")String s){//这里有问题？参数改成String？在下面转化成实体
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Book book=GsonUtils.getEntity(s, Book.class);
		book.setState("待出售");//上传的都为待出售
		try {
			Book book__=bookService.selectBookByNewBook(book);
			//System.out.println(book__.getBookage());
			if(book__==null){
				int i=bookService.addBook(book);
				if(i!=0){
					Book book_=bookService.selectBookByNewBook(book);
					int bookid=book_.getBookid();
					//String bookid_=bookid+"";
					Book book___=new Book();
					book___.setBookid(bookid);
//					Sale sale=new Sale();
//					sale.setBookid(bookid);
//					sale.setUserid(userid);
//					SaleService saleService=new SaleServiceImp();
//					int j=saleService.addSale(sale);
//					if(j==0){
//						jsonObject.addProperty("msg","fail");
//						return jsonObject.toString();
//					}
					//jsonObject.addProperty("msg","sucess");
					//return jsonObject.toString();
					return GsonUtils.EntityToJson(book___);
				}else{
					jsonObject.addProperty("msg","fail");
					return jsonObject.toString();
				}
			}else{
				jsonObject.addProperty("msg","已上传");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg", e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/updateBook", produces= "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String updateBook(@RequestParam("Book")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Book book=GsonUtils.getEntity(s, Book.class);
		try {
			int i=bookService.updateBook(book);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/deleteBook", produces= "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String deleteBook(@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			int i=bookService.deleteBook(bookid);
			if(i!=0){
				jsonObject.addProperty("msg","sucess");
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectBookByBookId", produces= "application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String selectBookByBookId(@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			Book book=bookService.selectBookByBookId(bookid);
			if(book!=null){
				JsonObject jsonObject1=GsonUtils.getJsonObject();
				List<String> list=pictureService.selectPictureByBookid(book.getBookid());
				
				jsonObject1.addProperty("url0",list.get(0));
				jsonObject1.addProperty("url1",list.get(1));
				jsonObject1.addProperty("url2",list.get(2));
				
				
				jsonObject.addProperty("bookid",book.getBookid());
				jsonObject.addProperty("bookage",book.getBookage());
				jsonObject.addProperty("bookname",book.getBookname());
				jsonObject.addProperty("category",book.getCategory());
				jsonObject.addProperty("department",book.getDepartment());
				jsonObject.addProperty("editor",book.getEditor());
				jsonObject.addProperty("money",book.getMoney());
				jsonObject.addProperty("state",book.getState());
				jsonObject.addProperty("uploaduserid",book.getUploaduserid());
				jsonObject.add("url",jsonObject1);
				return jsonObject.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		} catch (PictureException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/selectBookByCateory", produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectBookByCateory(@RequestParam("category")String category){
		//可能出错，没有每次循环一个jsonobject
		JsonObject jsonObject2=GsonUtils.getJsonObject();
		JsonArray array=new JsonArray();
		try {
			List<Book> book=bookService.selectBookByCateory(category);
			if(book.size()!=0){
				for(int i=0;i<book.size();i++){
					JsonObject jsonObject1=GsonUtils.getJsonObject();
					JsonObject jsonObject=GsonUtils.getJsonObject();
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
		} catch (BookException e) {
			jsonObject2.addProperty("msg",e.getMessage());
			return jsonObject2.toString();
		} catch (PictureException e) {
			jsonObject2.addProperty("msg",e.getMessage());
			return jsonObject2.toString();
		}
	}
	
	@RequestMapping(value="/selectBookByBookname", produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectBookByBookname(@RequestParam("bookName")String s){
		JsonObject jsonObject2=GsonUtils.getJsonObject();
		JsonArray array=new JsonArray();
		try {
			List<Book> book=bookService.selectBookByBookname(s);
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
		} catch (BookException e) {
			jsonObject2.addProperty("msg",e.getMessage());
			return jsonObject2.toString();
		} catch (PictureException e) {
			jsonObject2.addProperty("msg",e.getMessage());
			return jsonObject2.toString();
		}
	}
	
	@RequestMapping(value="/selectBookByBooknameAndEditor", produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectBookByBooknameAndEditor(@RequestParam("Book")String s){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Book book=GsonUtils.getEntity(s, Book.class);
		JsonArray array=new JsonArray();
		try {
			List<Book> book_=bookService.selectBookByBooknameAndEditor(book);
			if(book_.size()!=0){
				for(int i=0;i<book_.size();i++){
					JsonObject jsonObject1=GsonUtils.getJsonObject();
					List<String> list=pictureService.selectPictureByBookid(book_.get(i).getBookid());
					
					jsonObject1.addProperty("url0",list.get(0));
					jsonObject1.addProperty("url1",list.get(1));
					jsonObject1.addProperty("url2",list.get(2));
					
					
					jsonObject.addProperty("bookid",book_.get(i).getBookid());
					jsonObject.addProperty("bookage",book_.get(i).getBookage());
					jsonObject.addProperty("bookname",book_.get(i).getBookname());
					jsonObject.addProperty("category",book_.get(i).getCategory());
					jsonObject.addProperty("department",book_.get(i).getDepartment());
					jsonObject.addProperty("editor",book_.get(i).getEditor());
					jsonObject.addProperty("money",book_.get(i).getMoney());
					jsonObject.addProperty("state",book_.get(i).getState());
					jsonObject.addProperty("uploaduserid",book_.get(i).getUploaduserid());
					jsonObject.add("url",jsonObject1);
					array.add(jsonObject);
				}
				return array.toString();
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (BookException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		} catch (PictureException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	/**
	 * 返回每本书后，在通过bookid去查询图片
	 * @return
	 */
	@RequestMapping(value="/selectAllBook", produces= "application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String selectAllBook(){
		JsonObject jsonObject2=GsonUtils.getJsonObject();
		JsonArray array=new JsonArray();
		try {
			List<Book> book=bookService.selectAllBook();
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
		} catch (BookException e) {
			jsonObject2.addProperty("msg",e.getMessage());
			return jsonObject2.toString();
		} catch (PictureException e) {
			jsonObject2.addProperty("msg",e.getMessage());
			return jsonObject2.toString();
		}
	}


	
	
}
