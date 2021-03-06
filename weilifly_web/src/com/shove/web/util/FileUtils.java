package com.shove.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;

import com.weili.constants.IConstants;

/**   
*    
* @Description 用户文件操作
* @Author 微力科技
* @Date: Feb 9, 2012 2:42:48 AM   
* @Version    
*    
*/ 
public class FileUtils {
	
	private static Log log = LogFactory.getLog(FileUtils.class);
	
	/** 
	* @Description: 创建目录
	* @Author 微力科技
	* @Date: Feb 9, 2012 3:20:47 AM  
	* @param path  
	* @return void    
	*/ 
	public static void mkdirs(String path){
	    File uploadFilePath = new File(path);
        // 如果该目录不存在,则创建
        if(!uploadFilePath.exists()) {
            uploadFilePath.mkdirs();
            log.info("目录不存在已创建");
        }else{
        	log.info("目录已存在");
        }
	}
	
	/**
	 * 自定义文件名称
	 * @return
	 */
	public static String getFileName(){
		SimpleDateFormat simpledateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Random random = new Random();
		return simpledateFormat.format(new Date())+random.nextInt(10000);
	}
	
	/**
	 * 返回指定路径下的所有文件
	 * @param path 路径
	 * @return
	 */
	public static File[] getFiles(String path){
		File folder = new File(path);
		if(!folder.exists()){
			folder.mkdirs();
		}
		File[] files = folder.listFiles();//得到当前文件和子文件
		return files;
	}
	
	/**
	 * 删除文件
	 * @param file
	 */
	public static void removeFile(File file){
		if(file.exists()){//判断一个文件是否存在
			file.delete();
		}
	}
	
	/**
	 * 文件下载
	 * @param path
	 */
	public static void downloadFile(String path,HttpServletResponse response){
		try {
			// path是指欲下载的文件的路径。
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
