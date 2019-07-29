package com.ar.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ar.admin.bean.Login;
import com.ar.admin.service.LoginService;

@Controller
//@SessionAttributes({"user","Role","login"}) 
public class LoginController {
	@Autowired
    private LoginService loginservice;
	private static final int WIDTH = 100;
    private static final int HEIGHT = 30;
    private static final int LENGTH = 4;
    public static final int LINECOUNT = 20;
    
    private static final String str="23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static Random random = new Random();

	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView login(Login login,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			String rand = (String) session.getAttribute("newCode");
			if(login.getMemberID()!=null && !login.getMemberID().equals("") && login.getMemberPWD()!=null
					&& !login.getMemberPWD().equals("") && rand!=null ) {
				String pwd_md5 = md5.md5(login.getMemberPWD());
				List<Login> user = loginservice.getUserbyId(login.getMemberID(), pwd_md5);
				if(user!=null && user.size()>0 && rand.toUpperCase().equals(login.getUsercode().toUpperCase())) {
					if(user.get(0).getRole().equals("super")) {
						ModelAndView mv = new ModelAndView("SuperManager");
						session.setAttribute("user", user.get(0).getMemberID());
						session.setAttribute("role", user.get(0).getRole());
						session.setAttribute("login", "Y");
				        return mv;
					}else {
						ModelAndView mv = new ModelAndView("Manager");
						session.setAttribute("user", user.get(0).getMemberID());
						session.setAttribute("role", user.get(0).getRole());
						session.setAttribute("login", "Y");
				        return mv;
					}					
				}else {
					out.println("<script language=\"javascript\">");
					out.print("alert(\"帳號、密碼或驗證碼錯誤\");location.href=\"login\";");
					out.println("</script>");
					return null;
				}
			}else {
				ModelAndView mv  = new ModelAndView();
				mv.setViewName("login");
				mv.addObject("login", new Login());
			    return mv;
			}
		}catch(Exception e) {
			out.println(e.toString());
		}
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("login", new Login());
	    return mv;		
	}  		
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("role");
		session.removeAttribute("login");
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("login", new Login());
	    return mv;
	}
	
	@RequestMapping(value = "/Image")
	public void Image(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
//        Color color = new Color(200,151,255);//顏色生成
//        g.setColor(color);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        String code=drawChar(g);
        for(int i=0;i<LINECOUNT;i++) {
        	drawLine(g);
        }

        request.getSession().setAttribute("newCode", code);//存至session便於後續驗證
        g.dispose();
        ImageIO.write(image, "JPEG", response.getOutputStream());//通過ImageIO輸出圖片，並傳至前台
	}
	
	public Color getColor() {
		return new Color(random.nextInt(255),random.nextInt(255),
				random.nextInt(255));
	}
	
	public Font getFont() {
		return new Font("Times New Roman",Font.CENTER_BASELINE,20);
	}
	
	public String drawChar(Graphics g) {
		String code="";
		g.setFont(getFont());
		for(int i=0;i<LENGTH;i++) {
			char c=str.charAt(random.nextInt(str.length()));
			g.setColor(getColor());
			g.drawString(c+"", 20*i+10,20);
			code=code+c;
		}
		return code;
	}
	public void drawLine(Graphics g) {
		int x=random.nextInt(WIDTH);
		int y=random.nextInt(HEIGHT);
		int x1=random.nextInt(13);
		int y1=random.nextInt(15);
		g.setColor(getColor());
		g.drawLine(x, y, x+x1, y+y1);
	}


}
