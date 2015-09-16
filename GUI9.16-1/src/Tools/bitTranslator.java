package Tools;

public class bitTranslator {
	public static String bi2hex(String CurrenthexString,int orilen)  
	    {  
		String hexString=CurrenthexString;
		System.out.println("++++++"+ hexString+"++++++");
		System.out.println("before bi2hex:\n"+ hexString);
		String result=null;
		
		
	        if (hexString == null || hexString.equals("") )  
	            return null;  
	        if(hexString.equals("0")){
	        	System.out.println("into 0");
	        	hexString="";
	        	for(int i=0;i<orilen;i++){
	        		hexString+="0";
	        	}
	        }
	        //不规整，末尾补0
	        if(orilen % 4 != 0){
	        	int append=4-orilen%4;
	        	for(int i=0;i<append;i++){
	        		hexString+="0";
	        	}
	        
	        } 
	        System.out.println("before bi2hex,after format:\n"+ hexString);
	        
	        //得到规矩的4倍二进制串
	        int divide=hexString.length()/4;
	        String[] trans_array=new String[divide];
	        for(int i=0;i<divide;i++){
	        	trans_array[i]=hexString.substring(i*4,(i+1)*4);
	        	switch(trans_array[i]){
	        	case "0000":
	        		trans_array[i]="0";
	        		break;
	        	case "0001":
	        		trans_array[i]="1";
	        		break;
	        	case "0010":
	         		trans_array[i]="2";
	         		break;
	        	case "0011":
	         		trans_array[i]="3";
	         		break;
	        	case "0100":
	        		trans_array[i]="4";
	        		break;
	        	case "0101":
	        		trans_array[i]="5";
	        		break;
	        	case "0110":
	         		trans_array[i]="6";
	         		break;
	        	case "0111":
	         		trans_array[i]="7";
	         		break;
	        	case "1000":
	        		trans_array[i]="8";
	        		break;
	        	case "1001":
	        		trans_array[i]="9";
	        		break;
	        	case "1010":
	         		trans_array[i]="A";
	         		break;
	        	case "1011":
	         		trans_array[i]="B";
	         		break;
	        	case "1100":
	        		trans_array[i]="C";
	        		break;
	        	case "1101":
	        		trans_array[i]="D";
	        		break;
	        	case "1110":
	         		trans_array[i]="E";
	         		break;
	        	case "1111":
	         		trans_array[i]="F";
	         		break;
	        	}
	       
        		
	        }
	        result="";
	        for(int i=0;i<divide;i++){
	        	result+=trans_array[i];
	        }
	        System.out.println("after bi2hex:\n"+ result);


	        return result;
	        
	    }  
	public static String hex2bi(String bString,int orilen){
		String result=null;
	
	   System.out.println("before hex2bi:\n"+bString);
	   if (bString == null || bString.equals("") )  
           return null;  
	   else{
		   int divide=bString.length();
	        String[] trans_array=new String[divide];
	        for(int i=0;i<divide;i++){
	        	trans_array[i]=bString.substring(i,(i+1));
	        	switch(trans_array[i]){
	        	case "0":
	        		trans_array[i]="0000";
	        		break;
	        	case "1":
	        		trans_array[i]="0001";
	        		break;
	        	case "2":
	         		trans_array[i]="0010";
	         		break;
	        	case "3":
	         		trans_array[i]="0011";
	         		break;
	        	case "4":
	        		trans_array[i]="0100";
	        		break;
	        	case "5":
	        		trans_array[i]="0101";
	        		break;
	        	case "6":
	         		trans_array[i]="0110";
	         		break;
	        	case "7":
	         		trans_array[i]="0111";
	         		break;
	        	case "8":
	        		trans_array[i]="1000";
	        		break;
	        	case "9":
	        		trans_array[i]="1001";
	        		break;
	        	case "A":
	         		trans_array[i]="1010";
	         		break;
	        	case "B":
	         		trans_array[i]="1011";
	         		break;
	        	case "C":
	        		trans_array[i]="1100";
	        		break;
	        	case "D":
	        		trans_array[i]="1101";
	        		break;
	        	case "E":
	         		trans_array[i]="1110";
	         		break;
	        	case "F":
	         		trans_array[i]="1111";
	         		break;
	        	}
	       
       		
	        }
	        result="";
	        for(int i=0;i<divide;i++){
	        	result+=trans_array[i];
	        }
	        if(4*divide>orilen){
	     	   
	     	   result=result.substring(0,orilen);
	        }
	        
	   }
	   System.out.println("after hex2bi:\n"+result);
       
       return result;
    }  
	public static void main(String[] args){
		String str="111111111111111111111100111111111111111111111110011111111111111111111111001";
		int orilen=str.length();
		            
		String b=bi2hex(str,orilen);
		//System.out.println(b);
		String c=hex2bi(b,orilen);
		//System.out.println(c);
	}

}
