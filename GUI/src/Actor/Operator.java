package Actor;

import DBBasic.DBHandler;
import DBBasic.DBHandlerOper;
import DBBasic.DBRetrieve;

public class Operator {
	
	String Oid;
	DBHandler myhandler;
	DBHandlerOper myhandlerOper;
	DBRetrieve myretrieve;
	
	
	public Operator(String oid,String password){
		
		String Oid=oid;
		myhandler=new DBHandler();
		myhandlerOper=new DBHandlerOper();
		myretrieve=new DBRetrieve();

	}
	
	public void SearchAirline(int date,String origin,String destination){
		myhandler.getAirline(date, origin, destination);

	}
	
	
	public void BookAirTicket(String cid,String aid,String seat_type,String seatid){
		
		String sid=null;
		myhandler.bookTicket(cid, aid, seat_type, seatid);
	}
	
	
	public void CancelAirTicket(String orderid){
		myhandler.cancelTicket(orderid);
		
		
	}
	
	//这个移到membership一栏在做，membership的constructor可以先载好当前name啊~phone啊~这样就可以设default值啦
	public void ProfileUpdate(String Cid,String NewIDNum,String NewPhone,String NewName){
		if(NewIDNum==""&&NewPhone==""&&NewName=="")
			return;
		else{
			if(NewIDNum=="")
				NewIDNum=myretrieve.getClientIDNum(Cid);
			if(NewPhone=="")
				NewPhone=myretrieve.getClientPhone(Cid);
			if(NewName=="")
				NewName=myretrieve.getClientName(Cid);
			myhandler.profileUpdate(Cid, NewIDNum, NewPhone, NewName );
		}

	}
	
	public void searchClientByPhone(String phone){
		myhandlerOper.searchClientByPhone(phone);
		
	}
	public void searchClientByIDNum(String idnum){
		myhandlerOper.searchClientByIDNum(idnum);
		
	}
	public void searchClientByCid(String cid){
		myhandlerOper.searchClientByCid(cid);
		
	}
	
	public void AddClient(String idnum,String phone,String name,String sid ){
		myhandlerOper.addClient(idnum, phone, name, sid);
	}
	
	public void PrintSchedules(String Cid){
		myhandler.printSchedule(Cid);
	}
	
	
	
	public static void main(String[] args) {
		
		Operator me=new Operator("O00000001","O00000001");
		
		System.out.println("??");
		//System.out.println(me.Phone);
		//System.out.println(me.Name);
		//System.out.println(me.IDNum);
		me.SearchAirline(20100908, "广州", "上海");
		//me.AddClient("2444", "34", "111","S00000001");
		//me.BookAirTicket("C00000001", "MU340", "FirstClass", "12A");
		
		
	}

}
