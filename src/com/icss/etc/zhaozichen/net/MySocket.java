package com.icss.etc.zhaozichen.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.icss.etc.zhaozichen.controller.UserController;

public class MySocket implements Runnable {
	
	
	
	 private Socket client;  
	  
     public MySocket(Socket client) {  
         this.client = client;  
     }  

     public void run() {  
    	 ObjectInputStream input;  
    	 ObjectOutputStream output;  
         try {  
             input = new ObjectInputStream(client.getInputStream());  
             output = new ObjectOutputStream(client.getOutputStream()); 
             Object getMsg =null;
             try {
				while((getMsg = input.readObject())!=null) {
					 System.out.println("收到:   " + getMsg); 
					 UserController controller=new UserController();
				     output.writeObject(controller.service(getMsg));;
				 }
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

              
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
     }  
 }

