package com.example.ModuleImp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.Properties;

import com.briup.util.BIDR;
import com.briup.util.Configuration;
import com.briup.util.Logger;
import com.briup.woss.ConfigurationAWare;
import com.briup.woss.client.Client;
import com.example.ConfigurationImp.ConfigurationImp;

public class ClientImp implements Client,ConfigurationAWare{
	private String address;
	private Logger log;
	private int port;
	@Override
	public void init(Properties pro) {
			address=pro.getProperty("ip");
			port=Integer.parseInt(pro.getProperty("client-port"));
	}

	@Override
	public void setConfiguration(Configuration conf) {
		try {
			log=conf.getLogger();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void send(Collection<BIDR> list) throws Exception {
			Socket soc=new Socket(address, port);
			OutputStream os=soc.getOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(os);
			oos.writeObject(list);
			oos.flush();
			
			os.close();
			oos.close();
			soc.close();
	}
		
	
	public static void main(String[] args) {
		ConfigurationImp fon=new ConfigurationImp();
			try {
				Client c= fon.getClient();
				GatherImp gather = (GatherImp) fon.getGather();
				BufferedReader br=new BufferedReader(new FileReader(new File("src/temp.txt")));
				int lineNum=0;
				while(br.readLine()!=null){
						lineNum++;
				}
				int n=lineNum/1000;
				if(n*1000!=lineNum){
					n++;
				}
				for(int i=0;i<n;i++){
					if(i==n-1)
						c.send(gather.gather(i*1000+1,lineNum-i*1000));
					else
						c.send(gather.gather(i*1000+1,1000));
				}
//				c.send(gather.gather(4001,1000));
//				c.send(gather.gather(5001,1000));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
