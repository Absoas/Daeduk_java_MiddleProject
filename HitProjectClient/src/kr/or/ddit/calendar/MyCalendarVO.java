package kr.or.ddit.calendar;

import java.io.Serializable;

public class MyCalendarVO implements Serializable {
	private String calendarno;
	private String calendar_date;
	private String calendar_enddate;
	private String calendar_title;
	private String calendar_content;
	private String mem_id;
	public String getCalendarno() {
		return calendarno;
	}
	public void setCalendarno(String calendarno) {
		this.calendarno = calendarno;
	}
	public String getCalendar_date() {
		return calendar_date;
	}
	public void setCalendar_date(String calendar_date) {
		this.calendar_date = calendar_date;
	}
	public String getCalendar_enddate() {
		return calendar_enddate;
	}
	public void setCalendar_enddate(String calendar_enddate) {
		this.calendar_enddate = calendar_enddate;
	}
	public String getCalendar_title() {
		return calendar_title;
	}
	public void setCalendar_title(String calendar_title) {
		this.calendar_title = calendar_title;
	}
	public String getCalendar_content() {
		return calendar_content;
	}
	public void setCalendar_content(String calendar_content) {
		this.calendar_content = calendar_content;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
}
