package com.ldm.demo.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ldm.demo.beans.User;
import com.ldm.demo.service.IUserService;
import com.sun.mail.iap.ByteArray;

@Controller
@RequestMapping("/god")
public class TestController {
	
	@Resource
	private IUserService userService;
	
	@RequestMapping("/fdafa")
	public String showMsg(HttpServletRequest req){
		req.setAttribute("aaa", "恭喜恭喜！！！");
		return "success";
	}
	
	
	
	@RequestMapping("/dosome")
	public String dosome(HttpServletRequest req){
		User user = new User();
		int id = 0;
		String name =null;
		if(null!=req.getParameter("id")&&req.getParameter("id").trim().length()>0){
			id = Integer.parseInt(req.getParameter("id"));
		}
		if(null!=req.getParameter("name")&&req.getParameter("name").trim().length()>0){
			name = req.getParameter("name");
		}
		
		//没事上传文件玩
		upload(req);
		
		System.out.println("id:"+id+";name:"+name);
		user.setId(id);
		user.setName(name);
		userService.insert(user);
		req.setAttribute("aaa", "插入一条数据,id:"+user.getId());
		return "success";
	}
	
	
	
	//上传文件，使用spring组件
	private void upload(HttpServletRequest req){
		InputStream is =null;
		OutputStream os = null;
		
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest)req;
		MultiValueMap<String,MultipartFile> mvm =  mhsr.getMultiFileMap();
		Iterator<String> iter = mvm.keySet().iterator();
		while(iter.hasNext()){
			String str = iter.next();
			List<MultipartFile> list =  mvm.get(str);
			for(MultipartFile temp:list){
				try {
					is = temp.getInputStream();
					String desPath = System.getProperty("java.io.tmpdir");
					os = new FileOutputStream(desPath+System.getProperty("file.separator")+"aaa.zip");
					System.out.println("上传保存路径："+desPath+System.getProperty("file.separator")+"aaa.zip");
					byte[] buf = new byte[1024];
					int len ;
					while((len=is.read(buf))>0){
						os.write(buf,0,len);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	static void testThread(int a ){
		newThread mythread = new newThread();
		Thread thread = new Thread(mythread);
		System.out.println("当前线程："+a);
		thread.start();
	}
	
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			testThread(i);
		}
	}
}

class newThread implements Runnable{
	@Override
	public void run() {
		for(int i =0;i<555555555;i++){
			
		}
		System.out.println("线程开始！");
	}
}
