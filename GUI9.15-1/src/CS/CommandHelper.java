package CS;

import DBBasic.*;

public class CommandHelper {
	DBHandler dbhandler;
	DBHandlerOper dbhandleroper;
	DBLoginHandler dbloginhandler;
	DBHandlerPro dbhandlerpro;
	
	public CommandHelper(){
		dbhandler=new DBHandler();
		dbhandleroper=new DBHandlerOper();
		dbloginhandler=new DBLoginHandler();
		dbhandlerpro=new DBHandlerPro();
	}

	
	
	public String getRespond(String line){
		
		String comid=line.substring(0, 2);
		String para_str=line.substring(2);
		String[] input ;
		
		String result="";
		switch(comid){
		
		case "03":
			input= para_str.split("-");
            
            for (int i = 0; i < 2; i++) {
                System.out.println(input[i]);
            }
			result=dbloginhandler.login(input[0], input[1])+"";
			
			break;
		case "04":
			input = para_str.split("-");
            
            for (int i = 0; i < 4; i++) {
                System.out.println(input[i]);
            }
			result=""+dbloginhandler.register(input[0], input[1],input[2],input[3]);
			break;
		case "05":break;
		case "06":break;
		case "07":
			input = para_str.split("-");
            
            for (int i = 0; i < 3; i++) {
                System.out.println(input[i]);
            }
			result=dbhandler.getAirline(Integer.parseInt(input[0]), input[1],input[2]);
			break;
		case "08":
			input = para_str.split("-");
        
        for (int i = 0; i < 4; i++) {
            System.out.println(input[i]);
        }
		result=dbhandler.bookTicket(input[0], input[1],input[2],input[3]);
		break;
		case "09":
			result=dbhandler.cancelTicket(para_str);
			break;
		case "10":
            input= para_str.split("-");
            
            for (int i = 0; i < 2; i++) {
                System.out.println(input[i]);
            }
			result=dbhandler.profileUpdate_idn(input[0], input[1]);
			
			break;

		case "11":
			input= para_str.split("-");
            
            for (int i = 0; i < 2; i++) {
                System.out.println(input[i]);
            }
			result=dbhandler.profileUpdate_phone(input[0], input[1]);
			
			break;
		case "12":
            input= para_str.split("-");
            
            for (int i = 0; i < 2; i++) {
                System.out.println(input[i]);
            }
			result=dbhandler.profileUpdate_name(input[0], input[1]);
			
			break;
		case "13":
			result=dbhandler.printSchedule(para_str);
			break;
		case "14":
			//result=dbhandleroper.printScheduleByIDN(para_str);
			break;
		case "15":
			//result=dbhandleroper.printScheduleByIDNPhone(para_str);
			break;
		case "16":
			input = para_str.split("-");
            System.out.println("input.length:"+input.length);
            if(input.length==3)
            	result=dbhandleroper.addClient(input[0], input[1],input[2],"");
            else
            	result=dbhandleroper.addClient(input[0], input[1],input[2],input[3]);
			break;
		case "17":
			result=dbhandleroper.searchClientByCid(para_str);
			break;
		case "18":
			result=dbhandleroper.searchClientByIDNum(para_str);
			break;
		case "19":
			result=dbhandleroper.searchClientByPhone(para_str);
			break;
		case "22":
			input= para_str.split("-");
            
            for (int i = 0; i < 7; i++) {
                System.out.println(input[i]);
            }
			result=dbhandlerpro.addAirline(input[0], input[1],input[2], Integer.parseInt(input[3]), Integer.parseInt(input[4]),  Integer.parseInt(input[5]),input[6]);
			break;
		case "23":
			result=dbhandlerpro.cancelAirline(para_str);
			break;
		case "24":
			input= para_str.split("-");
            
            for (int i = 0; i < 2; i++) {
                System.out.println(input[i]);
            }
			result=dbhandlerpro.addSalesman(input[0], input[1]);
			
			break;
		case "25":
			result=dbhandlerpro.cancelSalesman(para_str);
			break;
		case "26":
			input= para_str.split("-");
            
            for (int i = 0; i < 2; i++) {
                System.out.println(input[i]);
            }
			result=dbhandlerpro.addDeliveryman(input[0], input[1]);
			
			break;
		case "27":
			result=dbhandlerpro.cancelDeliveryman(para_str);
			break;
		case "28":
			result=dbhandlerpro.addOperator(para_str);
			break;
		case "29":
			result=dbhandlerpro.cancelOperator(para_str);
			break;
		case "30":
			input= para_str.split("-");
            
            for (int i = 0; i < 2; i++) {
                System.out.println(input[i]);
            }
			result=dbhandlerpro.addTicketBox(input[0], input[1]);
			break;
		case "31":
			result=dbhandlerpro.cancelTicketBox(para_str);
			break;
			
		case "32":
			result=dbhandlerpro.printSalesmanChart(para_str);
			break;
		case "33":
			result=dbhandlerpro.printClientChart();
			break;
		default:break;
		
		
		
		
		}
		/*
		if(commid=="00"){
			result=XXX.XXXX();

		}
		else
		*/
		return result;
	}
	
	public void Command23(){
		
	}

}
