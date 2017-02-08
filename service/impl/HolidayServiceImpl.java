package service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.HolidayDao;

import po.Holiday;
import service.HolidayService;
import util.DateUtil;
@Service("holidayService")
public class HolidayServiceImpl implements HolidayService {
	@Autowired
	@Qualifier("holidayDao")
	private HolidayDao holidayDao; 
	@Transactional(rollbackFor={Throwable.class},propagation=Propagation.REQUIRED)
	public void insertHoliday(Date date)throws Exception{
		Holiday holiday=new Holiday();
		if(DateUtil.validHoliday(date)){
			holiday.setIsWorkDay("N");
		}else{
			holiday.setIsWorkDay("Y");
		}
		holiday.setValidDate(new Date());
		holidayDao.insertHoliday(holiday);
	}
}
