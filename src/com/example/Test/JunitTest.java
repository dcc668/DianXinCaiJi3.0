package com.example.Test;

import java.net.ServerSocket;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import com.briup.util.BIDR;
import com.example.ModuleImp.ClientImp;
import com.example.ModuleImp.GatherImp;
import com.example.ModuleImp.ServerImp;
import com.example.util.PareseTxtAndObject;

public class JunitTest {
	
	@Test
	public void test(){
		GatherImp gi=new GatherImp();
		try {
			Collection<BIDR> gather = gi.gather();
//			System.out.println(gather.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void test1(){
		
		List<BIDR> list = PareseTxtAndObject.parse("src/temp.txt",1, 1000);
		for(BIDR b:list){
			System.out.println(b.getLogin_date());
		}
		
		System.out.println(list.size());
		
		PareseTxtAndObject.objectToTxt("src/cc.txt", list, false);
		
	}
	

	public void test3(){
			ClientImp c=new ClientImp();
			try {
	
				c.send(null);
				ServerImp si=new ServerImp();
				si.revicer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("src/log4j.properties");
		Logger logger=Logger.getRootLogger();
			logger.debug("1.-------------------debug---------.");
			logger.info("2------------------info------------");
			logger.warn("3-----------warn-------------");
			logger.error("4-------------error---------------");
			logger.fatal("5---------------fatal---------------");
			
	}
	
	
}
