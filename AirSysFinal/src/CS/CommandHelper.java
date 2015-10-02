package CS;

import java.io.IOException;

import DBBasic.*;

/**
 * 解析字符串，然后调用合适的方法完成任务并返回结果字符串
 * 
 * 将字符串拆分为指令+参数 根据指令调用DBBasic包的方法并传入参数 返回结果字符串
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 * 
 * 主要函数清单：
 * getRespond(String)
 *
 * 
 * 
 */
public class CommandHelper {
	/* DBBasic包里几个类的实例，实现服务器需要提供的功能 */
	DBHandler dbhandler;
	DBHandlerOper dbhandleroper;
	DBLoginHandler dbloginhandler;
	DBHandlerPro dbhandlerpro;

	/**
	 * 
	 * 构造方法
	 * 
	 * 初始化
	 * 
	 * 
	 */
	public CommandHelper() {
		dbhandler = new DBHandler();
		dbhandleroper = new DBHandlerOper();
		dbloginhandler = new DBLoginHandler();
		dbhandlerpro = new DBHandlerPro();
	}

	/**
	 * 
	 * 解析接收到的消息，调用方法实现被请求的功能
	 * 
	 * @param line
	 *            消息字符串
	 * 
	 * @return String 结果字符串
	 * 
	 * @exception IOException
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public String getRespond(String line) {

		String comid = line.substring(0, 2);// 获取指令码
		String para_str = line.substring(2);// 获取参数集
		String[] input;
		String result = "";
		switch (comid) {

		// 若指令码为03，解析参数集，获取两参数，调用login方法
		case "03":
			input = para_str.split("-");

			result = dbloginhandler.login(input[0], input[1]) + "";

			break;

		// 若指令码为04，解析参数集，获取四参数，调用register方法
		case "04":
			input = para_str.split("-");

			result = dbloginhandler.register(input[0], input[1], input[2], input[3]);
			break;

		// 若指令码为07，解析参数集，获取三参数，调用getAirline方法
		case "07":
			input = para_str.split("-");

			result = dbhandler.getAirline(Integer.parseInt(input[0]), input[1], input[2]);
			break;

		// 若指令码为08，解析参数集，获取四参数，调用bookTicket方法
		case "08":
			input = para_str.split("-");

			result = dbhandler.bookTicket(input[0], input[1], input[2], input[3]);
			break;

		// 若指令码为09，调用cancelTicket方法，传入唯一参数
		case "09":
			result = dbhandler.cancelTicket(para_str);
			break;

		// 若指令码为10，解析参数集，获取两参数，调用profileUpdate_idn方法
		case "10":
			input = para_str.split("-");

			result = dbhandler.profileUpdate_idn(input[0], input[1]);

			break;

		// 若指令码为11，解析参数集，获取两参数，调用profileUpdate_phone方法
		case "11":
			input = para_str.split("-");

			result = dbhandler.profileUpdate_phone(input[0], input[1]);

			break;

		// 若指令码为12，解析参数集，获取两参数，调用profileUpdatee_name方法
		case "12":
			input = para_str.split("-");

			result = dbhandler.profileUpdate_name(input[0], input[1]);

			break;

		// 若指令码为13，调用printSchedule方法，传入唯一参数
		case "13":
			result = dbhandler.printSchedule(para_str);
			break;
		case "14":
			// result=dbhandleroper.printScheduleByIDN(para_str);
			break;
		case "15":
			// result=dbhandleroper.printScheduleByIDNPhone(para_str);
			break;

		// 若指令码为16，解析参数集，获取四参数，调用addClient方法，允许第四个参数为空
		case "16":
			input = para_str.split("-");
			if (input.length == 3)
				result = dbhandleroper.addClient(input[0], input[1], input[2], "");
			else
				result = dbhandleroper.addClient(input[0], input[1], input[2], input[3]);
			break;

		// 若指令码为17，调用searchClientByCid方法，传入唯一参数
		case "17":
			result = dbhandleroper.searchClientByCid(para_str);
			break;
		// 若指令码为18，调用searchClientByIDNum方法，传入唯一参数
		case "18":
			result = dbhandleroper.searchClientByIDNum(para_str);
			break;
		// 若指令码为19，调用searchClientByPhone方法，传入唯一参数
		case "19":
			result = dbhandleroper.searchClientByPhone(para_str);
			break;
		// 若指令码为22，解析参数集，获取四参数，调用addAirline方法
		case "22":
			input = para_str.split("-");

			result = dbhandlerpro.addAirline(input[0], input[1], input[2], Integer.parseInt(input[3]),
					Integer.parseInt(input[4]), Integer.parseInt(input[5]), input[6]);
			break;
		// 若指令码为23，调用cancelAirline方法，传入唯一参数
		case "23":
			result = dbhandlerpro.cancelAirline(para_str);
			break;
		// 若指令码为24，解析参数集，获取二参数，调用addSalesman方法
		case "24":
			input = para_str.split("-");

			result = dbhandlerpro.addSalesman(input[0], input[1]);

			break;
		// 若指令码为25，调用cancelSalesman方法，传入唯一参数
		case "25":
			result = dbhandlerpro.cancelSalesman(para_str);
			break;
		// 若指令码为26，解析参数集，获取二参数，调用addDeliveryman方法
		case "26":
			input = para_str.split("-");

			result = dbhandlerpro.addDeliveryman(input[0], input[1]);

			break;
		// 若指令码为27，调用cancelDeliveryman方法，传入唯一参数
		case "27":
			result = dbhandlerpro.cancelDeliveryman(para_str);
			break;
		// 若指令码为28，调用addOperator方法，传入唯一参数
		case "28":
			result = dbhandlerpro.addOperator(para_str);
			break;
		// 若指令码为29，调用cancelOperator方法，传入唯一参数
		case "29":
			result = dbhandlerpro.cancelOperator(para_str);
			break;
		// 若指令码30，解析参数集，获取二参数，调用addTicketBox方法
		case "30":
			input = para_str.split("-");

			for (int i = 0; i < 2; i++) {
				System.out.println(input[i]);
			}
			result = dbhandlerpro.addTicketBox(input[0], input[1]);
			break;
		// 若指令码为31，调用cancelTicketBox方法，传入唯一参数
		case "31":
			result = dbhandlerpro.cancelTicketBox(para_str);
			break;
		// 若指令码为32，调用printSalesmanChart方法，传入唯一参数
		case "32":
			result = dbhandlerpro.printSalesmanChart(para_str);
			break;
		// 若指令码为33，调用printClientChart方法，传入唯一参数
		case "33":
			result = dbhandlerpro.printClientChart();
			break;
		// 若指令码为40，调用getAircraft方法，传入唯一参数
		case "40":
			result = dbhandler.getAircraft(para_str);
			break;
		default:
			break;

		}

		return result;
	}

}
