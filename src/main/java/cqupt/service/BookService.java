package cqupt.service;

import java.util.List;

import cqupt.exception.BookException;
import cqupt.po.Book;

public interface BookService {
	public int addBook(Book book)throws BookException;
	public int updateBook(Book book)throws BookException;
	public int deleteBook(int bookid)throws BookException;
	public Book selectBookByBookId(int bookid)throws BookException;
	public List<Book> selectBookByCateory(String category)throws BookException;
	public List<Book> selectBookByBooknameAndEditor(Book book)throws BookException;
	public List<Book> selectAllBook()throws BookException;
	public Book selectBookByNewBook(Book book)throws BookException;
	public List<Book> selectBookByBookname(String bookName)throws BookException;
}
