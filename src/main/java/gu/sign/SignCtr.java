package gu.sign;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class SignCtr {

//    @Autowired
//    private BoardSvc boardSvc;
    
    static final Logger LOGGER = LoggerFactory.getLogger(SignCtr.class);
    
    /**
     * 결재 현황.
     */
    @RequestMapping(value = "/signList")
    public String signList() {
        
        return "sign/SignList";
    }
    
    /**
     * 새문서 리스트.
     */
    @RequestMapping(value = "/signFormList")
    public String signFormList() {
        
        return "sign/SignFormList";
    }
    
    /**
     * 기안문서.
     */
    @RequestMapping(value = "/signDocForm")
    public String signDocForm() {
        
        return "sign/SignDocForm";
    }
    
    /**
     * 결재받을문서.
     */
    @RequestMapping(value = "/signDocGet")
    public String signDocGet() {
        
        return "sign/SignDocGet";
    }
    
    /**
     * 결재할문서.
     */
    @RequestMapping(value = "/signDocDo")
    public String signDocDo() {
        
        return "sign/SignDocDo";
    }
    
   
}
