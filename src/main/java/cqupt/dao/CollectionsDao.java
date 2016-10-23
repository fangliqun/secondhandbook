package cqupt.dao;

import java.util.List;

import cqupt.po.Book;
import cqupt.po.Collections;

public interface CollectionsDao {
	int insertCollections(Collections collections)throws Exception;
	int deleteCollections(Collections collections)throws Exception;
	int updateCollections(Collections collections)throws Exception;
	List<Book> selectBookByUserid(int userid)throws Exception; 
	List<Integer> selectBookidByUserid(int userid)throws Exception; 
}
