package Tools;

/**
 * 
 * 将座位号转化成线性编号
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 主要函数清单：
 * getLinearID(int, int, int)
 * trans_seat(String, String, String)
 * transA320(String, String)
 * transBoeing737(String, String)
 * 
 */
public class SeatTranslator {

	/**
	 * 
	 * 计算第row行第col列的座位的线性编号
	 * 
	 * @param row
	 *            行号 
	 *        col 
	 *            列号
	 *        collim
	 *            列的数目
	 * 
	 * @return int 线性编号
	 * 
	 * @exception null
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public static int getLinearID(int row, int col, int collim) {
		int LinearID = row * collim + col;

		return LinearID;
	}

	/**
	 * 
	 * 将A320型飞机的座位号转化为线性编号
	 * 
	 * @param seattype
	 *            舱位
	 *        seatid 
	 *            座位号
	 * 
	 * @return int 线性编号
	 * 
	 * @exception null
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public static int transA320(String seattype, String seatid) {
		int result = 0;
		int row = 0;// 行号
		int col = 0;// 列号

		switch (seattype) {
		/* A320没有头等舱 */
		case "FIRSTCLASS":
			return 10086;
		/* 商务舱座位号的转化 */
		case "BUSINESS":
			row = Integer.parseInt(seatid.substring(0, 1)) - 1;// 获取行号
			col = 0;
			// 检查行号是否合法
			if (row != 0 && row != 1)
				return 10086;
			// 翻译字符获取列号
			switch (seatid.substring(1)) {
			case "A":
				col = 0;
				break;
			case "C":
				col = 1;
				break;
			case "H":
				col = 2;
				break;
			case "K":
				col = 3;
				break;
			default:
				return 10086;

			}
			// 转化为线性编号
			result = SeatTranslator.getLinearID(row, col, 4);

			break;

		/* 经济舱座位号的转化 */
		case "ECONOMIC":
			row = Integer.parseInt(seatid.substring(0, 2)) - 31;// 获取行号
			col = 0;
			/*检查行号是否合法*/
			if (row < 0 || row > 24)
				return 10086;
			// 翻译字符获取列号
			switch (seatid.substring(2)) {
			case "A":
				col = 0;
				break;
			case "B":
				col = 1;
				break;
			case "C":
				col = 2;
				break;
			case "H":
				col = 3;
				break;
			case "J":
				col = 4;
				break;
			case "K":
				col = 5;
				break;
			default:
				return 10086;

			}
			// 转化为线性编号
			result = SeatTranslator.getLinearID(row, col, 6);
			break;
		}
		return result;
	}

	/**
	 * 
	 * 将Boeing737型飞机的座位号转化为线性编号
	 * 
	 * @param seattype
	 *            舱位
	 *        seatid 
	 *            座位号
	 * 
	 * @return int 线性编号
	 * 
	 * @exception null
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public static int transBoeing737(String seattype, String seatid) {
		int result = 0;
		int row = 0;//行号
		int col = 0;//列号
		switch (seattype) {
		/* 头等舱座位号的转化 */
		case "FIRSTCLASS":
			row = Integer.parseInt(seatid.substring(0, 1)) - 1;//获取行号
			/*检查行号是否合法*/
			if (row != 0 && row != 1)
				return 10086;
			col = 0;
			//翻译字符获取列号
			switch (seatid.substring(1)) {
			case "A":
				col = 0;
				break;
			case "C":
				col = 1;
				break;
			case "H":
				col = 2;
				break;
			case "K":
				col = 3;
				break;
			default:
				return 10086;

			}
			// 转化为线性编号
			result = SeatTranslator.getLinearID(row, col, 4);

			break;
		/*Boeing737没有商务舱*/
		case "BUSINESS":
			return 10086;
		/* 头等舱座位号的转化 */
		case "ECONOMIC":
			row = Integer.parseInt(seatid.substring(0, 2)) - 31;//获取行号
			col = 0;
			/*检查行号是否合法*/
			if (row < 0 || row > 26)
				return 10086;
			//翻译字符获取列号
			switch (seatid.substring(2)) {
			case "A":
				col = 0;
				break;
			case "B":
				col = 1;
				break;
			case "C":
				col = 2;
				break;
			case "H":
				col = 3;
				break;
			case "J":
				col = 4;
				break;
			case "K":
				col = 5;
				break;
			default:
				return 10086;

			}
			// 转化为线性编号
			result = SeatTranslator.getLinearID(row, col, 6);
			break;
		}
		return result;
	}

	/**
	 * 
	 * 将飞机的座位号转化为线性编号
	 * 
	 * @param type
	 *            飞机型号
	 *        seattype
	 *            舱位
	 *        seatid 
	 *            座位号
	 * 
	 * @return int 线性编号
	 * 
	 * @exception null
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public static int trans_seat(String type, String seattype, String seatid) {
		int result = 0;
		switch (type) {
		/*如果飞机型号是Boeing737*/
		case "Boeing737":
			result = SeatTranslator.transBoeing737(seattype, seatid);
			break;
		/*如果飞机型号是A320*/
		case "A320":
			result = SeatTranslator.transA320(seattype, seatid);
			break;
		default:
			return 10086;
		}
		return result;

	}

}
