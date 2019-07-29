package com.ar.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ar.admin.bean.AdminMember;
import com.ar.admin.bean.Category;
import com.ar.admin.bean.ChangePassword;
import com.ar.admin.bean.Login;
import com.ar.admin.bean.Prize;
import com.ar.admin.service.ManagerService;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
@Controller
public class ManagerController {
	@Autowired
    private ManagerService managerService;
	@Autowired
	ServletContext context;
	
	@RequestMapping(value = "/allPrize", method = RequestMethod.GET)
    public ModelAndView allPrize(HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String user = (String) session.getAttribute("user");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		if(user!=null && !user.equals("") && login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("admin")) {
				List<Prize> prizes = managerService.getAllPrizes(user);
				model.setViewName("allPrizes");
		        model.addObject("prizes", prizes);
			}else {
				out.println("<script language=\"javascript\">");
				out.print("alert(\"您沒有權限\");");
				out.println("</script>");
				return null;
			}	
			
		}else {
			model.setViewName("login");
			model.addObject("login", new Login());		    
		}        
        return model;
    }
	
	@RequestMapping(value = "/getPrize/{id}", method = RequestMethod.GET)
    public ModelAndView getPrize(@PathVariable("id") String id,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String user = (String) session.getAttribute("user");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		if(user!=null && !user.equals("") && login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("admin")) {
				List<Category> categorys = managerService.getAllCategorys(); 
				List<Prize> prizes = managerService.getPrizebyId(id);
				model.setViewName("Prizes");
		        model.addObject("prizes", prizes);
		        model.addObject("categorys", categorys);
			}else {
				out.println("<script language=\"javascript\">");
				out.print("alert(\"您沒有權限\");");
				out.println("</script>");
				return null;
			}	
			
		}else {
			model.setViewName("login");
			model.addObject("login", new Login());
		}
        return model;
	}
	
	@RequestMapping(value = "/Prize")
	   public ModelAndView Prize(HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String user = (String) session.getAttribute("user");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		PrintWriter out = response.getWriter();
		ModelAndView model = new ModelAndView();
		if(user!=null && !user.equals("") && login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("admin")) {
				List<Category> categorys = managerService.getAllCategorys();
				if(categorys!=null && categorys.size()>0) {
					model.setViewName("insertPrize");
					model.addObject("categorys", categorys);
					model.addObject("prizes", new Prize());
				}else {
					out.println("<script language=\"javascript\">");
					out.print("alert(\"目前資料庫還未建立獎品分類,所以無法新增獎品\");");
					out.println("</script>");
					return null;
				}
			}else {
				out.println("<script language=\"javascript\">");
				out.print("alert(\"您沒有權限\");");
				out.println("</script>");
				return null;
			}						
		}else {
			model.setViewName("login");
			model.addObject("login", new Login());
		}	
	      return model;
	   }
	
	@RequestMapping(value = "/addPrize", method = RequestMethod.POST)
    public ModelAndView addPrize(@ModelAttribute("uploadForm") Prize prize,HttpSession session,HttpServletResponse response) throws IllegalStateException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String user = (String) session.getAttribute("user");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		long stamp = System.currentTimeMillis();
		if(user!=null && !user.equals("") && login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("admin")) {
				List<MultipartFile> files = prize.getImages();			
				if (files != null && files.size() > 0) {
					for(MultipartFile multipartFile :files) {
						String oldFileName = multipartFile.getOriginalFilename();
						String ext = FilenameUtils.getExtension(oldFileName);						                
						String id = managerService.getPrizeID();
		                File imageFile = new File(context.getRealPath("")+"/img", id+"_"+stamp+"."+ext);
			            multipartFile.transferTo(imageFile);
			            prize.setImage("/AR_admin/img/"+id+"_"+stamp+"."+ext);		            
			            managerService.insertPrize(prize, user);
			        }
				}	        			
				List<Prize> prizes = managerService.getAllPrizes(user);
				model.setViewName("allPrizes");
		        model.addObject("prizes", prizes);
			}else {
				out.println("<script language=\"javascript\">");
				out.print("alert(\"您沒有權限\");");
				out.println("</script>");
				return null;
			}	
			
		}else {
			model.setViewName("login");
			model.addObject("login", new Login());
		}		
    	return model;
	}
	
	@RequestMapping(value = "/updatePrize", method = RequestMethod.POST)	
    public ModelAndView updatePrize(@ModelAttribute("prizerForm") Prize prize,HttpSession session,HttpServletResponse response) throws IllegalStateException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String user = (String) session.getAttribute("user");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		String filepath = context.getRealPath("")+"img\\";
		String oldfilename = "",oldImg="",id="";
		long stamp = System.currentTimeMillis();		
		if(user!=null && !user.equals("") && login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("admin")) {
				List<MultipartFile> files = prize.getImages();			
				if (files != null && files.size() > 0) {
					for(MultipartFile multipartFile :files) {
						String OriginalFilename = multipartFile.getOriginalFilename();
						id = prize.getID();
						if(OriginalFilename!=null && !OriginalFilename.equals("")) {						
							oldImg = managerService.getImgbyId(id);					
							String temp[] = oldImg.replaceAll("\\\\","/").split("/");			
							if (temp.length > 1) {
								oldfilename = temp[temp.length - 1];
							}							
							String ext = FilenameUtils.getExtension(OriginalFilename);						
							if(new File(filepath+oldfilename).exists()) {
								new File(filepath+oldfilename).delete();
							}
							File imageFile = new File(context.getRealPath("")+"/img", id+"_"+stamp+"."+ext);
				            multipartFile.transferTo(imageFile);
				            prize.setImage("/AR_admin/img/"+id+"_"+stamp+"."+ext);
				            List<Prize> prizes = managerService.updatePrize(prize);	
						}else {
							oldImg = managerService.getImgbyId(id);
							prize.setImage(oldImg);						
							List<Prize> prizes = managerService.updatePrize(prize);	
						}
					}
				}   
				out.println("<script type=\"text/javascript\">");
				out.print("alert(\"修改成功\");location.href=\"previewPrize/"
						+ prize.getID() + "\";");
				out.println("</script>");
				return null;
			}else {
				out.println("<script language=\"javascript\">");
				out.print("alert(\"您沒有權限\");");
				out.println("</script>");
				return null;
			}	
			
		}else {
			model.setViewName("login");
			model.addObject("login", new Login());
		}
        return model;
	}
	
	@RequestMapping(value = "/delPrize/{id}", method = RequestMethod.GET)
    public ModelAndView delPrize(@PathVariable("id") String id,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String user = (String) session.getAttribute("user");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		String filepath = context.getRealPath("")+"img\\";
		String oldfilename = "",oldImg="";
		if(user!=null && !user.equals("") && login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("admin")) {
				oldImg = managerService.getImgbyId(id);					
				String temp[] = oldImg.replaceAll("\\\\","/").split("/");			
				if (temp.length > 1) {
					oldfilename = temp[temp.length - 1];
				}			
				if(new File(filepath+oldfilename).exists()) {
					new File(filepath+oldfilename).delete();
				}
				managerService.deletePrizebyId(id);
		    	List<Prize> prizes = managerService.getAllPrizes(user);	
		    	model.setViewName("allPrizes");
		        model.addObject("prizes", prizes);
			}else {
				out.println("<script language=\"javascript\">");
				out.print("alert(\"您沒有權限\");");
				out.println("</script>");
				return null;
			}	
			
		}else {
			model.setViewName("login");
			model.addObject("login", new Login());
		}
		return model;
	}
	
	@RequestMapping(value = "/previewPrize/{id}", method = RequestMethod.GET)
    public ModelAndView previewPrize(@PathVariable("id") String id,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String user = (String) session.getAttribute("user");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		if(user!=null && !user.equals("") && login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("admin")) {
				List<Category> categorys = managerService.getAllCategorys(); 
				List<Prize> prizes = managerService.getPrizebyId(id);
				model.setViewName("previewPrize");
		        model.addObject("prizes", prizes);
		        model.addObject("categorys", categorys);
			}else {
				out.println("<script language=\"javascript\">");
				out.print("alert(\"您沒有權限\");");
				out.println("</script>");
				return null;
			}	
			
		}else {
			model.setViewName("login");
			model.addObject("login", new Login());
		}
        return model;
	}
	
	@RequestMapping(value = "/changePassword", method = {RequestMethod.POST ,RequestMethod.GET})
	public ModelAndView changePassword(ChangePassword changePassword,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String user = (String) session.getAttribute("user");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		if(user!=null && !user.equals("") && login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("admin")) {
				if(changePassword.getOldPWD()!=null && !changePassword.getOldPWD().equals("") &&
						changePassword.getNewPWD()!=null && !changePassword.getNewPWD().equals("")) {
					String user_pwd = managerService.getPWDbyId(user);				
					String oldpassword_md5 = md5.md5(changePassword.getOldPWD()).toUpperCase();
					if(oldpassword_md5.equals(user_pwd.toUpperCase())) {
						String newpassword_md5 = md5.md5(changePassword.getNewPWD());
						managerService.updatePWD(user, newpassword_md5.toUpperCase());					
						out.println("<script language=\"javascript\">");
						out.print("alert(\"密碼更改成功\");");
						out.println("</script>");
						return null;
					}else {
						out.println("<script language=\"javascript\">");
						out.print("alert(\"輸入的舊密碼錯誤\");location.href=\"changePassword\";");
						out.println("</script>");
						return null;
					}
				}else {
					model.setViewName("changePassword");
					model.addObject("changePassword", new ChangePassword());
				}
			}else {
				out.println("<script language=\"javascript\">");
				out.print("alert(\"您沒有權限\");");
				out.println("</script>");
				return null;
			}	
						
		}else {
			model.setViewName("login");
			model.addObject("login", new Login());
		}
		return model;
	}
}
