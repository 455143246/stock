package action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import service.HolidayService;

@Controller
@RequestMapping(value = "/lilxtest")
public class StockController extends MultiActionController{
	@Autowired
	@Qualifier("holidayService")
	private HolidayService holidayService;
	@RequestMapping(value = "/hi")
	public ModelAndView list(HttpServletRequest req, HttpServletResponse rep){
		System.out.println("hello world");
		try {
			holidayService.insertHoliday(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("hello world1");
		return null;
	}
}
