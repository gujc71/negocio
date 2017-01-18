package gu.sign;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignSvc {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    /**
     * 새문서리스트.
     */
    public List<?> selectSignFormList() {
        return sqlSession.selectList("selectSignFormList");
    }
    
    /**
     * 기안문서에 들어갈 새문서.
     */
    public SignFormVO selectSignFormOne(SignFormVO param) {
        return sqlSession.selectOne("selectSignFormOne", param);
    }
    
    /**
     * 게시판 저장 및 수정.
     */
    /*public void insertAdSignForm(AdSgnFormVO param) {
        if(param.getFrmno() == null || "".equals(param.getFrmno())) {
        	sqlSession.insert("insertAdSignForm", param);
        } else {
        	sqlSession.update("updateAdSignForm", param);        	
        }
    }*/
    
    /**
     * 게시판 삭제.
     */
    /*public void deleteAdSignForm(String param) {
        sqlSession.delete("deleteAdSignForm", param);
    }*/
    
    
}
