package Actor;

import DBBasic.DBHandler;
import DBBasic.DBRetrieve;


public class Membership {
	
	String Cid;
	String Password;
	String IDNum;
	String Phone;
	String Name;
	DBHandler myhandler;
	DBRetrieve myretrieve;
	
	
	public Membership(String cid,String password){
		Cid=cid;
		Password=password;
		
		//constructor里貌似不能调方法哎……
		String IDNum="";
		String Phone="";
		String Name="";
		
		myhandler=new DBHandler();
		myretrieve=new DBRetrieve();

	}
	
	public void Init(){
		this.IDNum=myretrieve.getClientIDNum(this.Cid);
		this.Phone=myretrieve.getClientPhone(this.Cid);
		this.Name=myretrieve.getClientName(this.Cid);
		
	}
	
	
	public void SearchAirline(int date,String origin,String destination){
		myhandler.getAirline(date, origin, destination);

	}
	
	
	public void BookAirTicket(String aid,String seat_type,String seatid){
		
		String sid=null;
		myhandler.bookTicket(this.Cid, aid, seat_type, seatid);
	}
	
	
	public void CancelAirTicket(String orderid){
		myhandler.cancelTicket(orderid);
		
		
	}
	
	//这个移到membership一栏在做，membership的constructor可以先载好当前name啊~phone啊~这样就可以设default值啦
	public void ProfileUpdate(String NewIDNum,String NewPhone,String NewName){
		if(NewIDNum==""&&NewPhone==""&&NewName=="")
			return;
		else{
			if(NewIDNum=="")
				NewIDNum=this.IDNum;
			if(NewPhone=="")
				NewPhone=this.Phone;
			if(NewName=="")
				NewName=this.Name;
			myhandler.profileUpdate(this.Cid, NewIDNum, NewPhone, NewName );
		}

	}
	
	public void PrintSchedules(){
		myhandler.printSchedule(this.Cid);
	}
	
	
	public static void main(String[] args) {
		
		Membership me=new Membership("C00000008","3112334");
		me.Init();
		System.out.println("??");
		System.out.println(me.Phone);
		System.out.println(me.Name);
		System.out.println(me.IDNum);
		//me.SearchAirline(20100908, "广州", "上海");
		me.ProfileUpdate("", "", "中二");
		
		
	}
	
	
	
	
	

}


