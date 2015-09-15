package Tools;

public class MakeMatrix {
	
	public static String[][] makeMatrix(String para_str,int row,int col){
		
		String[][] result=new String[row][col];
		
		
		String[] input= para_str.split("-");
        
        for (int i = 0; i < input.length; i++) {
            //System.out.println(input[i]);
            result[i/col][i%col]=input[i];
        }
        return result;
	}
	/*
	public static void main(String args[]){
		String str="A_B_C_D_A_B_C_D_A_B_C_D_";
		String[][] a=MakeMatrix.makeMatrix(str, 3, 4);
		System.out.println(a[0]);
	}
	*/
	

}
