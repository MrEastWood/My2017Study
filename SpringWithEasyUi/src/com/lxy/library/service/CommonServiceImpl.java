package com.lxy.library.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxy.library.common.BusException;
import com.lxy.library.dao.MaxIdDao;
import com.lxy.library.entry.MaxId;

@Service("commonService")
@Transactional(rollbackFor = Exception.class)
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	protected MaxIdDao maxIdDao;
	@Override
	public String getJournal(Date date) throws Exception {
		
		String idType = "journal";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String idPrefix = sdf.format(date);
		
		MaxId maxid = maxIdDao.getRecordByIdUpd(MaxId.class, idType);
		if(maxid == null){
			
			BusException e = new BusException("初始记录不存在，生成流水号失败");
			e.setReturnCode("C001");
			throw e;
		}
		
		int curSeqNum = 1;
		//前缀没有修改，序列号在原来的基础上加1。前缀有修改，使用新的前缀，序列号置为1
		if(maxid.getIdPrefix().equals(idPrefix)){
			curSeqNum = maxid.getidSeqNum() + 1;
		}else{
			maxid.setIdPrefix(idPrefix);
		}

		maxid.setidSeqNum(curSeqNum);
		maxIdDao.modifyRecord(maxid);
		return maxid.toString();
	}
}
