package cn.entity;

//ɨ���еĵ�����
public class Block {
	//���ݵײ�:true��ʾ����;false��ʾ����
	private boolean flag = false;
	//���ݶ���:0`8����Ź������׵���Ŀ;
	private int num = 0;
	//��ʾ��:-1����С����,0������ʾ,1������ʾ;
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
