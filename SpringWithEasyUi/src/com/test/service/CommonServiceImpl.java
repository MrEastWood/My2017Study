package com.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.common.BusException;
import com.test.dao.BaseDao;
import com.test.entry.MaxId;

@Service("commonService")
@Transactional(rollbackFor = Exception.class)
public class CommonServiceImpl implements CommonService {
	
	
	@Override
	public String getJournal(String idPrefix) throws Exception {
		
		BaseDao<MaxId> maxIdDao = new BaseDao<MaxId>(){};
		String idType = "journal";
		
		MaxId maxid = maxIdDao.getRecordByIdUpd(MaxId.class, idType);
		if(maxid == null){
			
			BusException e = new BusException("初始记录不存在，生成流水号失败");
			e.setReturnCode("C001");
			throw e;
		}
		
		int curSeqNum = 1;
		//前缀没有修改，序列号在原来的基础上加1。前缀有修改，使用新的前缀，序列号置为1
		if(maxid.getIdPrefix() == idPrefix){
			curSeqNum = maxid.getidSeqNum() + 1;
		}else{
			maxid.setIdPrefix(idPrefix);
		}

		maxid.setidSeqNum(curSeqNum);
		maxIdDao.modifyRecord(maxid);
		
		return maxid.toString();
	}
}
