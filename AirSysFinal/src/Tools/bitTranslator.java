package Tools;

/**
 * 
 * 实现2进制字符串与16进制字符串的互转 用于选座情况的存储、查询
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 主要函数清单：
 * bi2hex(String, int)
 * hex2bi(String, int)
 * 
 */
public class bitTranslator {

	/**
	 * 
	 * 将2进制字符串转为16进制字符串
	 * 
	 * @param CurrentbiString
	 *          2进制字符串
	 *        orilen
	 *          2进制字符串的长度
	 * 
	 * @return String 16进制字符串
	 * 
	 * @exception null
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public static String bi2hex(String CurrentbiString, int orilen) {
		String biString = CurrentbiString;

		String result = null;
		/*如果2进制串为空，返回空 */
		if (biString == null || biString.equals(""))
			return null;
		/*如果2进制串为0，补0直到字符串长度为orilen */
		if (biString.equals("0")) {
			biString = "";
			for (int i = 0; i < orilen; i++) {
				biString += "0";
			}
		}
		//若2进制串位数不能正好被四整除，末尾补0直到可以正好被四整除
		if (orilen % 4 != 0) {
			int append = 4 - orilen % 4;//计算要补多少个0
			for (int i = 0; i < append; i++) {
				biString += "0";
			}

		}

		int divide = biString.length() / 4;//计算将转化为16进制串后的长度
		//创建divide大小的数组，数组的每个元素存储一个由4位2进制串转化为的16进制字符
		String[] trans_array = new String[divide];
		for (int i = 0; i < divide; i++) {
			trans_array[i] = biString.substring(i * 4, (i + 1) * 4);
			switch (trans_array[i]) {
			case "0000":
				trans_array[i] = "0";
				break;
			case "0001":
				trans_array[i] = "1";
				break;
			case "0010":
				trans_array[i] = "2";
				break;
			case "0011":
				trans_array[i] = "3";
				break;
			case "0100":
				trans_array[i] = "4";
				break;
			case "0101":
				trans_array[i] = "5";
				break;
			case "0110":
				trans_array[i] = "6";
				break;
			case "0111":
				trans_array[i] = "7";
				break;
			case "1000":
				trans_array[i] = "8";
				break;
			case "1001":
				trans_array[i] = "9";
				break;
			case "1010":
				trans_array[i] = "A";
				break;
			case "1011":
				trans_array[i] = "B";
				break;
			case "1100":
				trans_array[i] = "C";
				break;
			case "1101":
				trans_array[i] = "D";
				break;
			case "1110":
				trans_array[i] = "E";
				break;
			case "1111":
				trans_array[i] = "F";
				break;
			}

		}
		result = "";
		/*将数组中存储的字符拼接为16进制字符串*/
		for (int i = 0; i < divide; i++) {
			result += trans_array[i];
		}

		return result;

	}

	/**
	 * 
	 * 将16进制字符串转为2进制字符串
	 * 
	 * @param CurrenthexString
	 *          16进制字符串
	 *        orilen
	 *          2进制字符串的长度
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
	public static String hex2bi(String hexString, int orilen) {
		String result = null;

		/*如果16进制字符串为空，返回空*/
		if (hexString == null || hexString.equals(""))
			return null;
		else {
			//计算16进制串的长度，创建该长度的数组，数组中的每个元素存储由1位16进制字符转化成的4位2进制字符串
			int divide = hexString.length();
			String[] trans_array = new String[divide];
			for (int i = 0; i < divide; i++) {
				trans_array[i] = hexString.substring(i, (i + 1));
				switch (trans_array[i]) {
				case "0":
					trans_array[i] = "0000";
					break;
				case "1":
					trans_array[i] = "0001";
					break;
				case "2":
					trans_array[i] = "0010";
					break;
				case "3":
					trans_array[i] = "0011";
					break;
				case "4":
					trans_array[i] = "0100";
					break;
				case "5":
					trans_array[i] = "0101";
					break;
				case "6":
					trans_array[i] = "0110";
					break;
				case "7":
					trans_array[i] = "0111";
					break;
				case "8":
					trans_array[i] = "1000";
					break;
				case "9":
					trans_array[i] = "1001";
					break;
				case "A":
					trans_array[i] = "1010";
					break;
				case "B":
					trans_array[i] = "1011";
					break;
				case "C":
					trans_array[i] = "1100";
					break;
				case "D":
					trans_array[i] = "1101";
					break;
				case "E":
					trans_array[i] = "1110";
					break;
				case "F":
					trans_array[i] = "1111";
					break;
				}

			}
			result = "";
			/*将数组元素拼接成2进制字符串*/
			for (int i = 0; i < divide; i++) {
				result += trans_array[i];
			}

			/*如果转化后的二进制字符串长度大于它应该有的长度orilen，去掉末位的补零*/
			if (4 * divide > orilen) {
				result = result.substring(0, orilen);
			}

		}

		return result;
	}

	public static void main(String[] args) {
		String str = "111111111111111111111100111111111111111111111110011111111111111111111111001";
		int orilen = str.length();

		String b = bi2hex(str, orilen);
		// System.out.println(b);
		String c = hex2bi(b, orilen);
		// System.out.println(c);
	}

}
