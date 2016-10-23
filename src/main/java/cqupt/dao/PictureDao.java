package cqupt.dao;

import java.util.List;

import cqupt.po.Picture;

public interface PictureDao {
	int insertPicture(Picture picture)throws Exception;
	int deletePicture(Picture picture)throws Exception;
	int updatePicture(Picture picture)throws Exception;
	List<String> selectPictureByBookid(int bookid)throws Exception;
}
