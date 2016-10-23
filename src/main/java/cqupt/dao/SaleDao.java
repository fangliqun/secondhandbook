package cqupt.dao;


import java.util.List;

import cqupt.exception.SaleException;
import cqupt.po.Book;
import cqupt.po.Sale;

public interface SaleDao {
	int insertSale(Sale sale)throws Exception;
	int deleteSale(Sale sale)throws Exception;
	int updateSale(Sale sale)throws Exception;
	List<Book> selectBookidByUserid(int userid)throws Exception;
	List<Sale> selectSaleByUserid(int userid)throws Exception;
}
