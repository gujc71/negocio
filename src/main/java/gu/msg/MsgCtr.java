package gu.msg;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import gu.admin.board.BoardGroupVO;
import gu.board.BoardSvc;
import gu.board.BoardVO;
import gu.common.Field3VO;
import gu.common.FileUtil;
import gu.common.FileVO;
import gu.common.TreeMaker;
import gu.common.UtilEtc;
import gu.etc.EtcSvc;

@Controller 
public class MsgCtr {

    @Autowired
    private MsgSvc msgSvc;
    
    
    @Autowired
    private EtcSvc etcSvc;    
    
    static final Logger LOGGER = LoggerFactory.getLogger(MsgCtr.class);
    
    /**
     * 리스트.
     */
    @RequestMapping(value = "/msgList")
    public String msgList(HttpServletRequest request, MsgSearchVO searchVO, ModelMap modelMap) {
    	String globalKeyword = request.getParameter("globalKeyword");  // it's search from left side bar
        if (globalKeyword!=null & !"".equals(globalKeyword)) {
            searchVO.setSearchKeyword(globalKeyword);
        }
        
        String userno = request.getSession().getAttribute("userno").toString();
        
        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        
        if (searchVO.getBgno() != null && !"".equals(searchVO.getBgno())) {
            BoardGroupVO bgInfo = msgSvc.selectBoardGroupOne4Used(searchVO.getBgno());
            if (bgInfo == null) { 
                return "board/BoardGroupFail";
            }
            modelMap.addAttribute("bgInfo", bgInfo);
        }
        
        List<?> noticelist  = msgSvc.selectNoticeList(searchVO);

        searchVO.pageCalculate( msgSvc.selectmsgCount(searchVO) ); // startRow, endRow
        List<?> listview  = msgSvc.selectmsgList(searchVO);
        
        modelMap.addAttribute("searchVO", searchVO);
        modelMap.addAttribute("listview", listview);
        modelMap.addAttribute("noticelist", noticelist);
        
        if (searchVO.getBgno() == null || "".equals(searchVO.getBgno())) {
            return "msg/MsgListAll";
        }
        return "msg/MsgListAll";
    }
    @RequestMapping(value = "/msgListRev")
    public String msgList1(HttpServletRequest request, MsgSearchVO searchVO, ModelMap modelMap) {
    	String globalKeyword = request.getParameter("globalKeyword");  // it's search from left side bar
        if (globalKeyword!=null & !"".equals(globalKeyword)) {
            searchVO.setSearchKeyword(globalKeyword);
        }
        
        String userno = request.getSession().getAttribute("userno").toString();
        
        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        
        if (searchVO.getBgno() != null && !"".equals(searchVO.getBgno())) {
            BoardGroupVO bgInfo = msgSvc.selectBoardGroupOne4Used(searchVO.getBgno());
            if (bgInfo == null) { 
                return "board/BoardGroupFail";
            }
            modelMap.addAttribute("bgInfo", bgInfo);
        }
        
        List<?> noticelist  = msgSvc.selectNoticeList(searchVO);

        searchVO.pageCalculate( msgSvc.selectmsgCount(searchVO) ); // startRow, endRow
        List<?> listview  = msgSvc.selectmsgList(searchVO);
        
        modelMap.addAttribute("searchVO", searchVO);
        modelMap.addAttribute("listview", listview);
        modelMap.addAttribute("noticelist", noticelist);
        
        if (searchVO.getBgno() == null || "".equals(searchVO.getBgno())) {
            return "msg/MsgListRev";
        }
        return "msg/MsgListRev";
    }
    @RequestMapping(value = "/msgListSend")
    public String msgList2(HttpServletRequest request, MsgSearchVO searchVO, ModelMap modelMap) {
    	String globalKeyword = request.getParameter("globalKeyword");  // it's search from left side bar
        if (globalKeyword!=null & !"".equals(globalKeyword)) {
            searchVO.setSearchKeyword(globalKeyword);
        }
        
        String userno = request.getSession().getAttribute("userno").toString();
        
        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        
        if (searchVO.getBgno() != null && !"".equals(searchVO.getBgno())) {
            BoardGroupVO bgInfo = msgSvc.selectBoardGroupOne4Used(searchVO.getBgno());
            if (bgInfo == null) { 
                return "board/BoardGroupFail";
            }
            modelMap.addAttribute("bgInfo", bgInfo);
        }
        
        List<?> noticelist  = msgSvc.selectNoticeList(searchVO);

        searchVO.pageCalculate( msgSvc.selectmsgCount(searchVO) ); // startRow, endRow
        List<?> listview  = msgSvc.selectmsgList(searchVO);
        
        modelMap.addAttribute("searchVO", searchVO);
        modelMap.addAttribute("listview", listview);
        modelMap.addAttribute("noticelist", noticelist);
        
        if (searchVO.getBgno() == null || "".equals(searchVO.getBgno())) {
            return "msg/MsgListSend";
        }
        return "msg/MsgListSend";
    }
    /** 
     * 글 쓰기. 
     */
    @RequestMapping(value = "/msgForm")
    public String msgForm(HttpServletRequest request, ModelMap modelMap) {
        String userno = request.getSession().getAttribute("userno").toString();
        
        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        
        String brdno = request.getParameter("brdno");
        
        if (brdno != null) {
            MsgVO msgInfo = msgSvc.selectmsgOne(new Field3VO(brdno, null, null));
        
            modelMap.addAttribute("msgInfo", msgInfo);

        }
        
        return "msg/MsgForm";
    }
    
    /**
     * 글 저장.
     */
    @RequestMapping(value = "/msgSave")
    public String msgSave(HttpServletRequest request, MsgVO msgInfo) {
        String userno = request.getSession().getAttribute("userno").toString();
        msgInfo.setUserno(userno);

        if (msgInfo.getBrdno() != null && !"".equals(msgInfo.getBrdno())) {    // check auth for update
            String chk = msgSvc.selectmsgAuthChk(msgInfo);
            if (chk == null) {
                return "common/noAuth";
            }
        }
        
        String[] fileno = request.getParameterValues("fileno");
        FileUtil fs = new FileUtil();
        List<FileVO> filelist = fs.saveAllFiles(msgInfo.getUploadfile());

        msgSvc.insertmsg(msgInfo, filelist, fileno);

        return "redirect:/msgList?bgno=" + msgInfo.getBgno();
    }

    /**
     * 글 읽기.
     */
    @RequestMapping(value = "/msgRead")
    public String msgRead(HttpServletRequest request, ModelMap modelMap) {
    	String userno = request.getSession().getAttribute("userno").toString();
        
        Integer alertcount = etcSvc.selectAlertCount(userno);
        modelMap.addAttribute("alertcount", alertcount);
        
        String bgno = request.getParameter("bgno");
        String brdno = request.getParameter("brdno");
        
        Field3VO f3 = new Field3VO(brdno, userno, null);
        
        msgSvc.updatemsgRead(f3);
        MsgVO boardInfo = msgSvc.selectmsgOne(f3);
        List<?> listview = msgSvc.selectmsgFileList(brdno);
        
        BoardGroupVO bgInfo = msgSvc.selectBoardGroupOne4Used(boardInfo.getBgno());
        if (bgInfo == null) {
            return "board/BoardGroupFail";
        }
        
        modelMap.addAttribute("boardInfo", boardInfo);
        modelMap.addAttribute("listview", listview);
        modelMap.addAttribute("bgno", bgno);
        modelMap.addAttribute("bgInfo", bgInfo);

        
        return "msg/MsgRead";
    }
    
    /**
     * 글 삭제.
     */
    @RequestMapping(value = "/msgDelete")
    public String msgDelete(HttpServletRequest request) {
        String brdno = request.getParameter("brdno");
        String bgno = request.getParameter("bgno");
        String userno = request.getSession().getAttribute("userno").toString();

        MsgVO msgInfo = new MsgVO();        // check auth for delete
        msgInfo.setBrdno(brdno);
        msgInfo.setUserno(userno);
        String chk = msgSvc.selectmsgAuthChk(msgInfo);
        if (chk == null) {
            return "common/noAuth";
        }
        
        msgSvc.deletemsgOne(brdno);
        
        return "redirect:/msgList?bgno=" + bgno;
    }

    /**
     * 게시판 트리. Ajax용.     
     */
   /* @RequestMapping(value = "/msgListByAjax")
       public void msgListByAjax(HttpServletResponse response, ModelMap modelMap) {
    	
        TreeMaker tm = new TreeMaker();
        String treeStr = tm.makeTreeByHierarchy(listview);
        
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(treeStr);
        } catch (IOException ex) {
            LOGGER.error("msgListByAjax");
        }
        
    }*/
 
}
