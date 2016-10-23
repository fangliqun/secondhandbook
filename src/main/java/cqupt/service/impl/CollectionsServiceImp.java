package cqupt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cqupt.dao.CollectionsDao;
import cqupt.exception.CollectionsException;
import cqupt.po.Book;
import cqupt.po.Collections;
import cqupt.service.CollectionsService;
@Service
public class CollectionsServiceImp implements CollectionsService{
	@Autowired
	private CollectionsDao collectionsDao;
	public int addCollections(Collections collections) throws CollectionsException {
		try {
			return collectionsDao.insertCollections(collections) ;
		} catch (Exception e) {
			throw new CollectionsException("添加失败");
		}
	}

	public int deleteCollections(Collections collections) throws CollectionsException {
		try {
			return collectionsDao.deleteCollections(collections);
		} catch (Exception e) {
			throw new CollectionsException("删除失败");
		}
	}

	public int updateCollections(Collections collections) throws CollectionsException {
		try {
			return collectionsDao.updateCollections(collections);
		} catch (Exception e) {
			throw new CollectionsException("更新失败");
		}
	}

	public List<Book> selectBookByUserid(int userid) throws CollectionsException {
		try {
			return collectionsDao.selectBookByUserid(userid);
		} catch (Exception e) {
			throw new CollectionsException("查询失败");
		}
	}

	public List<Integer> selectBookidByUserid(int userid) throws CollectionsException {
		try {
			return collectionsDao.selectBookidByUserid(userid);
		} catch (Exception e) {
			throw new CollectionsException("查询失败");
		}
	}

}
