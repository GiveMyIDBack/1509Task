package Actor;

import DBBasic.DBHandler;
import DBBasic.DBHandlerOper;
import DBBasic.DBRetrieve;
import DBBasic.DBHandlerPro;

public class Starter {
	
	
	DBHandler myhandler;
	DBHandlerOper myhandlerOper;
	DBRetrieve myretrieve;
	DBHandlerPro myhandlerPro;
	
	
	public Starter(){
		
		
		myhandler=new DBHandler();
		myhandlerOper=new DBHandlerOper();
		myretrieve=new DBRetrieve();
		myhandlerPro=new DBHandlerPro();

	}
	
	public void SearchAirline(int date,String origin,String destination){
		myhandler.getAirline(date, origin, destination);

	}
}
