package cn.utils;

//������
public class Utils {
	public static int StringToInt(String s){
		char[] charSet= s.toCharArray();
		int num = 1;
		int sum = 0;
		//�������ִ�С,int���8λ
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
