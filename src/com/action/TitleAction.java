package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import com.bean.Image;
import com.bean.Title;
import com.dao.impl.TitleDao;
import com.opensymphony.xwork2.ActionSupport;

import filter.StringUtil;

public class TitleAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	private String speed;
	private String direction;
	private String messages;
	private String result;

	private List<File> picture;   //保存上传的文件
	private List<String> pictureContentType;	 //保存上传的文件类型
	private List<String> pictureFileName;   //保存上传的文件名
	private String uploadPath;


	private TitleDao titledao;


	public String getContent() {
		return content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getmessages() {
		return messages;
	}
	public void setmessages(String messages) {
		this.messages = messages;
	}
	public String releasetitle()
	{
		//System.out.println(content);
		//System.out.println(speed);
		//System.out.println(direction);
		try{
			Title title=new Title();
			titledao=new TitleDao();
			title.setContent(content);
			title.setSpeed(Integer.parseInt(speed));
			title.setDirection(Integer.parseInt(direction));
			titledao.createTitle(title);
			result="发布成功！";
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result="发布失败！";
		}
		
		return "success";
		
	}

	public String uploadpic()
	{

		try
		{
			//StringUtil.getSpPropeurl("imagePath");
			uploadPath=StringUtil.getSpPropeurl("imagePath");
			for (int i = 0; i < pictureFileName.size(); i++) {
				String filename = pictureFileName.get(i); 
				Random random = new Random();
                
                //把上传的文件用生成的随机数重新命名
                //并判断生成的文件名是否已经存在
                //如果存在，则继续生成随机数命名，直到找打还没使用的随机数为止
				String dbfilename=random.nextLong()+ filename.substring(filename.lastIndexOf("."));
                filename = uploadPath + dbfilename;
                
                while (new File(filename).exists()) {
                	dbfilename=random.nextLong()+ filename.substring(filename.lastIndexOf("."));
                	filename = uploadPath + dbfilename;
                }
                
				//System.out.println(dbfilename);
				FileOutputStream fos = new FileOutputStream(filename);
                //System.out.println(filename);
                InputStream is = new FileInputStream(picture.get(i));
				try
				{
	                
	                
	                byte[] buffer = new byte[4*1024];
	                int count = 0;
	                while ((count = is.read(buffer)) > 0) {
	                	fos.write(buffer, 0, count);
	                }
	                //System.out.println(filename);
	                Image image=new Image();
	                image.setPath(dbfilename);
	                titledao=new TitleDao();
	                titledao.insertImage(image);
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				finally
				{
					fos.close();
	                is.close();
				}

                
			}
			messages = "文件上传成功！";
			return "success";

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "error";
		}
		
	}
	public List<File> getPicture() {
		return picture;
	}
	public void setPicture(List<File> picture) {
		this.picture = picture;
	}
	public List<String> getPictureContentType() {
		return pictureContentType;
	}
	public void setPictureContentType(List<String> pictureContentType) {
		this.pictureContentType = pictureContentType;
	}
	public List<String> getPictureFileName() {
		return pictureFileName;
	}
	public void setPictureFileName(List<String> pictureFileName) {
		this.pictureFileName = pictureFileName;
	}


}
