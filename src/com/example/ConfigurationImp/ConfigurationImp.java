package com.example.ConfigurationImp;

import java.util.Properties;

import com.briup.util.BackUP;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.client.Client;
import com.briup.woss.client.Gather;
import com.briup.woss.server.DBStore;
import com.briup.woss.server.Server;
import com.example.ModuleImp.BackUpImp;
import com.example.ModuleImp.ClientImp;
import com.example.ModuleImp.DBStoreImp;
import com.example.ModuleImp.GatherImp;
import com.example.ModuleImp.LoggerImp;
import com.example.ModuleImp.ServerImp;
import com.example.util.XmlParser;



public class ConfigurationImp implements Configuration{
	Properties p=null;
	public ConfigurationImp() {
			
	}

	@Override
	public Logger getLogger() throws Exception {
		LoggerImp log=new LoggerImp();
		return log;
	}

	@Override
	public BackUP getBackup() throws Exception {
		BackUpImp b=new BackUpImp();
		Properties p=XmlParser.getBackupProperty();
		b.setConfiguration(this);
		b.init(p);
		return b;
	}

	@Override
	public Gather getGather() throws Exception {
		GatherImp ga=new GatherImp();
		ga.setConfiguration(this);
		Properties p1=XmlParser.getGatherProperty();	
		Properties p2=XmlParser.getBackupProperty();
			p1.putAll(p2);
			ga.init(p1);
		return ga;
	}

	@Override
	public Client getClient() throws Exception {
		// TODO Auto-generated method stub
		ClientImp c=new ClientImp();
		Properties pro=XmlParser.getClientProperty();
		c.init(pro);
		return c;
	}
	

	@Override
	public Server getServer() throws Exception {
		ServerImp s=new ServerImp();
		s.setConfiguration(this);//需要用到DBStore模块
		Properties pro=XmlParser.getServerProperty();
		s.init(pro);
		return s;
	}

	@Override
	public DBStore getDBStore() throws Exception {
		DBStoreImp	dbstore=new DBStoreImp();
		Properties pro=XmlParser.getDBStoreProperty();
		dbstore.setConfiguration(this);
		dbstore.init(pro);
		return dbstore;
	}

}
