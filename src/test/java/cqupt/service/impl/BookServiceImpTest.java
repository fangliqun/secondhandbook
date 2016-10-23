package cqupt.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import cqupt.exception.BookException;
import cqupt.po.Book;
import cqupt.service.BookService;
import cqupt.service.PictureService;

public class BookServiceImpTest {

	@Test
	public void test() {
		BookServiceImp BookServiceImp=new BookServiceImp();
		try {
			System.out.println(2);
			Book book = (Book) BookServiceImp.selectBookByCateory("计算机");
			System.out.println(book.getBookid());
		} catch (BookException e) {
			e.printStackTrace();
		}
		
	}
}
