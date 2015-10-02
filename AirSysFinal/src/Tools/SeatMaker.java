package Tools;


/**
 * 
 * 用于初始化seat表中代表选座情况的2进制字符串
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 主要函数清单：
 * MakeSeatTable(int)
 * 
 */
public class SeatMaker {
	
	/**
	 * 
	 * 生成指定长度的2进制字符串
	 * 
	 * @param seatnum
	 *          座位数（2进制字符串的长度）
	 * 
	 * @return String 2进制字符串
	 * 
	 * @exception null
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public static String MakeSeatTable(int seatnum){
		String seatstr="";
		/*循环添0创建长度为seatnum的2进制字符串*/
		for(int i=0;i<seatnum;i++){
			seatstr+="0";
		}
		return seatstr;
	}

}
