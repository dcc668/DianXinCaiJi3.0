package com.example.model;

import java.sql.Date;
import java.sql.Timestamp;

public class BIDRwithTab  {
	private String aaa_login_name;
	private String login_ip;
	private Date login_date;
	private Date logout_date;
	private String nas_ip;
	private Integer time_duration;

	private String table_name;
	
	
	public BIDRwithTab() {
	}


	public BIDRwithTab(String aaa_login_name, String login_ip, Date login_date, Date logout_date, String nas_ip,
			Integer time_duration, String table_name) {
		this.aaa_login_name = aaa_login_name;
		this.login_ip = login_ip;
		this.login_date = login_date;
		this.logout_date = logout_date;
		this.nas_ip = nas_ip;
		this.time_duration = time_duration;
		this.table_name= table_name;
	}


	public String getAaa_login_name() {
		return aaa_login_name;
	}


	public void setAaa_login_name(String aaa_login_name) {
		this.aaa_login_name = aaa_login_name;
	}


	public String getLogin_ip() {
		return login_ip;
	}


	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}


	public Date getLogin_date() {
		return login_date;
	}


	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}


	public Date getLogout_date() {
		return logout_date;
	}


	public void setLogout_date(Date logout_date) {
		this.logout_date = logout_date;
	}


	public String getNas_ip() {
		return nas_ip;
	}


	public void setNas_ip(String nas_ip) {
		this.nas_ip = nas_ip;
	}


	public Integer getTime_duration() {
		return time_duration;
	}


	public void setTime_duration(Integer time_duration) {
		this.time_duration = time_duration;
	}


	public String getTable_name() {
		return table_name;
	}


	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}


	@Override
	public String toString() {
		return "BIDRwithTab [aaa_login_name=" + aaa_login_name + ", login_ip=" + login_ip + ", login_date=" + login_date
				+ ", logout_date=" + logout_date + ", nas_ip=" + nas_ip + ", time_duration=" + time_duration
				+ ", table_num=" + table_name + "]";
	}

}
	