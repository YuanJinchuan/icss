package com.icss.etc.zhaozichen.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.icss.etc.zhaozichen.turbine.ThreadPoolUtil;

public class TalkServer {
	
	
    public void doListen() {  
        ServerSocket server;  
        try {  
            server = new ServerSocket(8080); 
            //循环监听8080端口
            System.out.println("=======启动监听======");
            while (true) {  
                Socket client = server.accept();  
              //有Socket连接启用线程
              System.out.println("获取到通信连接");
              ThreadPoolUtil.push(new MySocket(client));   
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  

	
		
		
	}
	
	
	

