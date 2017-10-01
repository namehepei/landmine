package cn.utils;

//工具类
public class Utils {
	public static int StringToInt(String s){
		char[] charSet= s.toCharArray();
		int num = 1;
		int sum = 0;
		//限制数字大小,int最多8位
		if(charSet.length > 8){
			return sum;
		}
		for(int i = charSet.length - 1;i >= 0;i--){
			sum += (charSet[i] - '0') * num;
			num *= 10;
		}
		return sum;
	}

}
