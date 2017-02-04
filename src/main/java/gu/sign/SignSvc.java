package gu.sign;

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

import gu.admin.sign.AdSgnFormSvc;

@Service
public class SignSvc {

    @Autowired
    private SqlSessionTemplate sqlSession;
    @Autowired
    private DataSourceTransactionManager txManager;
    
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
    public SignFormVO selectSignFormOne(SignFormVO param) {
        return sqlSession.selectOne("selectSignFormOne", param);
    }
    
    /**
     * 기안문서 저장 및 임시저장.
     */
    public void insertSignDoc(SignDocVO param) {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = txManager.getTransaction(def);
    	try {
    		if(param.getSdstate() == null || "".equals(param.getSdstate())) {
    			sqlSession.insert("insertSignDoc", param);
    		} else {
    			sqlSession.update("updateSignDoc", param);        	
    		}
			txManager.commit(status);
		} catch (TransactionException ex) {
			txManager.rollback(status);
			LOGGER.error("insertSignDoc");
		}
    }
    
    /**
     * 기안문서 임시저장 리스트.
     */
    public List<?> selectSignDocTempList() {
        return sqlSession.selectList("selectSignDocTempList");
    }
    
    /**
     * 게시판 삭제.
     */
    /*public void deleteAdSignForm(String param) {
        sqlSession.delete("deleteAdSignForm", param);
    }*/
    
    
}
