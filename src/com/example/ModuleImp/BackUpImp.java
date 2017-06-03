package com.example.ModuleImp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.util.BackUP;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;
import com.example.util.PareseTxtAndObject;

public class BackUpImp implements BackUP,ConfigurationAWare{

	private String backPath;
	private Logger logger;
	@Override
	public void init(Properties pro) {
			backPath=pro.getProperty("back-temp");
	}
	/**
	 * 备份数据
	 * @param fileName 备份文件
	 * @param data  备份数据
	 * @throws Exception 
	 */
	@Override
	public void store(String filePath, Object map, boolean fugai) throws Exception {
		 	Map<String, BIDR>	maps=(Map<String, BIDR>)map;
		 	List<BIDR>list=new ArrayList<>(); 
		 	for(BIDR b:maps.values()){
		 			list.add(b);
		 	}
			PareseTxtAndObject.objectToTxt(backPath,list, fugai);
		
	}
	/**
	 * 加载备份
	 * @param filePath 备份文件路径
	 * @param del true:读完删除备份文件
	 * @return 返回Map<String, BIDR>  key:loginip   
	 * @throws Exception  
	 */
	@Override
	public Object load(String filePath, boolean del) throws Exception {
		
		List<BIDR> bs = PareseTxtAndObject.parseAll(backPath);
		Map<String, BIDR>map=new HashMap<>();
		for(BIDR b:bs){
			map.put(b.getLogin_ip(), b);
		}
		if(del==true){
			File file=new File(backPath);
			file.delete();
		}
		return map;
	}
	
	@Override
	public void setConfiguration(Configuration conf) {
			try {
				logger=conf.getLogger();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	
		
}
