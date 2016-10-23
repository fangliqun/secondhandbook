package cqupt.service;

import java.util.List;

import cqupt.exception.BuyException;
import cqupt.po.Book;
import cqupt.po.Buy;

public interface BuyService {
	public int addBuy(Buy buy)throws BuyException;
	public int deleteBuy(Buy buy)throws BuyException;
	public int updateBuy(Buy buy)throws BuyException;
	public List<Book> selectBookidByUserid(int userid)throws BuyException;
}
