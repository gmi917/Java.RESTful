package com.ar.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ar.admin.bean.AdminMember;
import com.ar.admin.bean.AdminMemberForm;
import com.ar.admin.bean.Category;
import com.ar.admin.bean.CategoryForm;
import com.ar.admin.bean.Image;
import com.ar.admin.bean.Login;
import com.ar.admin.service.SuperManagerService;

@Controller
public class SuperManagerController {
	@Autowired
    private SuperManagerService superManagerService;
	@Autowired
	ServletContext context;
	
	@RequestMapping(value = "/allCategory", method = RequestMethod.GET)
    public ModelAndView allCategory(HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		if(login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("super")) {
				List<Category> categorys = superManagerService.getAllCategorys();
				model.setViewName("allCategorys");
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
	
	@RequestMapping(value = "/getCategory/{id}", method = RequestMethod.GET)
    public ModelAndView getCategory(@PathVariable("id") String id,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		if(login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("super")) {
				List<Category> categorys = superManagerService.getCategorybyId(id);		   	 	
		   	 	model.setViewName("allCategorys");
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
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public ModelAndView addCategory(Category category,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		String user = (String) session.getAttribute("user");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		if(login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("super")) {
				List<Category> categorys = superManagerService.insertCategory(category,user);
				model.setViewName("allCategorys");
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
	
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
    public ModelAndView updateCategory(@ModelAttribute("categoryForm") CategoryForm categoryForm,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		if(login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("super")) {
				List<Category> CategoryList = categoryForm.getCategorys();
				if(CategoryList!=null && CategoryList.size()>0) {
					for(int i=0;i<CategoryList.size();i++) {
						superManagerService.updateCategory(CategoryList.get(i));
					}
				}
				List<Category> categorys = superManagerService.getAllCategorys();		    	
		    	model.setViewName("allCategorys");
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
	
	@RequestMapping(value = "/delCategory/{id}", method = RequestMethod.GET)
    public ModelAndView delCategory(@PathVariable("id") String id,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();		
		if(login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("super")) {
				if(superManagerService.findCategoryID(id)) {
					out.println("<script language=\"javascript\">");
					out.print("alert(\"要刪除的獎品分類已經被使用;無法刪除\");location.href=\"allCategory\";");
					out.println("</script>");
				}else {
					superManagerService.deleteCategorybyId(id);
			    	List<Category> categorys = superManagerService.getAllCategorys();	
			    	model.setViewName("allCategorys");
			        model.addObject("categorys", categorys);
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
	
	@RequestMapping(value = "/adminMember")
	   public ModelAndView AdminMember() {
		ModelAndView mv  = new ModelAndView("insertAdminMember");		
		mv.addObject("adminMember", new AdminMember());
	      return mv;
	   }   
	
	@RequestMapping(value = "/allAdminMember", method = RequestMethod.GET)
    public ModelAndView allAdminMember(HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();		
		if(login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("super")) {
				List<AdminMember> adminMembers = superManagerService.getAllAdminMembers();
				model.setViewName("allAdminMembers");		        
		        model.addObject("adminMembers", adminMembers);
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
	
	@RequestMapping(value = "/getAdminMember/{id}", method = RequestMethod.GET)
    public ModelAndView getAdminMember(@PathVariable("id") String id,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();		
		if(login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("super")) {
				List<AdminMember> adminMembers = superManagerService.getAdminMemberbyId(id);		   	 	
		   	 	model.setViewName("allAdminMembers");
		        model.addObject("adminMembers", adminMembers);
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
	
	@RequestMapping(value = "/addAdminMember", method = RequestMethod.POST)
    public ModelAndView addAdminMember(AdminMember adminMember,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		String user = (String) session.getAttribute("user");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();		
		if(login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("super")) {
				if(!superManagerService.findMemberID(adminMember.getMemberID())) {
					List<AdminMember> adminMembers = superManagerService.insertAdminMember(adminMember,user);
					model.setViewName("allAdminMembers");
					model.addObject("adminMembers", adminMembers);
				}else {
					out.println("<script language=\"javascript\">");
					out.print("alert(\"重複的廠商帳號,新增失敗\");location.href=\"adminMember\";");
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
	
	@RequestMapping(value = "/updateAdminMember", method = {RequestMethod.POST ,RequestMethod.GET})
    public ModelAndView updateAdminMember(@ModelAttribute("adminMemberForm") AdminMemberForm adminMemberForm,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();	
		try {
			if(login!=null && !login.equals("") && login.equals("Y")) {
				if(role!=null && !role.equals("") && role.equals("super")) {
					List<AdminMember> adminMemberList = adminMemberForm.getAdminMembers();
					if(adminMemberList!=null && adminMemberList.size() > 0) {
						for(int i=0;i<adminMemberList.size();i++) {								
							superManagerService.updateAdminMember(adminMemberList.get(i));														
						}
					}
					List<AdminMember> adminMembers = superManagerService.getAllAdminMembers();		    	
			    	model.setViewName("allAdminMembers");
			        model.addObject("adminMembers", adminMembers);
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
		}catch(Exception e) {
			out.println(e.toString());
		}
		     
        return model;	
	}
	
	@RequestMapping(value = "/delAdminMember/{id}", method = RequestMethod.GET)
    public ModelAndView delAdminMember(@PathVariable("id") String id,HttpSession session,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		String login = (String) session.getAttribute("login");
		String role = (String) session.getAttribute("role");
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();	
		String filepath = context.getRealPath("")+"img\\";
		String oldfilename = "";
		if(login!=null && !login.equals("") && login.equals("Y")) {
			if(role!=null && !role.equals("") && role.equals("super")) {
				List<AdminMember> adminMember = superManagerService.getAdminMemberbyId(id);										
				List<Image> oldImg = superManagerService.getImgbyId(adminMember.get(0).getMemberID());				
				if(oldImg!=null && oldImg.size()>0) {
					for(int i=0;i<oldImg.size();i++) {						
						String temp[] = oldImg.get(i).getImage().replaceAll("\\\\","/").split("/");			
						if (temp.length > 1) {
							oldfilename = temp[temp.length - 1];
						}						
						if(new File(filepath+oldfilename).exists()) {
							new File(filepath+oldfilename).delete();
						}
					}
				}
				superManagerService.deleteAdminMemberbyId(id);	
				superManagerService.deletePrizebyUser(adminMember.get(0).getMemberID());
				List<AdminMember> adminMembers = superManagerService.getAllAdminMembers();		    	
		    	model.setViewName("allAdminMembers");
		        model.addObject("adminMembers", adminMembers);
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
