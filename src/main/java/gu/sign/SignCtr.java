package gu.sign;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import gu.sign.SignFormVO;

@Controller 
public class SignCtr {

    @Autowired
    private SignSvc signSvc;
    
    static final Logger LOGGER = LoggerFactory.getLogger(SignCtr.class);
    
    /**
     * 결재현황.
     */
    @RequestMapping(value = "/signList")
    public String signList() {
        
        return "sign/SignList";
    }
    
    /**
     * 새문서리스트.
     */
    @RequestMapping(value = "/signFormList")
    public String signFormList(ModelMap modelMap) {
        
    	List<?> listview = signSvc.selectSignFormList();
    	
    	modelMap.addAttribute("listview", listview);
    	
        return "sign/SignFormList";
    }
    
    /**
     * 기안문서.
     */
    @RequestMapping(value = "/signDocForm")
    public String signDocForm(HttpServletRequest request, ModelMap modelMap, SignDocVO signDocVO) {
    	String frmno = request.getParameter("frmno");
    	// String sdno = request.getParameter("sdno");
    	
    	SignDocVO signDocInfo = new SignDocVO();
    	
    	if (frmno!=null) {
    		SignFormVO signFormInfo = signSvc.selectSignFormOne(frmno);
    		signDocInfo.setFrmno(signFormInfo.getFrmno());
    		signDocInfo.setSdcontents(signFormInfo.getFrmcontents());
    	} else{
    		signDocInfo = signSvc.selectSignDocOne(signDocVO);
    	}
    	
    	modelMap.addAttribute("signDocInfo", signDocInfo);
        
        return "sign/SignDocForm";
    }
    
    /**
     * 기안문서 저장 및 임시저장.
     */
    @RequestMapping(value = "/signDocSave")
    public String signDocSave(HttpServletResponse response, HttpServletRequest request, SignDocVO signDocInfo) {
    	String userno = request.getSession().getAttribute("userno").toString();
    	String sdstate = request.getParameter("sdstate");
    	
    	signDocInfo.setUserno(userno);
    	signDocInfo.setSdstate(sdstate);
    	
		signSvc.insertSignDoc(signDocInfo);
    	if("1".equals(signDocInfo.getSdstate())) { // 임시저장
    	 	
    	 	return "redirect:signDocTempList";
    	 	
    	}
    		
    	return "redirect:signDocGetList"; // 저장
    		
    	
	 }
    
    /**
     * 임시저장 리스트.
     */
    @RequestMapping(value = "/signDocTempList")
    public String signDocTempList(ModelMap modelMap) {
    	List<?> listview = signSvc.selectSignDocTempList();
    	
    	modelMap.addAttribute("listview", listview);
        
        return "sign/SignDocTempList";
    }
    
    /**
     * 결재받을문서리스트.
     */
    @RequestMapping(value = "/signDocGetList")
    public String signDocGetList(ModelMap modelMap) {
        List<?> listview = signSvc.selectSignDocGetList();
        
        modelMap.addAttribute("listview", listview);
        
        return "sign/SignDocGetList";
    }
    
    /**
     * 결재받을문서.
     */
    @RequestMapping(value = "/signDocGet")
    public String signDocGet() {
        
        return "sign/SignDocGet";
    }
    
    /**
     * 결재할문서리스트.
     */
    @RequestMapping(value = "/signDocDoList")
    public String signDocDoList() {
        
        return "sign/SignDocDoList";
    }
    
    /**
     * 결재할문서.
     */
    @RequestMapping(value = "/signDocDo")
    public String signDocDo() {
        
        return "sign/SignDocDo";
    }
    
   
}
