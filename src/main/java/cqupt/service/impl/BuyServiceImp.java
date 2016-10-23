package cqupt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cqupt.dao.BuyDao;
import cqupt.exception.BuyException;
import cqupt.po.Book;
import cqupt.po.Buy;
import cqupt.service.BuyService;

@Service
public class BuyServiceImp implements BuyService{
	@Autowired
	private BuyDao buyDao;
	public int addBuy(Buy buy) throws BuyException {
		try {
			return buyDao.insertBuy(buy);
		} catch (Exception e) {
			throw new BuyException("添加失败");
		}
	}

	public int deleteBuy(Buy buy) throws BuyException {
		try {
			return buyDao.deleteBuy(buy);
		} catch (Exception e) {
			throw new BuyException("删除失败");
		}
	}

	public int updateBuy(Buy buy) throws BuyException {
		try {
			return buyDao.updateBuy(buy);
		} catch (Exception e) {
			throw new BuyException("更新失败");
		}
	}

	public List<Book> selectBookidByUserid(int userid) throws BuyException {
		try {
			return buyDao.selectBookidByUserid(userid);
		} catch (Exception e) {
			throw new BuyException("查询失败");
		}
	}

}
