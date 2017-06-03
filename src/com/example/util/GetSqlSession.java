package com.example.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class GetSqlSession {
	private static SqlSession ss=null;
	public static  SqlSession openSession(){
		if(ss==null){
			synchronized(GetSqlSession.class){
				if(ss==null){
					try{
							String resource = "src/Configuration.xml";
							FileReader reader= new FileReader(new File(resource));
							SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
							ss= ssf.openSession(false);//没false   默认自动提交
							return ss;
						} catch (IOException e) { 
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}else{
					return ss;
				}
			}
		}else{
			return ss;
		}
		return ss;
	}
}
