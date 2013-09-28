package com.http;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bean.Image;
import com.bean.Title;
import com.dao.impl.TitleDao;
import com.opensymphony.xwork2.ActionSupport;

public class GetXml extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//HTML«Î«Û
	public void getxml() throws Exception{
		try
		{
			
			TitleDao titledao=new TitleDao();
			Title title=titledao.findTitleById(4);
			//System.out.println(title.getContent());
			List<Image> images=titledao.findAllImages();
			Document document = DocumentHelper.createDocument();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			Element result = document.addElement("result");
			Element cmd = result.addElement("cmd");
			cmd.addText("getxml");
			Element successful = result.addElement("successful");
			successful.addText("yes");
			Element titleEL=result.addElement("title");
			Element titleContentEL=titleEL.addElement("titleContent");
			titleContentEL.addText(title.getContent());
			Element titleSpeedEL=titleEL.addElement("titleSpeed");
			titleSpeedEL.addText(title.getSpeed()+"");
			Element titleDirectionEL=titleEL.addElement("titleDirection");
			titleDirectionEL.addText(title.getDirection()+"");
			Element imageEL=result.addElement("image");
			for(Image image:images)
			{
				Element imageAttEL=imageEL.addElement("imageAtt");
				imageAttEL.addAttribute("id", image.getId()+"");
				imageAttEL.addAttribute("name", image.getPath());
			}
			
			
			
			
			out.println(document.asXML());
			out.flush();
			out.close();
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
