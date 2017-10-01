package cn.entity;

//雷区
public class AllBlock {
	//x,y是用户指定的区域大小,数学坐标
	private int x = 1;
	private int y = 1;
	//底层数据的大小,数组索引
	private int xSize = 3;
	private int ySize = 3;
	//所有块
	private Block[] blocks = null;
	//雷的数目
	private int mineNum = 0;
	//雷所在数组索引
	private Integer[] mines = null;
	//result(-1:失败;0:正在运行;1:胜利)
	private int result = 0;



	//初始化雷区
	public AllBlock(int x,int y,int mineNum){
		this.x = x;
		this.y = y;
		xSize = x + 2;
		ySize = y + 2;
		blocks = new Block[xSize * ySize];//数组中的对象没有实例化
		this.mineNum = mineNum;
		mines = new Integer[mineNum];
		//实例化 blocks
		for(int i = 0;i < blocks.length;i++){
			blocks[i] = new Block();
		}
	}
	
	//
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {	this.y = y;}
	public int getxSize() {	return xSize;}
	public void setxSize(int xSize) {	this.xSize = xSize;}
	public int getySize() {	return ySize;}
	public void setySize(int ySize) {	this.ySize = ySize;}
	public Block[] getBlocks() {	return blocks;}
	public void setBlocks(Block[] blocks) {	this.blocks = blocks;}
	public int getMineNum() {	return mineNum;}
	public void setMineNum(int mineNum) {	this.mineNum = mineNum;}
	public Integer[] getMines() {	return mines;}
	public void setMines(Integer[] mines) {	this.mines = mines;}
	public int getResult() {	return result;}
	public void setResult(int result) {	this.result = result;}
	//布雷
	public void layMines(){
		//生成mineNum 个雷并分布
		for(int i = 0;i < mineNum;i++){
			//分布在 1~x*y 的随机数
			int randnum = (int) (Math.random() * x * y) + 1;
			//雷所在数学坐标
			int y = getXY(randnum)[1];
			int x = getXY(randnum)[0];
			//雷所在的数组索引
			int index = getIndex(x,y);
			//防止随机数重复
			for(int j = 0;j < i;j++){
				if(mines[j] == index){
					i--;
					continue;
				}	
			}
			//存储雷的数组索引
			mines[i] = index;
			//修改 blocks 的 flag 层
			blocks[index].setFlag(true);
		}
		//修改Block的 num 层
		for(int i = 1;i <= y;i++){
			for(int j = 1;j <= x;j++){
				int index = getIndex(j, i);
				blocks[index].setNum(getBlockNum(j, i));
			}
		}
	}
	//鼠标右击(如果有-1,则取消-1;反之,增加-1)
	public void clickRight(int length){
		int y = getXY(length)[1];
		int x = getXY(length)[0];
		clickRight(x,y);
	}
	public void clickRight(int x,int y){
		int index = getIndex(x, y);
		Block block = blocks[index];
		block.setShow((block.getShow() == -1)?0:-1);
	}
	
	//点击鼠标后的结果(需要考虑:是否为雷,num 是否为0;递归需要考虑:是否翻过,是否越界,num 是否为0)
	public int click(int length){
		int y = getXY(length)[1];
		int x = getXY(length)[0];
		return click(x,y);
	}
	public int click(int x,int y){
		int index = getIndex(x, y);
		//检测是否被翻过
		if(blocks[index].getShow() == 1){
			return result;
		}
		//检测是否超界
		if(x < 1 || x > this.x || y < 1 || y > this.y)
			return result;
		//更改 show 数据
		blocks[index].setShow(1);	
		//检测是否雷块,因为 num = 0,所以必定isFlag == false;
		if(blocks[index].isFlag()){
			result = -1;
			return result;
		}
		//
		if(blocks[index].getNum() == 0){
			click(x - 1, y - 1);
			click(x - 1, y);
			click(x - 1, y + 1);
			
			click(x, y - 1);
			click(x, y + 1);
			
			click(x + 1, y - 1);
			click(x + 1, y);
			click(x + 1, y + 1);
		}
		else{
			return result;
		}
		
		return result;	
	}
	
	//游戏结果(-1:失败;0:正在运行;1:胜利)
	public int result(){
		if(result == -1)
			return result;
		else{
			int num = 0;
			for(int i = 1;i <= y;i++){
	  			for(int j = 1;j <= x;j++){
	  				int index = getIndex(j, i);
	  				if(blocks[index].getShow() != 1)
	  					num++;
	  			}
			}
			if(num == mineNum)
				result = 1;
			return result;	
		}
	}
	
	//控制台输出
	public void show(){
		for(int i = 1;i <= y;i++){
  			for(int j = 1;j <= x;j++){
  				int index = getIndex(j, i);
  				System.out.print(blocks[index].getShow() + " ");
  			}
  			System.out.println();
		}
	}
	
	//重新开始
	
	//根据坐标(x,y)求得数据的数组索引(外索引)
	public int getIndex(int x,int y){
		return y * xSize + x;
	}
	//根据坐标(x,y)求得数据的数组索引(内索引)
	public int getIndexIn(int x,int y){
		return y * this.x + x;
	}
	//根据坐标的length(1开始)求得坐标(x,y)
	public int[] getXY(int length){
		int[] xy = new int[2];
		xy[1] = (length % this.x != 0)?(length / this.x + 1):(length / this.x);
		xy[0] = length - this.x * (xy[1] - 1);
		
		return xy;
	}
	
	//计算 Block 的 num 层
	int getBlockNum(int x,int y){
		return ftn(x - 1,y - 1) + ftn(x,y - 1) + ftn(x + 1,y - 1) + 
				ftn(x - 1,y) + ftn(x,y) + ftn(x + 1, y) + 
				ftn(x - 1, y + 1) + ftn(x, y + 1) + ftn(x + 1, y + 1);
				
	}
	//九宫格中的单个flagToNum
	int ftn(int x,int y){
		return blocks[getIndex(x,y)].flagToNum();
	}
	

	public static void main(String[] args) {
		
	}

}
