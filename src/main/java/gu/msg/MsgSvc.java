package gu.msg;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import gu.admin.board.BoardGroupVO;
import gu.common.Field3VO;
import gu.common.FileVO;

@Service
public class MsgSvc {

    @Autowired
    private SqlSessionTemplate sqlSession;    
    @Autowired
    private DataSourceTransactionManager txManager;

    static final Logger LOGGER = LoggerFactory.getLogger(MsgSvc.class);
    
    
    /** 
     * 게시판 정보 (그룹).
     */
    public BoardGroupVO selectBoardGroupOne4Used(String param) {
        return sqlSession.selectOne("selectBoardGroupOne4Used", param);
    }
    
    /** ------------------------------------------
     * 게시판.
     */
    public Integer selectmsgCount(MsgSearchVO param) {
        return sqlSession.selectOne("selectMsgCount", param);
    }
    
    public List<?> selectmsgList(MsgSearchVO param) {
        return sqlSession.selectList("selectMsgList", param);
    }
    public List<?> selectNoticeList(MsgSearchVO param) {
        return sqlSession.selectList("selectNoticeList", param);
    }
    
    /**
     * 글 저장.
     */
    public void insertmsg(MsgVO param, List<FileVO> filelist, String[] fileno) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        
        try {
            if (param.getBrdno() == null || "".equals(param.getBrdno())) {
                 sqlSession.insert("insertMsg", param);
            } else {
                sqlSession.update("updateMsg", param);
            }
            
            if (fileno != null) {
                HashMap<String, String[]> fparam = new HashMap<String, String[]>();
                fparam.put("fileno", fileno);
                sqlSession.insert("deleteMsgFile", fparam);
            }
            
            for (FileVO f : filelist) {
                f.setParentPK(param.getBrdno());
                sqlSession.insert("insertMsgFile", f);
            }
            txManager.commit(status);
        } catch (TransactionException ex) {
            txManager.rollback(status);
            LOGGER.error("insertBoard");
        }            
    }
 
    public MsgVO selectmsgOne(Field3VO param) {
        return sqlSession.selectOne("selectMsgOne", param);
    }

    /**
     * 게시판 수정/삭제 권한 확인. 
     */
    public String selectmsgAuthChk(MsgVO param) {
        return sqlSession.selectOne("selectMsgAuthChk", param);
    }
    
    public void updatemsgRead(Field3VO param) {
        sqlSession.insert("updateMsgRead", param);
    }
    
    public void deletemsgOne(String param) {
        sqlSession.delete("deleteMsgOne", param);
    }
    public List<?> selectmsgFileList(String param) {
        return sqlSession.selectList("selectMsgFileList", param);
    }
    
}
