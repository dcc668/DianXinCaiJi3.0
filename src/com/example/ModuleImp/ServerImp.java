package com.example.ModuleImp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.server.DBStore;
import com.briup.woss.server.Server;
import com.example.ConfigurationImp.ConfigurationImp;
import com.example.util.ServerThread;

public class ServerImp implements Server,ConfigurationAWare{
		private int port;
		private ServerSocket ss=null;
		private Socket soc=null;
		private DBStore dbs=null;
		private Logger log;
		@Override
		public void init(Properties pro) {
			port=Integer.parseInt(pro.getProperty("ser-port"));
			try {
				ss = new ServerSocket(port);
				while(true){
						soc=ss.accept();
						new Thread(new ServerThread(soc,dbs)).start();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		@Override
		public void setConfiguration(Configuration conf) {
				try {
					dbs=conf.getDBStore();
					log=conf.getLogger();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	
		@Override
		public Collection<BIDR> revicer() throws Exception {
				
				
				return null;
				
		}
		@Override
		public void shutdown() {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		public static void main(String[] args) {
			ServerImp si;
			try {
				Configuration conf=new ConfigurationImp();
				conf.getServer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
