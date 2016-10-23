package cqupt.service;

import java.util.List;

import cqupt.exception.SaleException;
import cqupt.po.Book;
import cqupt.po.Sale;


public interface SaleService {
	public int addSale(Sale sale)throws SaleException;
	public int deleteSale(Sale sale)throws SaleException;
	public int updateSale(Sale sale)throws SaleException;
	public List<Book> selectBookidByUserid(int userid)throws SaleException;
	public List<Sale> selectSaleByUserid(int userid)throws SaleException;
}
