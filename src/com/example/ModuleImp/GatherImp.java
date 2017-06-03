package com.example.ModuleImp;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.util.BackUP;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.client.Gather;
import com.example.util.PareseTxtAndObject;

public class GatherImp implements Gather,ConfigurationAWare{
	private Logger log;
	private String backPath="";
	private String tempPath="";
	private int lineNum=1000;//一次获取1000条
	private int startline=1;
	BackUP backupImp=null;
	@Override
	public void init(Properties p) {
		backPath=p.getProperty("back-temp");
		tempPath=p.getProperty("src-file");
		log.debug("---------GatherImp-------------"+backPath+tempPath);
	}
	

	@Override
	public Collection<BIDR> gather() throws Exception {
		List<BIDR> outLines=new ArrayList<>();
		//key:loginip  存储未下线的
		Map<String, BIDR> backup=new HashMap<>();
	
			//从备份中加载未下线的
			File file=new File(backPath);
			if(file.exists()&&file.length()>0){
				Object obj=backupImp.load(null, false);//加载后不删除
				if(obj!=null){
					backup.putAll((Map<String, BIDR>)obj);
				}
			}
			log.debug("----------------从备份文件获取个数：------->"+backup.size());
	//		采集数据处理：未下线备份，下线的返回 存储数据库
			List<BIDR> bidrs=PareseTxtAndObject.parse(tempPath, startline,lineNum);
			log.debug("---------------------采集总个数：------------>"+bidrs.size());
			for(BIDR bidr:bidrs){
				String login_ip = bidr.getLogin_ip();
				Timestamp shangXian=bidr.getLogin_date();
					if(shangXian!=null){
							backup.put(login_ip, bidr);
							
					}else{
							BIDR bidr1=backup.remove(login_ip);		//备份缓存中移除
							Timestamp xiaXian=bidr.getLogout_date();
							bidr1.setLogout_date(xiaXian);
							int time=(int) ((xiaXian.getTime()-bidr1.getLogin_date().getTime())/60+1);
							bidr1.setTime_deration(time);
							outLines.add(bidr1);
					}
			}

			
			//未下线部分备份 覆盖以前的
			backupImp.store(null, backup, true);
			log.debug("---------最终-备份个数------->"+backup.size());
			log.debug("---------最终-入库个数------->"+outLines.size());
			

		return outLines;
	}
	

//	BackUP backupImp=null;
	@Override
	public void setConfiguration(Configuration conf) {
			try {
				backupImp=conf.getBackup();
				log=conf.getLogger();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	public Collection<BIDR> gather(int startline,int lineNum) throws Exception {
			this.startline=startline;
			this.lineNum=lineNum;
			return gather();
	}

			

}
