package Tools;

public class bitTranslator_old {
	public static String bi2hex(String bString,int orilen)  
	    {  
		
		System.out.println(bString);
	        if (bString == null || bString.equals("") )  
	            return null;  
	        if(bString.length() % 4 != 0){
	        	int append=4-bString.length();
	        	for(int i=0;i<append;i++){
	        		bString+="0";
	        	}
	        	
	       
	        } 
	        String out=Long.toHexString(Long.parseLong(bString, 2));
	        System.out.println(out);
	        return out;
	        
	    }  
	public static String hex2bi(String hexString,int orilen){
	
	   System.out.println(hexString);
	   if (hexString == null || hexString.equals("") )  
           return null;  
	   else{
       String str=Long.toBinaryString(Long.valueOf(hexString,16));
       if(str.length()<orilen){
    	   int append=orilen-str.length();
    	   for(int i=0;i<append;i++){
    		   str="0"+str;
    	   }
       }
       return str;
	   }
    }  
	public static void main(String[] args){
		String str="011111111111111111111111111111111111111111111111111111111111111";
		String str2=str.trim();
		String b=bi2hex(str2,str2.length());
		System.out.println(b);
		//String c=hex2bi(0,16);
		//System.out.println(c);
	}

}




