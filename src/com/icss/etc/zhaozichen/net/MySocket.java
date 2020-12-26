package com.icss.etc.zhaozichen.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.icss.etc.zhaozichen.pojo.Student;

public class MySocket implements Runnable {
	
	
	
	 private Socket client;  
	  
     public MySocket(Socket client) {  
         this.client = client;  
     }  

     public void run() {  
         DataInputStream input;  
         DataOutputStream output;  
         try {  
             input = new DataInputStream(client.getInputStream());  
             output = new DataOutputStream(client.getOutputStream());  
             String listMsg = input.readUTF();  
             System.out.println("收到:   " + listMsg);  
             output.writeUTF(new Student().toString()); 
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
     }  
 }

