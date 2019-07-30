package com.ar.json;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ar.admin.bean.Category;
import com.ar.json.bean.AllPrizeList;
import com.ar.json.bean.DetailPrize;
import com.ar.json.bean.MemberNumber;
import com.ar.json.bean.OnePage;
import com.ar.json.bean.PageContent;
import com.ar.json.bean.PageInfo;
import com.ar.json.bean.PrizeOrder;
import com.ar.json.bean.RatingStarPrize;
import com.ar.json.bean.TotalPoint;
import com.ar.json.bean.UserLoginLog;
import com.ar.json.bean.UserPrize;
import com.ar.json.service.UserService;

@Controller
public class ListPrizeController {
	@Autowired
    private UserService userService;
	
	@RequestMapping(value="/UserfindallCategory",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Category> UserfindallCategory(){
		return userService.getAllCategory();
	}
	
	@RequestMapping(value="/UsergetPrizebyCategoryId/{id}",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<UserPrize> UsergetPrizebyCategoryId(@PathVariable("id") String id){
		return userService.getPrizebyCategoryId(id);
	}
	
	@RequestMapping(value="/UsergetPrizebyPage/{pageid}/{pagesize}",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<UserPrize> UsergetPrizebyPage(@PathVariable("pageid") String pageid,@PathVariable("pagesize") int pagesize){
		OnePage onePage = new OnePage();
		List content = userService.getAllPrizes();
		List onePageContent = onePage.OnePage(content, pageid,pagesize);
//		PageContent page = new PageContent();
//		page.setFirst(Integer.toString(onePage.getbeginPageNum()));
//		page.setPrev(onePage.getPrevPage());
//		page.setNext(onePage.getNextPage());
//		page.setLast(Integer.toString(onePage.getendPageNum()));
//		page.setContent(content);
		return onePageContent;
	}
	
	@RequestMapping(value="/UsergetPageInfo/{curpage}/{pagesize}",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<PageInfo> UsergetPageInfo(@PathVariable("curpage") String curpage,@PathVariable("pagesize") int pagesize){
		List pagedata = new ArrayList();
		OnePage onePage = new OnePage();
		List content = userService.getAllPrizes();
		List onePageContent = onePage.OnePage(content, curpage,pagesize);
		PageInfo pageInfo = new PageInfo(); 
		pageInfo.setPrevPage(onePage.getPrevPage());
		pageInfo.setNextPage(onePage.getNextPage());
		pageInfo.setEndPageNum(Integer.toString(onePage.getendPageNum()));
		pagedata.add(pageInfo);
		return pagedata;
	}
	
	@RequestMapping(value="/UserfindallPrize",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<UserPrize> UserfindallPrize(){
		return userService.getAllPrizes();
	}
	
	@RequestMapping(value="/UserfindallPrizeList",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<AllPrizeList> UserfindallPrizeList(){
		return userService.getAllPrizeList();
		
	}
	
	@RequestMapping(value = "/UsergetPrizeDetailbyId/{id}",method = RequestMethod.GET,produces="application/json")
	public @ResponseBody List<DetailPrize> UsergetPrizebyId(@PathVariable("id") String id){
		return userService.getPrizeDetailbyId(id);
	}
	
	@RequestMapping(value = "/UsergetTotalPoint/{username}",method = RequestMethod.GET,produces="application/json")
	public @ResponseBody List<TotalPoint> UsergetTotalPoint(@PathVariable("username") String username){
		return userService.getTotalPointbyUsername(username);
	}
	
	@RequestMapping(value = "/UserExchangePrize",method = RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody String UserExchangePrize(@RequestBody PrizeOrder prizeOrder){
		boolean status=userService.insertPrizeOrder(prizeOrder.getUsername(),prizeOrder.getPrizeId(),prizeOrder.getPrizePoint());
		if(status) {
			return "{\"result\":\"0\",\"message\":\"success\"}";			
		}else {
			return "{\"result\":\"1\",\"message\":\"failed\"}";			
		}
	}
	
	@RequestMapping(value = "/UserRatingStarPrize",method = RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody String UserRatingStarPrize(@RequestBody RatingStarPrize ratingStarPrize){
		int insertValue=userService.insertRatingStarPrize(ratingStarPrize.getUserAccount(),ratingStarPrize.getPrizeID(),ratingStarPrize.getRatingStar(),ratingStarPrize.getComment());
		if(insertValue>=1) {
			return "{\"result\":\"0\",\"message\":\"success\"}";			
		}else {
			return "{\"result\":\"1\",\"message\":\"failed\"}";			
		}
	}
	
	@RequestMapping(value = "/UsergetPrizeTotalCount",method = RequestMethod.GET,produces="application/json")
	public @ResponseBody String UsergetPrizeTotalCount(){
		String prizeCount=userService.getPrizeCount();
		return "{\"result\":\"0\",\"value\":\""+prizeCount+"\"}";
	}
	
	@RequestMapping(value = "/checkMemberNumber",method = RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody String checkMemberNumber(@RequestBody MemberNumber memberNumber){
		if(memberNumber.getPrizeId()!=null && !memberNumber.getPrizeId().equals("")
				&& memberNumber.getMemberNumber()!=null && !memberNumber.getMemberNumber().equals("")) {
			boolean checkNumber=userService.checkMemberNumber(memberNumber.getPrizeId(), memberNumber.getMemberNumber());
			if(checkNumber) {
				return "{\"result\":\"0\",\"message\":\"success\"}";			
			}else {
				return "{\"result\":\"1\",\"message\":\"failed\"}";			
			}
		}else {
			return "{\"result\":\"1\",\"message\":\"parameter is null\"}";	
		}		
	}
	
	@RequestMapping(value = "/UseLoginLog",method = RequestMethod.POST,consumes="application/json")
	public @ResponseBody void UseLoginLog(@RequestBody UserLoginLog userLoginLog){
		if(userLoginLog.getUserAccount()!=null && !userLoginLog.getUserAccount().equals("")) {
			userService.insertUserLoginLog(userLoginLog.getUserAccount());			
		}
	}
}
