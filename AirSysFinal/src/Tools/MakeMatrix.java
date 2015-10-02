package Tools;

/**
 * 
 * 将一个用'-'分隔的信息字符串切割成row*col大小的矩阵
 * 便于用户界面将查询结果显示成表格
 * 
 * @author LJQ
 * 
 * @Time 2015-9
 *
 * 主要函数清单清单：
 * makeMatrix(String, int, int)
 * 
 */
public class MakeMatrix {
	
	/**
	 * 
	 *  将一个用'-'分隔的信息字符串切割成row*col大小的矩阵
	 * 
	 * @param para_str
	 *          用'-'分隔的信息字符串
	 *        row
	 *          数组行数
	 *        col
	 *          数组列数
	 * 
	 * @return String[][] 矩阵
	 * 
	 * @exception null
	 * 
	 * @author LJQ
	 * 
	 * @Time 2015-9
	 * 
	 * 
	 */
	public static String[][] makeMatrix(String para_str,int row,int col){
		
		String[][] result=new String[row][col];//新建row*col大小的数组
		
		String[] input= para_str.split("-");//将para_str中用'-'分隔的信息拆开，存入input数组中
        
		/*将input数组中的内容存入result矩阵中*/
        for (int i = 0; i < input.length; i++) {
            result[i/col][i%col]=input[i];
        }
        return result;
	}
	
	

}
