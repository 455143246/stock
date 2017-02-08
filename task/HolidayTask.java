package task;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import service.HolidayService;


@Component("holidayTask")
public class HolidayTask implements org.quartz.Job{
	
	@Autowired()
	@Qualifier("holidayService")
	public HolidayService holidayService; 

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			holidayService.insertHoliday(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
