package cqupt.service;

import java.util.List;

import cqupt.exception.CollectionsException;
import cqupt.po.Book;
import cqupt.po.Collections;

public interface CollectionsService {
	public int addCollections(Collections collections)throws CollectionsException;
	public int deleteCollections(Collections collections)throws CollectionsException;
	public int updateCollections(Collections collections)throws CollectionsException;
	public List<Book> selectBookByUserid(int userid)throws CollectionsException; 
	public List<Integer> selectBookidByUserid(int userid)throws CollectionsException; 
}
