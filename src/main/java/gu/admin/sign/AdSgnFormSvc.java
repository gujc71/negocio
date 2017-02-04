package gu.admin.sign;

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

@Service
public class AdSgnFormSvc {

    @Autowired
    private SqlSessionTemplate sqlSession;
    @Autowired
    private DataSourceTransactionManager txManager;
    
    static final Logger LOGGER = LoggerFactory.getLogger(AdSgnFormSvc.class);
    
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
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    	TransactionStatus status = txManager.getTransaction(def);
    	try {
    		if(param.getFrmno() == null || "".equals(param.getFrmno())) {
    			sqlSession.insert("insertAdSignForm", param);
    		} else {
    			sqlSession.update("updateAdSignForm", param);        	
    		}
			txManager.commit(status);
		} catch (TransactionException ex) {
			txManager.rollback(status);
			LOGGER.error("insertAdSignForm");
		}
    }
    
    /**
     * 게시판 삭제.
     */
    public void deleteAdSignForm(String param) {
        sqlSession.delete("deleteAdSignForm", param);
    }
    
    
}
