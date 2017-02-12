package gu.schedule;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gu.board.BoardVO;
import gu.common.Field3VO;
import gu.schedule.ScheduleSearchVO;

@Service
public class ScheduleSvc {

    @Autowired
    private SqlSessionTemplate sqlSession;    
        
    public List<?> selectScheduleGroupCount4Statistic() {
        return sqlSession.selectList("selectScheduleGroupCount4Statistic");
    }

    public Integer selectScheduleCount(ScheduleSearchVO param) {
        return sqlSession.selectOne("selectScheduleCount", param);
    }
    
    public List<?> selectScheduleList(ScheduleSearchVO param) {
        return sqlSession.selectList("selectScheduleList", param);
    }
    
    public ScheduleVO selectScheduleOne(Field3VO param) {
        return sqlSession.selectOne("selectScheduleOne", param);
    }

	public List<?> selectScheduleList(String schno) {
	     return sqlSession.selectList("selectScheduleList", schno);
	}
}
