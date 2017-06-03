package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import com.briup.util.BIDR;
import com.briup.woss.server.DBStore;
import com.example.ModuleImp.DBStoreImp;

public class ServerThread implements Runnable{
	public Socket soc=null;
	DBStore dbimp=null;
	public ServerThread(Socket soc,DBStore dbs){
			this.soc=soc;
			this.dbimp=dbs;
	}
	
	@Override
	public void run() {
		InputStream is=null;
		ObjectInputStream ois=null;
		try {
				 is=soc.getInputStream();
				 ois=new ObjectInputStream(is);
				List<BIDR> list=null;
				Object obj=null;
				
				if((obj=ois.readObject())!=null){
					list=(List<BIDR>)obj;
					dbimp.saveToDB(list);
				}
				 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				is.close();
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
