package cqupt.service;

import java.util.List;

import cqupt.exception.PictureException;
import cqupt.po.Picture;

public interface PictureService {
	public int addPicture(Picture picture)throws PictureException;
	public int deletePicture(Picture picture)throws PictureException;
	public int updatePicture(Picture picture)throws PictureException;
	public List<String> selectPictureByBookid(int bookid)throws PictureException;
}
