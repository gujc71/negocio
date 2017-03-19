package gu.survey;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import gu.board.BoardSearchVO;
import gu.board.BoardSvc;

@Service
public class SurveySvc {
	
	@Autowired
    private SqlSessionTemplate sqlSession;    
    @Autowired
    private DataSourceTransactionManager txManager;
    
    static final Logger LOGGER = LoggerFactory.getLogger(SurveySvc.class);
    
    public Integer selectSurveyCount(BoardSearchVO param) {
        return sqlSession.selectOne("selectSurveyCount", param);
    }
    
    public List<?> selectSurveyList(BoardSearchVO param) {
        return sqlSession.selectList("selectSurveyList", param);
    }
    
    public void insertSurvey(SurveyVO param){
//    	System.out.println("** "+param.toString());
    	sqlSession.insert("insertSurvey", param);
    }
    
    public SurveyVO getSurvey(String surno){
    	return sqlSession.selectOne("getSurvey", surno);
    }
}
