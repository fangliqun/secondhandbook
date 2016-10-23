package cqupt.dao;

import java.util.List;

import cqupt.po.Book;
import cqupt.po.Buy;

public interface BuyDao {
	int insertBuy(Buy buy)throws Exception;
	int deleteBuy(Buy buy)throws Exception;
	int updateBuy(Buy buy)throws Exception;
	List<Book> selectBookidByUserid(int userid)throws Exception;
}
