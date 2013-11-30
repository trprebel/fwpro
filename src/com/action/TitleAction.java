package com.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

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
	
	private File file;
	private String fileContentType;
	private String fileFileName;
	
	

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
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
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
	public void uploadfile()
	{
		try {
			System.out.println(fileFileName);
			
			POIFSFileSystem fs;
		    HSSFWorkbook wb = null;
		    HSSFSheet sheet;
		    HSSFRow row;
			InputStream is = new FileInputStream(file);
			try {
	            fs = new POIFSFileSystem(is);
	            wb = new HSSFWorkbook(fs);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        sheet = wb.getSheetAt(0);
	        // 得到总行数
	        int rowNum = sheet.getLastRowNum();
	        row = sheet.getRow(0);
	        int colNum = row.getPhysicalNumberOfCells();
	        // 正文内容应该从第二行开始,第一行为表头的标题
	        for (int i = 0; i <= rowNum; i++) {
	            row = sheet.getRow(i);
	            int j = 0;
	            while (j < colNum) {
	                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
	                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
	                // str += getStringCellValue(row.getCell((short) j)).trim() +
	                // "-";
	                System.out.print(getCellFormatValue(row.getCell(j)).trim() + "    ");
	                j++;
	            }
	            System.out.println("");
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	 private String getCellFormatValue(HSSFCell cell) {
	        String cellvalue = "";
	        if (cell != null) {
	            // 判断当前Cell的Type
	            switch (cell.getCellType()) {
	            // 如果当前Cell的Type为NUMERIC
	            case HSSFCell.CELL_TYPE_NUMERIC:
	            case HSSFCell.CELL_TYPE_FORMULA: {
	                // 判断当前的cell是否为Date
	                if (HSSFDateUtil.isCellDateFormatted(cell)) {
	                    // 如果是Date类型则，转化为Data格式
	                    
	                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
	                    //cellvalue = cell.getDateCellValue().toLocaleString();
	                    
	                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
	                    Date date = cell.getDateCellValue();
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                    cellvalue = sdf.format(date);
	                    
	                }
	                // 如果是纯数字
	                else {
	                    // 取得当前Cell的数值
	                    cellvalue = String.valueOf(cell.getNumericCellValue());
	                }
	                break;
	            }
	            // 如果当前Cell的Type为STRIN
	            case HSSFCell.CELL_TYPE_STRING:
	                // 取得当前的Cell字符串
	                cellvalue = cell.getRichStringCellValue().getString();
	                break;
	            // 默认的Cell值
	            default:
	                cellvalue = " ";
	            }
	        } else {
	            cellvalue = "";
	        }
	        return cellvalue;

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
