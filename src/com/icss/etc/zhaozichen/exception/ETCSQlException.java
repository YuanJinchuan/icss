package com.icss.etc.zhaozichen.exception;

public class ETCSQlException extends RuntimeException {
	
	
	
	 protected String key;
     protected Object[] args;
 
     public ETCSQlException(String key)
     {
         super(key);
         this.key = key;
     }
     
     public  ETCSQlException(String key, Throwable cause)
     {
         super(key, cause);
         this.key = key;
     }
     

     public  ETCSQlException(String key,  Object ... args)
     {
         super(key);
         this.key = key;
         this.args = args;
     }
     
 
     public  ETCSQlException(String key, Throwable cause, Object ... args)
     {
         super(key, cause);
         this.key = key;
         this.args = args;
     }

     public String getKey()
     {
         return key;
     }

     public Object[] getArgs()
     {
         return args;
     }

}
