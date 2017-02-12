package gu.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import gu.schedule.ScheduleSearchVO;
import gu.schedule.ScheduleSvc;
import gu.admin.board.BoardGroupVO;
import gu.board.BoardVO;
import gu.common.Field3VO;
import gu.common.MakeExcel;
import gu.common.Util4calen;
import gu.etc.EtcSvc;


@Controller
public class ScheduleCtr {

    @Autowired
    private EtcSvc etcSvc;

    @Autowired
    private ScheduleSvc scheduleSvc;

    /**
     * List & Excel 사용 샘플.
     */
    @RequestMapping(value = "/scheduleDay")
    public String scheduleDay(HttpServletRequest request, ScheduleSearchVO searchVO, ModelMap modelMap) {
        String userno = request.getSession().getAttribute("userno").toString();
        String schno = request.getParameter("schno");
        
        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        // -----------------------------------------
        
        Field3VO f3 = new Field3VO(schno, userno, null);
        ////////////////////////////////////
        //	페이지 계산
        ////////////////////////////////////
        searchVO.pageCalculate( scheduleSvc.selectScheduleCount(searchVO) ); // startRow, endRow
        
        
        List<?> listview  = scheduleSvc.selectScheduleList(searchVO);
        ScheduleVO scheduleInfo = scheduleSvc.selectScheduleOne(f3);
        
        modelMap.addAttribute("searchVO", searchVO);
        modelMap.addAttribute("listview", listview);
        modelMap.addAttribute("scheduleInfo", scheduleInfo);

        return "schedule/ScheduleDay";
    }
    
    @RequestMapping(value = "/scheduleMonth")
    public String scheduleMonth(HttpServletRequest request, ScheduleSearchVO searchVO, ModelMap modelMap) {
        String userno = request.getSession().getAttribute("userno").toString();

        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        // -----------------------------------------

        searchVO.pageCalculate( scheduleSvc.selectScheduleCount(searchVO) ); // startRow, endRow
        List<?> listview  = scheduleSvc.selectScheduleList(searchVO);

        modelMap.addAttribute("searchVO", searchVO);
        modelMap.addAttribute("listview", listview);

        return "schedule/ScheduleMonth";
    }
    
    @RequestMapping(value = "/scheduleYear")
    public String scheduleYear(HttpServletRequest request, ScheduleSearchVO searchVO, ModelMap modelMap) {
        String userno = request.getSession().getAttribute("userno").toString();

        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        // -----------------------------------------

        searchVO.pageCalculate( scheduleSvc.selectScheduleCount(searchVO) ); // startRow, endRow
        List<?> listview  = scheduleSvc.selectScheduleList(searchVO);

        modelMap.addAttribute("searchVO", searchVO);
        modelMap.addAttribute("listview", listview);

        return "schedule/ScheduleYear";
    }
    
    @RequestMapping(value = "/schedulePopup")
    public String schedulePopup(HttpServletRequest request, ScheduleSearchVO searchVO, ModelMap modelMap) {
        String userno = request.getSession().getAttribute("userno").toString();

        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        // -----------------------------------------

        searchVO.pageCalculate( scheduleSvc.selectScheduleCount(searchVO) ); // startRow, endRow
        List<?> listview  = scheduleSvc.selectScheduleList(searchVO);

        modelMap.addAttribute("searchVO", searchVO);
        modelMap.addAttribute("listview", listview);

        return "schedule/SchedulePopup";
    }
    
    /** 스케쥴 쓰기     */
    @RequestMapping(value = "/scheduleForm")
    public String scheduleForm(HttpServletRequest request, ModelMap modelMap) {
        String userno = request.getSession().getAttribute("userno").toString();
        
        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        
        //String bgno = request.getParameter("bgno");
        String schno = request.getParameter("schno");
        
        if (schno != null) {
            ScheduleVO scheduleInfo = scheduleSvc.selectScheduleOne(new Field3VO(schno, null, null));
            List<?> listview = scheduleSvc.selectScheduleList(schno);
        
            modelMap.addAttribute("scheduleInfo", scheduleInfo);
            modelMap.addAttribute("listview", listview);
            //bgno = scheduleInfo.getBgno();
        }
        String today = Util4calen.date2Str(Util4calen.getToday());        
        
        modelMap.addAttribute("alertcount", alertcount);
        modelMap.addAttribute("today", today);
       // modelMap.addAttribute("bgno", bgno);
        
        return "schedule/ScheduleForm";
    }

}
