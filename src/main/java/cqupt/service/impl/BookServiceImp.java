package cqupt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cqupt.dao.BookDao;
import cqupt.exception.BookException;
import cqupt.po.Book;
import cqupt.service.BookService;
@Service
public class BookServiceImp implements BookService {
	@Autowired
	private BookDao bookDao;
	public int addBook(Book book) throws BookException {
		try {
			return bookDao.addBook(book);
		} catch (Exception e) {
			throw new BookException("添加失败");
		}
	}

	public int updateBook(Book book) throws BookException {
		try {
			return bookDao.updateBook(book);
		} catch (Exception e) {
			throw new BookException("更新失败");
		}
	}

	public int deleteBook(int bookid) throws BookException {
		try {
			return bookDao.deleteBook(bookid);
		} catch (Exception e) {
			throw new BookException("删除失败");
		}
	}

	public Book selectBookByBookId(int bookid) throws BookException {
		try {
			return bookDao.selectBookByBookId(bookid);
		} catch (Exception e) {
			throw new BookException("按id查询失败");
		}
	}

	public List<Book> selectBookByCateory(String category) throws BookException {
		try {
			return bookDao.selectBookByCateory(category);
		} catch (Exception e) {
			throw new BookException("按类型查询失败");
		}
	}

	public List<Book> selectBookByBooknameAndEditor(Book book) throws BookException {
		try {
			return bookDao.selectBookByBooknameAndEditor(book);
		} catch (Exception e) {
			throw new BookException("按书名和作者查询失败");
		}
	}

	public List<Book> selectAllBook() throws BookException {
		try {
			return bookDao.selectAllBook();
		} catch (Exception e) {
			throw new BookException("查询所有失败");
		}
	}

	public Book selectBookByNewBook(Book book) throws BookException {
		try {
			return bookDao.selectBookByNewBook(book);
		} catch (Exception e) {
			throw new BookException("查询失败");
		}
	}

	public List<Book> selectBookByBookname(String bookName) throws BookException {
		try {
			return bookDao.selectBookByBookname(bookName);
		} catch (Exception e) {
			throw new BookException("查询失败");
		}
	}

}
