package cn.display;

import java.io.*;

import cn.utils.Utils;
import cn.entity.*;

public class Display {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		GameSet gameSet = GameSet.show();
		int xLength = gameSet.getxLength();
		int yLength = gameSet.getyLength();
		int mineNum = gameSet.getMineNum();
		AllBlock allBlock = new AllBlock(xLength,yLength,mineNum);
		allBlock.layMines();
		while(allBlock.result() == 0){
			try {
				System.out.println("x = ");
				int x = Utils.StringToInt(br.readLine());
				System.out.println("y = ");
				int y = Utils.StringToInt(br.readLine());
				//¼ì²âÊý¾Ý
				if(x < 1 || x > 9 || y < 1 || y > 9)
					return;
				allBlock.click(x, y);
				allBlock.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}

	}

}
