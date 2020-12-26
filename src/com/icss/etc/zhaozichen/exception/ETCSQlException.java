package com.icss.etc.zhaozichen.exception;

public class ETCSQlException extends RuntimeException {
	
	
	
	 protected String key;

 
     public ETCSQlException(String key)
     {
         super(key);
         this.key = key;
     }
     
   

}
