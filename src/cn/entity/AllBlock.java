package cn.entity;

//����
public class AllBlock {
	//x,y���û�ָ���������С,��ѧ����
	private int x = 1;
	private int y = 1;
	//�ײ����ݵĴ�С,��������
	private int xSize = 3;
	private int ySize = 3;
	//���п�
	private Block[] blocks = null;
	//�׵���Ŀ
	private int mineNum = 0;
	//��������������
	private Integer[] mines = null;
	//result(-1:ʧ��;0:��������;1:ʤ��)
	private int result = 0;



	//��ʼ������
	public AllBlock(int x,int y,int mineNum){
		this.x = x;
		this.y = y;
		xSize = x + 2;
		ySize = y + 2;
		blocks = new Block[xSize * ySize];//�����еĶ���û��ʵ����
		this.mineNum = mineNum;
		mines = new Integer[mineNum];
		//ʵ���� blocks
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
	//����
	public void layMines(){
		//����mineNum ���ײ��ֲ�
		for(int i = 0;i < mineNum;i++){
			//�ֲ��� 1~x*y �������
			int randnum = (int) (Math.random() * x * y) + 1;
			//��������ѧ����
			int y = getXY(randnum)[1];
			int x = getXY(randnum)[0];
			//�����ڵ���������
			int index = getIndex(x,y);
			//��ֹ������ظ�
			for(int j = 0;j < i;j++){
				if(mines[j] == index){
					i--;
					continue;
				}	
			}
			//�洢�׵���������
			mines[i] = index;
			//�޸� blocks �� flag ��
			blocks[index].setFlag(true);
		}
		//�޸�Block�� num ��
		for(int i = 1;i <= y;i++){
			for(int j = 1;j <= x;j++){
				int index = getIndex(j, i);
				blocks[index].setNum(getBlockNum(j, i));
			}
		}
	}
	//����һ�(�����-1,��ȡ��-1;��֮,����-1)
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
	
	//�������Ľ��(��Ҫ����:�Ƿ�Ϊ��,num �Ƿ�Ϊ0;�ݹ���Ҫ����:�Ƿ񷭹�,�Ƿ�Խ��,num �Ƿ�Ϊ0)
	public int click(int length){
		int y = getXY(length)[1];
		int x = getXY(length)[0];
		return click(x,y);
	}
	public int click(int x,int y){
		int index = getIndex(x, y);
		//����Ƿ񱻷���
		if(blocks[index].getShow() == 1){
			return result;
		}
		//����Ƿ񳬽�
		if(x < 1 || x > this.x || y < 1 || y > this.y)
			return result;
		//���� show ����
		blocks[index].setShow(1);	
		//����Ƿ��׿�,��Ϊ num = 0,���Աض�isFlag == false;
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
	
	//��Ϸ���(-1:ʧ��;0:��������;1:ʤ��)
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
	
	//����̨���
	public void show(){
		for(int i = 1;i <= y;i++){
  			for(int j = 1;j <= x;j++){
  				int index = getIndex(j, i);
  				System.out.print(blocks[index].getShow() + " ");
  			}
  			System.out.println();
		}
	}
	
	//���¿�ʼ
	
	//��������(x,y)������ݵ���������(������)
	public int getIndex(int x,int y){
		return y * xSize + x;
	}
	//��������(x,y)������ݵ���������(������)
	public int getIndexIn(int x,int y){
		return y * this.x + x;
	}
	//���������length(1��ʼ)�������(x,y)
	public int[] getXY(int length){
		int[] xy = new int[2];
		xy[1] = (length % this.x != 0)?(length / this.x + 1):(length / this.x);
		xy[0] = length - this.x * (xy[1] - 1);
		
		return xy;
	}
	
	//���� Block �� num ��
	int getBlockNum(int x,int y){
		return ftn(x - 1,y - 1) + ftn(x,y - 1) + ftn(x + 1,y - 1) + 
				ftn(x - 1,y) + ftn(x,y) + ftn(x + 1, y) + 
				ftn(x - 1, y + 1) + ftn(x, y + 1) + ftn(x + 1, y + 1);
				
	}
	//�Ź����еĵ���flagToNum
	int ftn(int x,int y){
		return blocks[getIndex(x,y)].flagToNum();
	}
	

	public static void main(String[] args) {
		
	}

}
