package cqupt.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import cqupt.exception.PictureException;
import cqupt.po.Picture;
import cqupt.service.PictureService;
import cqupt.utils.GsonUtils;

@Controller
@RequestMapping("/picture")
public class PictureController {
	@Resource(name="pictureServiceImp")
	private PictureService pictureService;
	
	@RequestMapping(value="/readPictureTest",produces="application/json;charset=UTF-8",method=RequestMethod.GET)
	@ResponseBody
	public String readPictureTest(@RequestParam("bookid")int bookid){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		try {
			List<String> list=pictureService.selectPictureByBookid(bookid);
			if(!list.isEmpty()){
				return GsonUtils.EntityToJson(list);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (PictureException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		} 
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/uploadPicture",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	//MultipartHttpServletRequest   
	public String uploadPicture(@RequestParam("Picture")String s,HttpServletRequest request,@RequestParam("files")MultipartFile[] files){//需要bookid
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Picture picture=GsonUtils.getEntity(s,Picture.class);
		try {
			 if (files != null && files.length > 0) {
		            for (int i = 0; i< files.length; i++) {
		            	MultipartFile file = files[i];
		            	if(!file.isEmpty()){
							//String filePath="D:\\TomCat\\picture"+"\\"+picture.getBookid()+"\\"+file.getOriginalFilename();
							//String filePath = request.getSession().getServletContext().getRealPath("/") + "upload\\" + picture.getBookid()+"\\"+file.getOriginalFilename();
							// String filePath="/secondhandbook/"+file.getOriginalFilename();
		            		String fileName = file.getOriginalFilename();//a.pjg
//		            		String etc = fileName.substring(fileName.lastIndexOf("."));
//		            		String filePath=String.valueOf(System.currentTimeMillis())+etc;//改名字
//		            		String realPath1 = request.getSession().getServletContext().getRealPath("/");
//		            		realPath1 = realPath1 + "upload/"+picture.getBookid()+"/"+ filePath;
		            		String filePath="D:/secondhandbook/"+picture.getBookid()+"/"+ fileName;
		            		
		            		//filePath = "http://192.168.10.163:8080/secondhandbook/upload/"+picture.getBookid()+"/"+ filePath;
		            		String filePath1 = "http://192.168.10.163:8080/secondhandbook/upload/"+picture.getBookid()+"/"+  fileName;
							List<Object> fileTypes=new ArrayList<Object>();
				            fileTypes.add("jpg");
				            fileTypes.add("jpeg");  
				            fileTypes.add("bmp");  
				            fileTypes.add("gif");
				            
				            
			                String ext = fileName.substring(fileName.lastIndexOf(".")+1);  
			                ext = ext.toLowerCase();  
				           
			                if(fileTypes.contains(ext)){
			                	/*File saveFile=new File(filePath);
			                	if (!saveFile.getParentFile().exists())//不存在创建文件夹
			                		saveFile.getParentFile().mkdirs();
			    					file.transferTo(saveFile);//把文件放过去就行
				    				//上传成功后，地址保存到picture
				    				picture.setUrl(filePath);
				    				int j=pictureService.addPicture(picture);
				    				if(j!=0){
				    					jsonObject.addProperty("msg","sucess");
				    				}else{
				    					jsonObject.addProperty("msg","fail");
				    				}*/
			                	   FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath));
			                	   picture.setUrl(filePath1);
				    				int j=pictureService.addPicture(picture);
				    				if(j!=0){
				    					jsonObject.addProperty("msg","sucess");
				    				}else{
				    					jsonObject.addProperty("msg","fail");
				    				}
			                	}else{
				                	jsonObject.addProperty("msg","图片格式不对");
			                }
						
					}else{
						jsonObject.addProperty("msg","isEmpty");
					}
		         }
			 }
			 return jsonObject.toString(); 
		} catch (IllegalStateException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		} catch (IOException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		} catch (PictureException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		}
	}
	
	@RequestMapping(value="/readPicture",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String readPicture(@RequestParam("Picture")Picture picture){
		JsonObject jsonObject=GsonUtils.getJsonObject();
		int bookid=picture.getBookid();
		try {
			//List<String> listPicture=new ArrayList<String>();
			List<String> list=pictureService.selectPictureByBookid(bookid);
			if(!list.isEmpty()){
//				for(int i=0;i<list.size();i++){
//					//读出所有图片 URL就是路径
//					BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(list.get(i))));
//					//BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new OutputStream(listPicture.get(i))));
//					byte[] b=new byte[1024];
//					int a=0;
//					while((a=br.read())!=-1){
//						//out.write(listPicture[i]);
//					}
//				}
//				jsonObject.addProperty("msg",GsonUtils.EntityToJson(listPicture));
//				return jsonObject.toString();
				return GsonUtils.EntityToJson(list);
			}else{
				jsonObject.addProperty("msg","fail");
				return jsonObject.toString();
			}
		} catch (PictureException e) {
			jsonObject.addProperty("msg",e.getMessage());
			return jsonObject.toString();
		} 
	}
}



