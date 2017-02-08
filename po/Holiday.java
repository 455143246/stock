package po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="HOLIDAY_TEST")
public class Holiday {
	@Id
	@SequenceGenerator(name = "S_HOLIDAY_TEST", sequenceName = "S_HOLIDAY_TEST")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "S_HOLIDAY_TEST")
	@Column(name="ID")
	private int id;
	@Column(name="IS_WORK_DAY")
	private String isWorkDay;
	@Column(name="VALID_DATE")
	private Date validDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsWorkDay() {
		return isWorkDay;
	}
	public void setIsWorkDay(String isWorkDay) {
		this.isWorkDay = isWorkDay;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	
}
