package gu.admin.sign;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import gu.common.UtilEtc;

@Controller
public class AdSgnFormCtr {

    @Autowired
    private AdSgnFormSvc adSgnFormSvc; 

    /**
     * 리스트.
     */
    @RequestMapping(value = "/adSignFormList")
    	public String adSignFormList(ModelMap modelMap) {
    	
    	List<?> listview = adSgnFormSvc.selectAdSignFormList();
    	
    	modelMap.addAttribute("listview", listview);
    	
    	return "admin/sign/AdSgnFormList";
    }
    
    /**
     * 리드.
     */
    @RequestMapping(value = "/adSignFormRead")
    	public String adSignFormRead(ModelMap modelMap, AdSgnFormVO adSgnFormVO) {
    	
    	AdSgnFormVO sgnFormInfo = adSgnFormSvc.selectAdSignFormOne(adSgnFormVO);
    	
    	modelMap.addAttribute("sgnFormInfo", sgnFormInfo);
    	
    	return "admin/sign/AdSgnFormRead";
    }
    	
    /**
     * 저장.
     */ 
    @RequestMapping(value = "/adSignForm")
	public String adSignForm() {
	
    	return "admin/sign/AdSgnForm";
	
    }
    
    @RequestMapping(value = "/adSignFormSave")
       public String adSignFormSave(HttpServletResponse response, AdSgnFormVO sgnInfo) {
        
    	adSgnFormSvc.insertAdSignForm(sgnInfo);
    	
    	return "redirect:adSignFormList";
    }
    
    /**
     * 수정.
     */ 
    @RequestMapping(value = "/adSignFormModify")
       public String adSignFormModify(ModelMap modelMap, AdSgnFormVO sgnInfo) {
        
    		if(sgnInfo.getFrmno() != null) {
    			
    			sgnInfo = adSgnFormSvc.selectAdSignFormOne(sgnInfo);
    			
    			modelMap.addAttribute("sgnInfo", sgnInfo);
    			
    		}
    		return "admin/sign/AdSgnForm";
       }
    
    /**
     * 삭제.
     */
    @RequestMapping(value = "/adSignFormDelete")
       public String adSignFormDelete(HttpServletRequest request, HttpServletResponse response) {
        
    	String frmno = request.getParameter("frmno");
        
    	adSgnFormSvc.deleteAdSignForm(frmno);
    	UtilEtc.responseJsonValue(response, "OK");
        
    	return "redirect:adSignFormList";
    }

}
