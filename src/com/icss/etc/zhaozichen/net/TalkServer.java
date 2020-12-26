package com.icss.etc.zhaozichen.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer {
	
	
    public void doListen() {  
        ServerSocket server;  
        try {  
            server = new ServerSocket(8080); 
            //循环监听8080端口
            while (true) {  
                Socket client = server.accept();  
              //有Socket连接启用线程
                new Thread(new MySocket(client)).start();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  

	
		
		
	}
	
	
	

