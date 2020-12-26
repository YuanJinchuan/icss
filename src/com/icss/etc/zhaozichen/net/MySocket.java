package com.icss.etc.zhaozichen.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
             output.writeUTF("Recive:  " + listMsg + "    \r\n Thx...");  
             System.out.println("Recive:   " + listMsg);  
             listMsg = input.readUTF();  
             output.writeUTF("Recive Second:  " + listMsg + "    \r\n Thx...");  
             System.out.println("Recive Second:   " + listMsg);  
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
     }  
 }

