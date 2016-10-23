package cqupt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cqupt.dao.SaleDao;
import cqupt.exception.SaleException;
import cqupt.po.Book;
import cqupt.po.Sale;
import cqupt.service.SaleService;
@Service
public class SaleServiceImp implements SaleService{
	@Autowired
	private SaleDao saleDao;
	public int addSale(Sale sale) throws SaleException {
		try {
			return saleDao.insertSale(sale);
		} catch (Exception e) {
			throw new SaleException("添加失败");
		}
	}

	public int deleteSale(Sale sale) throws SaleException {
		try {
			return saleDao.deleteSale(sale);
		} catch (Exception e) {
			throw new SaleException("删除失败");
		}
	}

	public int updateSale(Sale sale) throws SaleException {
		try {
			return saleDao.updateSale(sale);
		} catch (Exception e) {
			throw new SaleException("更新失败");
		}
	}

	public List<Book> selectBookidByUserid(int userid) throws SaleException {
		try {
			return saleDao.selectBookidByUserid(userid);
		} catch (Exception e) {
			throw new SaleException("查询失败");
		}
	}

	public List<Sale> selectSaleByUserid(int userid) throws SaleException {
		try {
			return saleDao.selectSaleByUserid(userid);
		} catch (Exception e) {
			throw new SaleException("查询失败");
		}
	}

}
