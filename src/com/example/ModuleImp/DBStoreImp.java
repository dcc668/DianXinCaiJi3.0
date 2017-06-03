package com.example.ModuleImp;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.SimpleFormatter;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.briup.util.BIDR;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.server.DBStore;
import com.example.mapper.BIDRMapper;
import com.example.model.BIDRwithTab;
import com.example.util.GetSqlSession;

public class DBStoreImp implements DBStore,ConfigurationAWare{

	private Properties pros=null;
	private Logger log;
	@Override 
	public void init(Properties pros) {
			this.pros=pros;
	}

	@Override
	public void setConfiguration(Configuration conf) {
		// TODO Auto-generated method stub
		try {
			log=conf.getLogger();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void saveToDB(Collection<BIDR> list) throws Exception {
			SqlSession ss = GetSqlSession.openSession();
			log.debug("============saveToDB==============="+ss);
			BIDRMapper mapper=ss.getMapper(BIDRMapper.class);
			
			for(BIDR b:list){	
					BIDRwithTab bt=new BIDRwithTab();
						String aAA_login_name=b.getAAA_login_name();
						String login_ip=b.getLogin_ip(); 
						Date login_date=new Date(b.getLogin_date().getTime());
						Date logout_date=new Date(b.getLogout_date().getTime());
						Integer time_duration=b.getTime_deration();
						Date tdate=new Date(b.getLogout_date().getTime());
						log.debug("===============DBStoreImp======tdate========"+tdate);
						SimpleDateFormat sdf=new SimpleDateFormat("dd");
						String table_name="t_detail_"+Integer.parseInt(sdf.format(tdate));
						log.debug("===============DBStoreImp======table_name========"+table_name);
						String nAS_ip=b.getNAS_ip();
					bt.setTable_name(table_name);
					bt.setAaa_login_name(aAA_login_name);
					bt.setLogin_ip(login_ip);
					bt.setLogin_date(login_date);
					bt.setLogout_date(logout_date);
					bt.setNas_ip(nAS_ip);
					bt.setTime_duration(time_duration);
					mapper.insertOne(bt);
			}
//			for(BIDR b:list){	
//				BIDRwithTab bt=new BIDRwithTab();
//				String aAA_login_name=b.getAAA_login_name();
//				String login_ip=b.getLogin_ip(); 
//				Timestamp login_date=b.getLogin_date();
//				Timestamp logout_date=b.getLogout_date();
//				String nAS_ip=b.getNAS_ip();
//				bt.setTable_num(table_num);
//				bt.setAAA_login_name(aAA_login_name);
//				bt.setLogin_ip(login_ip);
//				bt.setLogin_date(login_date);
//				bt.setLogout_date(logout_date);
//				bt.setNAS_ip(nAS_ip);
//				mapper.insertOne(bt);
//			}
			ss.commit();
			
	}


	
		
}
