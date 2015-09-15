package Actor;

import DBBasic.DBHandler;
import DBBasic.DBHandlerOper;
import DBBasic.DBRetrieve;
import DBBasic.DBHandlerPro;

public class Admin {
	
	
	DBHandler myhandler;
	DBHandlerOper myhandlerOper;
	DBRetrieve myretrieve;
	DBHandlerPro myhandlerPro;
	
	
	public Admin(){
		
		
		myhandler=new DBHandler();
		myhandlerOper=new DBHandlerOper();
		myretrieve=new DBRetrieve();
		myhandlerPro=new DBHandlerPro();

	}
	
	public void SearchAirline(int date,String origin,String destination){
		myhandler.getAirline(date, origin, destination);

	}
	
	
	public void AddAirline(String aid,String origin,String dest,int date,int hour,int minute,int first_num,int busi_num,int eco_num,String type){
		
		
		myhandlerPro.addAirline(aid, origin, dest, date, hour,minute, first_num, busi_num, eco_num, type);
	}
	
	
	public void CancelAirline(String aid){
		myhandlerPro.cancelAirline(aid);		
		
	}
	public void AddOperator(String password){

		myhandlerPro.addOperator(password);
	}
	
	
	public void CancelOperator(String oid){
		myhandlerPro.cancelOperator(oid);		

	}
	public void AddTicketBox(String address,String name){

		myhandlerPro.addTicketBox(address,name);
	}
	
	public void CancelTicketBox(String tid){
		myhandlerPro.cancelOperator(tid);		

	}
	
	public void AddSalesman(String name){

		myhandlerPro.addSalesman(name);
	}
	
	
	public void CancelSalesman(String sid){
		myhandlerPro.cancelSalesman(sid);	
	}
	
	public void AddDeliveryman(String name){

		myhandlerPro.addDeliveryman(name);
	}
	
	
	public void CancelDeliveryman(String did){
		myhandlerPro.cancelDeliveryman(did);	
	}
	
	public void printSalesmanChart(String sid){
		myhandlerPro.printSalesmanChart(sid);
		
	}
	
	
	public static void main(String[] args) {
		
		Admin me=new Admin();
		
		System.out.println("??");
		//System.out.println(me.Phone);
		//System.out.println(me.Name);
		//System.out.println(me.IDNum);
		me.SearchAirline(20100908, "广州", "上海");
		me.AddAirline("MU222", "厦门", "纽约",20100907,02,30,20,20,20,"波音747");
		me.printSalesmanChart("002");
		
		
	}
	
	
	
	
}
