package com.icss.etc.zhaozichen.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TalkServer {
	
	
    public void doListen() {  
        ServerSocket server;  
        try {  
            server = new ServerSocket(9991);  
            while (true) {  
                Socket client = server.accept();  
                new Thread(new MySocket(client)).start();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  

	
		
		
	}
	
	
	

