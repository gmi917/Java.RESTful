package com.ar.json.bean;

import java.util.ArrayList;
import java.util.List;

public class OnePage {
	int TotalpageNum=1;
	int curpage=1;
	int endPageNum=1;
//	int itemNumPerPage=5;  
	public List OnePage(List data, String page,int itemNumPerPage) {
		List pagedata = new ArrayList();
		UserPrize userPrizedata = null;
		int totalNum = data.size();
	    if (totalNum<=itemNumPerPage) {
	      this.TotalpageNum=1;
	    }else {
	      if (totalNum % itemNumPerPage == 0) {
	        this.TotalpageNum = totalNum / itemNumPerPage;
	      }
	      else {
	        this.TotalpageNum = (totalNum / itemNumPerPage) + 1;
	      }
	    }
	    if (page!=null && !page.equals("")) {
	        this.curpage = Integer.parseInt(page);
	      }else {
	        this.curpage = 1;
	      }
	      int beginNr = (this.curpage-1)*itemNumPerPage+1;
	      int endNr = this.curpage*itemNumPerPage;
	      if (endNr>totalNum)	endNr=totalNum;
	      for (int i=0;i<endNr;i++) {
	    	  userPrizedata= new  UserPrize();
	    	  userPrizedata = (UserPrize) data.get(i);
	          pagedata.add(userPrizedata);
	      }
	      return pagedata;
	}
	//結束的頁碼
	  public int getendPageNum() {		  
	        if (this.TotalpageNum>endPageNum) {
	        	endPageNum=this.TotalpageNum;
	        }else {
	        	endPageNum = 1;
	        }	       
	    return endPageNum;
	  }

	  //開始的頁碼
	  public int getbeginPageNum() {
	    int beginPageNum=0;
	    if (this.curpage==1) {
	      beginPageNum=1;
	    }else {
	      beginPageNum=this.endPageNum;	      
	    }
	    return beginPageNum;
	  }

	  //目前所在的頁碼
	  public int getCurpage() {
	    return this.curpage;
	  }
	  //前一頁頁碼
	  public String getPrevPage() {
	    if (curpage==1) {
	      return "0";
	    }else if (curpage-1<0) {
	      return "0";
	    }else {
	      return String.valueOf(curpage - 1);
	    }
	  }
	  //下一頁頁碼
	  public String getNextPage() {
	    if (this.curpage >= this.TotalpageNum) {
	      return "0";
	    }
	    return String.valueOf(curpage+1);
	  }

}
