package cqupt.controller;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import cqupt.exception.BookException;
import cqupt.exception.PictureException;
import cqupt.po.Book;
import cqupt.service.BookService;
import cqupt.service.PictureService;
import cqupt.service.impl.BookServiceImp;
import cqupt.service.impl.PictureServiceImp;
import cqupt.utils.GsonUtils;

public class Test {

	public static void main(String[] args) {
		BookService bookService=new BookServiceImp();
		JsonArray array=new JsonArray();
		System.out.println(1);
		PictureService pictureService=new PictureServiceImp();
		System.out.println(2);
		try {
			List<Book> book=bookService.selectAllBook();
			System.out.println(book.get(0).getBookid());
			if(book.size()!=0){
				for(int i=0;i<book.size();i++){
					JsonObject jsonObject=GsonUtils.getJsonObject();
					JsonObject jsonObject1=GsonUtils.getJsonObject();
					List<String> list=pictureService.selectPictureByBookid(book.get(i).getBookid());
					System.out.println(list.get(1));
					
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
				System.out.println(array.toString());
			}
		} catch (BookException e) {
			System.out.println(e.getMessage());
		} catch (PictureException e) {
			System.out.println(e.getMessage());
		}
	}
}

