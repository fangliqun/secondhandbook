package cqupt.dao;

import java.util.List;

import cqupt.exception.BookException;
import cqupt.po.Book;

public interface BookDao {
	int addBook(Book book)throws Exception;
	int updateBook(Book book)throws Exception;
	int deleteBook(int bookid)throws Exception;
	Book selectBookByBookId(int bookid)throws Exception;
	List<Book> selectBookByCateory(String category)throws Exception;
	List<Book> selectBookByBooknameAndEditor(Book book)throws Exception;//不能string a,string b。。只能组合
	List<Book> selectAllBook()throws Exception;
	//查询bookid 待定 
	//List<Integer> selectBookIdByBookid(Book book);
	//int selectBookIdBy
	Book selectBookByNewBook(Book book)throws Exception;
	List<Book> selectBookByBookname(String bookname)throws Exception;
}
