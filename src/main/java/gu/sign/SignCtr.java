package gu.sign;


import java.util.List;

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
    public String signDocForm(ModelMap modelMap, SignFormVO signFormVO, SignDocVO signDocInfo) {
    	
    	SignFormVO signFormInfo = signSvc.selectSignFormOne(signFormVO);
    	
    	modelMap.addAttribute("signFormInfo", signFormInfo);
    	modelMap.addAttribute("signDocInfo", signDocInfo);
        
        return "sign/SignDocForm";
    }
    
    /**
     * 기안문서 저장 및 임시저장.
     */
    @RequestMapping(value = "/signDocSave")
    public String signDocSave(HttpServletResponse response, SignDocVO signFormInfo) {
	     
		signSvc.insertSignDoc(signFormInfo);
    	if(signFormInfo.getSdstate() == "1") { // 임시저장
    	 	
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
    public String signDocGetList() {
        
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
