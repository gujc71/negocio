package gu.admin.sign;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdSgnFormSvc {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    /**
     * 게시판 리스트.
     */
    public List<?> selectAdSignFormList() {
        return sqlSession.selectList("selectAdSignFormList");
    }
    
    /**
     * 게시판 리드.
     */
    public AdSgnFormVO selectAdSignFormOne(AdSgnFormVO param) {
        return sqlSession.selectOne("selectAdSignFormOne", param);
    }
    
    /**
     * 게시판 저장 및 수정.
     */
    public void insertAdSignForm(AdSgnFormVO param) {
        if(param.getFrmno() == null || "".equals(param.getFrmno())) {
        	sqlSession.insert("insertAdSignForm", param);
        } else {
        	sqlSession.update("updateAdSignForm", param);        	
        }
    }
    
    /**
     * 게시판 삭제.
     */
    public void deleteAdSignForm(String param) {
        sqlSession.delete("deleteAdSignForm", param);
    }
    
    
}
