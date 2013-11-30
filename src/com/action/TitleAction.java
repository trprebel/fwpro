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

	private List<File> picture;   //�����ϴ����ļ�
	private List<String> pictureContentType;	 //�����ϴ����ļ�����
	private List<String> pictureFileName;   //�����ϴ����ļ���
	
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
			result="�����ɹ���";
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result="����ʧ�ܣ�";
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
                
                //���ϴ����ļ������ɵ��������������
                //���ж����ɵ��ļ����Ƿ��Ѿ�����
                //������ڣ���������������������ֱ���Ҵ�ûʹ�õ������Ϊֹ
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
			messages = "�ļ��ϴ��ɹ���";
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
	        // �õ�������
	        int rowNum = sheet.getLastRowNum();
	        row = sheet.getRow(0);
	        int colNum = row.getPhysicalNumberOfCells();
	        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
	        for (int i = 0; i <= rowNum; i++) {
	            row = sheet.getRow(i);
	            int j = 0;
	            while (j < colNum) {
	                // ÿ����Ԫ�������������"-"�ָ���Ժ���Ҫʱ��String���replace()������ԭ����
	                // Ҳ���Խ�ÿ����Ԫ����������õ�һ��javabean�������У���ʱ��Ҫ�½�һ��javabean
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
	            // �жϵ�ǰCell��Type
	            switch (cell.getCellType()) {
	            // �����ǰCell��TypeΪNUMERIC
	            case HSSFCell.CELL_TYPE_NUMERIC:
	            case HSSFCell.CELL_TYPE_FORMULA: {
	                // �жϵ�ǰ��cell�Ƿ�ΪDate
	                if (HSSFDateUtil.isCellDateFormatted(cell)) {
	                    // �����Date������ת��ΪData��ʽ
	                    
	                    //����1�������ӵ�data��ʽ�Ǵ�ʱ����ģ�2011-10-12 0:00:00
	                    //cellvalue = cell.getDateCellValue().toLocaleString();
	                    
	                    //����2�������ӵ�data��ʽ�ǲ�����ʱ����ģ�2011-10-12
	                    Date date = cell.getDateCellValue();
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                    cellvalue = sdf.format(date);
	                    
	                }
	                // ����Ǵ�����
	                else {
	                    // ȡ�õ�ǰCell����ֵ
	                    cellvalue = String.valueOf(cell.getNumericCellValue());
	                }
	                break;
	            }
	            // �����ǰCell��TypeΪSTRIN
	            case HSSFCell.CELL_TYPE_STRING:
	                // ȡ�õ�ǰ��Cell�ַ���
	                cellvalue = cell.getRichStringCellValue().getString();
	                break;
	            // Ĭ�ϵ�Cellֵ
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
