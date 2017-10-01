package cn.entity;

//扫雷中的单个块
public class Block {
	//数据底层:true表示有雷;false表示无累
	private boolean flag = false;
	//数据顶层:0`8代表九宫格中雷的数目;
	private int num = 0;
	//显示层:-1代表小红旗,0代表不显示,1代表显示;
	private int show = 0;
	
	//
	public boolean isFlag() {	return flag;}
	public void setFlag(boolean flag) {	this.flag = flag;}
	public int getNum() {	return num;}
	public void setNum(int num) {	this.num = num;}
	public int getShow() {	return show;}
	public void setShow(int show) {	this.show = show;}
	
	//
	public int flagToNum(){
		return flag?1:0;
	}
	
}
