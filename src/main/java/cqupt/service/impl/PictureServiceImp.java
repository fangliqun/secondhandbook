package cqupt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cqupt.dao.PictureDao;
import cqupt.exception.PictureException;
import cqupt.po.Picture;
import cqupt.service.PictureService;

@Service
public class PictureServiceImp implements PictureService{
	@Autowired
	private PictureDao pictureDao;
	public int addPicture(Picture picture) throws PictureException {
		try {
			return pictureDao.insertPicture(picture);
		} catch (Exception e) {
			throw new PictureException("添加失败");
		}
	}

	public int deletePicture(Picture picture) throws PictureException {
		try {
			return pictureDao.deletePicture(picture);
		} catch (Exception e) {
			throw new PictureException("删除失败");
		}
	}

	public int updatePicture(Picture picture) throws PictureException {
		try {
			return pictureDao.updatePicture(picture);
		} catch (Exception e) {
			throw new PictureException("更新失败");
		}
	}

	public List<String> selectPictureByBookid(int bookid) throws PictureException {
		try {
			return pictureDao.selectPictureByBookid(bookid);
		} catch (Exception e) {
			throw new PictureException("查询失败");
		}	
	}
	
}
