package gu.survey;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import gu.board.BoardSearchVO;
import gu.common.Util4calen;
import gu.etc.EtcSvc;

@Controller 
public class SurveyCtr {  

    static final Logger LOGGER = LoggerFactory.getLogger(SurveyCtr.class);
    
    @Autowired
    private EtcSvc etcSvc;
    
    @Autowired
    private SurveySvc surveySvc;
    
    /**
     * 리스트.
     */
    @RequestMapping(value = "/surveyList")
    public String surveyList(HttpServletRequest request, BoardSearchVO searchVO, ModelMap modelMap) {
        String userno = request.getSession().getAttribute("userno").toString();
        
        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        // -----------------------------------------
        
        searchVO.pageCalculate( surveySvc.selectSurveyCount(searchVO) ); // startRow, endRow
        List<?> listview  = surveySvc.selectSurveyList(searchVO);
        
        modelMap.addAttribute("searchVO", searchVO);
        modelMap.addAttribute("listview", listview);
        
        return "survey/SurveyList";
    }
    
    @RequestMapping(value = "/surveyRead")
    public String boardRead(HttpServletRequest request, ModelMap modelMap) {
        String userno = request.getSession().getAttribute("userno").toString();
        
        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        // -----------------------------------------
    	
        String surno = request.getParameter("surno");
        
        SurveyVO surveyVO = surveySvc.getSurvey(surno);
        modelMap.addAttribute("result",surveyVO);
        return "survey/SurveyRead";
    }
    
    @RequestMapping(value = "/surveyForm")
    public String boardForm(HttpServletRequest request, ModelMap modelMap){
    	 String userno = request.getSession().getAttribute("userno").toString();
         
         Integer alertcount = etcSvc.selectAlertCount(userno);
         modelMap.addAttribute("alertcount", alertcount);
         
         String surno = request.getParameter("surno");
         String today = Util4calen.date2Str(Util4calen.getToday());
         
         modelMap.addAttribute("surno",surno);
         modelMap.addAttribute("today", today);
         
         return "survey/SurveyForm";
    }
    
    /**
     * 글 저장.
     */
    @RequestMapping(value = "/surveySave")
    public String boardSave(HttpServletRequest request, SurveyVO surveyInfo) {
        String userno = request.getSession().getAttribute("userno").toString();
        surveyInfo.setUserno(userno);

        surveySvc.insertSurvey(surveyInfo);
        
        return "redirect:/surveyList?bgno=" + surveyInfo.getBgno();
    }
}
