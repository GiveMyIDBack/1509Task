package Tools;

public class SeatMaker {
	
	
	public static String MakeSeatTable(int seatnum){
		String seatstr="";
		for(int i=0;i<seatnum;i++){
			seatstr+="0";
		}
		return seatstr;
	}

}
