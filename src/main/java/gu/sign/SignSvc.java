package gu.sign;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gu.admin.sign.AdSgnFormSvc;

@Service
public class SignSvc {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    static final Logger LOGGER = LoggerFactory.getLogger(AdSgnFormSvc.class);
    
    /**
     * 새문서리스트.
     */
    public List<?> selectSignFormList() {
        return sqlSession.selectList("selectSignFormList");
    }
    
    /**
     * 기안문서에 들어갈 새문서.
     */
    public SignFormVO selectSignFormOne(String param) {
        return sqlSession.selectOne("selectSignFormOne", param);
    }
    
    /**
     * 기안문서 저장 및 임시저장.
     */
    public void insertSignDoc(SignDocVO param) {
    		if(param.getSdno() == null || "".equals(param.getSdno())) {
    			sqlSession.insert("insertSignDoc", param);
    		} else {
    			sqlSession.update("updateSignDoc", param);        	
    		}
    }
    
    /**
     * 기안문서 임시저장 리스트.
     */
    public List<?> selectSignDocTempList() {
        return sqlSession.selectList("selectSignDocTempList");
    }
    
    /**
     * 임시저장리스트 >> 기안문서
     */
    public SignDocVO selectSignDocOne(SignDocVO param) {
        return sqlSession.selectOne("selectSignDocOne", param);
    }
    
    /**
     * 결재받을문서리스트.
     */
    public List<?> selectSignDocGetList() {
        return sqlSession.selectList("selectSignDocGetList");
    }
    
    /**
     * 게시판 삭제.
     */
    /*public void deleteAdSignForm(String param) {
        sqlSession.delete("deleteAdSignForm", param);
    }*/
    
    
}
