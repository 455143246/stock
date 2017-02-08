package dao.impl;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ulic.ucia.framework.orm.GenericDao;


import po.Holiday;

import dao.HolidayDao;
@Repository("holidayDao")
public class HolidayDaoImpl extends GenericDao  implements HolidayDao{
	
	@Override
	public void insertHoliday(Holiday holiday) throws Exception {
		persist(holiday);
		System.out.println("232");
//		this.findByQuery("from Holiday t where 1=? ",1);
	}

}
