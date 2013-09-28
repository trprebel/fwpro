package com.dao.impl;


import java.util.List;

import com.bean.Image;
import com.bean.Title;


public class TitleDao {

	public void createTitle(Title title) throws Exception{
		SqlMap.getSqlMapClient().insert("createTitle",title);
	}
	public void insertImage(Image image) throws Exception{
		SqlMap.getSqlMapClient().insert("insertImage",image);
	}
	public List<Image> findAllImages() throws Exception{
		return (List<Image>)SqlMap.getSqlMapClient().queryForList("findAllImages");
	}
	public Title findTitleById(int id) throws Exception{
		return (Title)SqlMap.getSqlMapClient().queryForObject("findTitleById",id);
	}
}
