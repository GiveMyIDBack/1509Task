package Tools;

public class SeatTranslator {
	
	public static int getLinearID(int row,int col,int collim){
		int LinearID=row*collim+col;
		
		return LinearID;
	}
	
	public static int transA320(String seattype,String seatid){
		int result=0;
		int row=0;
		int col=0;
		switch(seattype){
		case "FIRSTCLASS":
			return 10086;
		case "BUSINESS":
			row=Integer.parseInt(seatid.substring(0,1))-1;
			col=0;
			if(row!=0&&row!=1)
				return 10086;
			switch(seatid.substring(1)){
			case "A":
				col=0;
				break;
			case "C":
				col=1;
				break;
			case "H":
				col=2;
				break;
			case "K":
				col=3;
				break;
			default:
				return 10086;
		
			
			}
			result=SeatTranslator.getLinearID(row,col,4);
			
			break;
		case "ECONOMIC":
			row=Integer.parseInt(seatid.substring(0,2))-31;
			col=0;
			if(row<0||row>24)
				return 10086;
			switch(seatid.substring(2)){
			case "A":
				col=0;
				break;
			case "B":
				col=1;
				break;
			case "C":
				col=2;
				break;
			case "H":
				col=3;
				break;
			case "J":
				col=4;
				break;
			case "K":
				col=5;
				break;
			default:
				return 10086;
			
			}
			result=SeatTranslator.getLinearID(row,col,6);
			break;
		}
		return result;
	}
	
	public static int transBoeing737(String seattype,String seatid){
		int result=0;
		int row=0;
		int col=0;
		switch(seattype){
		case "FIRSTCLASS":
			row=Integer.parseInt(seatid.substring(0,1))-1;
			System.out.println("row is"+row);
			if(row!=0&&row!=1)
				return 10086;
			col=0;
			switch(seatid.substring(1)){
			case "A":
				col=0;
				break;
			case "C":
				col=1;
				break;
			case "H":
				col=2;
				break;
			case "K":
				col=3;
				break;
			default:
				return 10086;
		
			
			}
			result=SeatTranslator.getLinearID(row,col,4);
			
			break;
		case "BUSINESS":
			return 10086;
		case "ECONOMIC":
			System.out.println("into boeing737 economic");
			row=Integer.parseInt(seatid.substring(0,2))-31;
			System.out.println(row);
			col=0;
			if(row<0||row>26)
				return 10086;
			switch(seatid.substring(2)){
			case "A":
				col=0;
				break;
			case "B":
				col=1;
				break;
			case "C":
				col=2;
				break;
			case "H":
				col=3;
				break;
			case "J":
				col=4;
				break;
			case "K":
				col=5;
				break;
			default:
				return 10086;
			
			}
			result=SeatTranslator.getLinearID(row,col,6);
			
			break;
		}
		return result;
	}
	
	
	
	public static int trans_seat(String type,String seattype,String seatid){
		int result=0;
		switch(type){
		case "Boeing737":
			System.out.println("into boeing737");
			result=SeatTranslator.transBoeing737(seattype, seatid);
			break;
		case "A320":
			System.out.println("into A320");
			result=SeatTranslator.transA320(seattype, seatid);
			break;
		default:
			System.out.println("not into any plane");
			return 10086;
		}
		return result;
		
	}
	
	
	
	

}
