package com.example.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.briup.util.BIDR;

public class PareseTxtAndObject {
	/**
	 * 
	 * @param filePath 文件路径
	 * @param lineNum 解析行数
	 * @param startLine 开始行 从1开始
	 * @return
	 */
	public static List<BIDR> parse(String filePath,int startLine,int lineNum){

		List<BIDR> list=new ArrayList<>();
		int count=1;
		File file=new File(filePath);
		try {
					BufferedReader br= new BufferedReader(new FileReader(file));
					String line=null;
					int start=startLine;
					//跳过前startLine行
					while(start>1){
							br.readLine();
							start--;
					}
					while((line=br.readLine())!=null&&count++<=lineNum){
							BIDR bidr=new BIDR();
							String[]strs=line.split("\\|");
							int  shangXian=Integer.parseInt(strs[2]);
							Long times=Long.parseLong(strs[3])*1000;
							java.sql.Timestamp time=new 	java.sql.Timestamp(times);
							String nAS_ip=strs[1];
							String login_ip=strs[4];
							bidr.setNAS_ip(nAS_ip);
							bidr.setLogin_ip(login_ip);
							if(shangXian==7){
									bidr.setLogin_date(time);
									bidr.setLogout_date(null);
									String aAA_login_name=strs[0].split("#")[1];
									bidr.setAAA_login_name(aAA_login_name);
							}else{
									bidr.setLogout_date(time);
									bidr.setLogin_date(null);
							}
							list.add(bidr);
					}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
	}
	
	
	
	public static List<BIDR> parseAll(String filePath){
		
		List<BIDR> list=new ArrayList<>();
		File file=new File(filePath);
		BufferedReader br= null;
		try {
			br= new BufferedReader(new FileReader(file));
			String line=null;
			while((line=br.readLine())!=null){
				BIDR bidr=new BIDR();
				String[]strs=line.split("\\|");
				int  shangXian=Integer.parseInt(strs[2]);
				Long times=Long.parseLong(strs[3])*1000;
				java.sql.Timestamp time=new 	java.sql.Timestamp(times);
				String nAS_ip=strs[1];
				String login_ip=strs[4];
				
				bidr.setNAS_ip(nAS_ip);
				bidr.setLogin_ip(login_ip);
				
				if(shangXian==7){
					bidr.setLogin_date(time);
					String aAA_login_name=strs[0].split("#")[1];
					bidr.setAAA_login_name(aAA_login_name);
					
				}else{
					bidr.setLogout_date(time);
				}
				list.add(bidr);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br!=null){
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		return list;
		
	}
	
	
	
	
	
	/**
	 * 
	 * @param filePath 文件路径
	 * @param list 所有对象
	 * @param zuijia 源文件追加？ture追加，false覆盖
	 * @return 
	 */
	public static void  objectToTxt(String filePath,List<BIDR> list,boolean fugai){
			int count=1;
			File file=new File(filePath);
			try {	
				FileWriter fw= new FileWriter(file, !fugai);
				BufferedWriter  bw = new BufferedWriter(fw);
				int size=list.size();
				for(BIDR bidr:list){
							String line="";
							String name=bidr.getAAA_login_name();
							String aAA_login_name=(name==null)?"":name;
							String nAS_ip=bidr.getNAS_ip();
							String login_ip= bidr.getLogin_ip();
							Timestamp timein=bidr.getLogin_date();
							
							if(timein==null){
								Long timeout=bidr.getLogout_date().getTime()/1000;
//				#|037:wKgB981A|8|1239159046|57.48.62.254
									line="#|"+nAS_ip+"|"+8+"|"+timeout+"|"+login_ip;
							}else{
//				#briup981|037:wKgB981A|7|1239104110|57.48.62.254
								long in=timein.getTime()/1000;
								line="#"+aAA_login_name+"|"+nAS_ip+"|"+7+"|"+in+"|"+login_ip;
							}
							if(--size>=1)
								bw.write(line+"\n");
							else
								bw.write(line);
				}
				bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
