package cqupt.controller;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import cqupt.exception.BookException;
import cqupt.exception.PictureException;
import cqupt.po.Book;
import cqupt.po.Picture;
import cqupt.service.BookService;
import cqupt.service.PictureService;
import cqupt.service.impl.BookServiceImp;
import cqupt.utils.GsonUtils;

@Controller
@RequestMapping("/upload")
public class UploadController {
	@Resource(name="pictureServiceImp")
	private PictureService pictureService;
 
    /***
     * 保存文件
     *
     * @param file
     * @return
     */
	@RequestMapping(value="/uploadFile",produces="text/html;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public String uploadPicture(HttpServletRequest request,@RequestParam("files")MultipartFile[] files){//需要bookid
		JsonObject jsonObject=GsonUtils.getJsonObject();
		Picture picture =new Picture();
		picture.setBookid(18);
		try {
			 if (files != null && files.length > 0) {
		            for (int i = 0; i< files.length; i++) {
		            	MultipartFile file = files[i];
		            	if(!file.isEmpty()){
							//String filePath="D:\\TomCat\\picture"+"\\"+picture.getBookid()+"\\"+file.getOriginalFilename();
							String filePath = request.getSession().getServletContext().getRealPath("/") + "upload\\" + picture.getBookid()+"\\"+file.getOriginalFilename();
							 System.out.println(filePath);
							List<Object> fileTypes=new ArrayList<Object>();
				            fileTypes.add("jpg");
				            fileTypes.add("jpeg");  
				            fileTypes.add("bmp");  
				            fileTypes.add("gif");
				            
				            String fileName = file.getOriginalFilename();
			                String ext = fileName.substring(fileName.lastIndexOf(".")+1);  
			                ext = ext.toLowerCase();  
				             
			                
			                if(fileTypes.contains(ext)){
			                	File saveFile=new File(filePath);
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
//    private String saveFile(HttpServletRequest request, MultipartFile file) {
//        // 判断文件是否为空
//        if (!file.isEmpty()) {
//            try {
//                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中  )
//                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload\\" + file.getOriginalFilename();
//            	//String filePath="D:\\TomCat\\picture\\"+file.getOriginalFilename();
//                System.out.println(filePath);
//                
//                
//                List<Object> fileTypes=new ArrayList<Object>();
//                fileTypes.add("jpg");
//                fileTypes.add("jpeg");  
//                fileTypes.add("bmp");  
//                fileTypes.add("gif");
//                
//                String fileName = file.getOriginalFilename();
//                String ext = fileName.substring(fileName.lastIndexOf(".")+1);  
//                //对扩展名进行小写转换  
//                ext = ext.toLowerCase();  
//                if(fileTypes.contains(ext)){
//                	File saveDir = new File(filePath);
//                    if (!saveDir.getParentFile().exists())//不存在
//                        saveDir.getParentFile().mkdirs();//用.mkdirs()生成父目录文件夹 getParentFile()返回File，getParent返回String
////                    mkdir（）创建此抽象路径名称指定的目录（及只能创建一级的目录，且需要存在父目录）
////                    mkdirs（）创建此抽象路径指定的目录，包括所有必须但不存在的父目录。（及可以创建多级目录，无论是否存在父目录）
//                    // 转存文件
//                    file.transferTo(saveDir);
//                    
//                    Picture picture=new Picture();
//                    picture.setUrl(filePath);
//                    picture.setBookid(18);
//                    pictureService.addPicture(picture);
//                    return "sucess";
//                }else{
//                	return "fail";
//                }
//                
//            } catch (Exception e) {
//                return e.getMessage();
//            }
//        }else{
//        	return "fail";
//        }
//    }
    /**
     * 上传图片
     *
     * @param files
     * @param request
     * @return
     */
//    @RequestMapping("/uploadPicture")
//    @ResponseBody
//    public String filesUpload(@RequestParam("myfiles") MultipartFile[] files,
//            HttpServletRequest request) {
//        if (files != null && files.length > 0) {
//            for (int i = 0; i < files.length; i++) {
//                MultipartFile file = files[i];
//                // 保存文件
//                saveFile(request, file);
//            }
//        }
//         
//        // 重定向
//        return "redirect:/upload/uploadFile";
//    }
    @RequestMapping("/readPicture")
    @ResponseBody
   // @Autowired
    public void readPicture(HttpServletResponse response){
    	try {
	    	List<String> listPicture=new ArrayList<String>();
			List<String> list;
			
				list = pictureService.selectPictureByBookid(1);
			
			if(!list.isEmpty()){
				for(int i=0;i<list.size();i++){
					//读出所有图片 URL就是路径
					BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(list.get(i))));
					//BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new OutputStream(listPicture.get(i))));
					byte[] b=new byte[1024];
					int a=0;
					while((a=br.read())!=-1){
						//out.write(listPicture[i]);
						//response.getWriter().write(b);
					OutputStream out=new OutputStream() {
						
						@Override
						public void write(int b) throws IOException {
							// TODO Auto-generated method stub
							
						}
					};
					}
				}
			}
    	} catch (PictureException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
