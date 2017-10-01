package cn.entity;

import cn.dao.SetImpl;

public class GameSet {
	int myIndex = 1;//有且仅有一个设置表
	int xLength = 0;
	int yLength = 0;
	int mineNum = 0;
	
	//
	public int getMyIndex() {	return myIndex;}
	public void setMyIndex(int myIndex) {	this.myIndex = myIndex;}
	public int getxLength() {	return xLength;}
	public void setxLength(int xLength) {	this.xLength = xLength;}
	public int getyLength() {	return yLength;}
	public void setyLength(int yLength) {	this.yLength = yLength;}
	public int getMineNum() {	return mineNum;}
	public void setMineNum(int mineNum) {	this.mineNum = mineNum;}
	
	//
	public GameSet(int xLength,int yLength,int mineNum){
		this.mineNum = mineNum;
		this.xLength = xLength;
		this.yLength = yLength;
	}
	
	//更改设置表
	public static boolean charge(GameSet gameSetN){
		SetImpl set = new SetImpl();
		//有且仅有一个表,故Index均为1
		set.update(gameSetN);
		return true;
	}
	
	//查看设置表
	public static GameSet show(){
		SetImpl set = new SetImpl();
		GameSet gameSet = set.select();
		return gameSet;
	}
}
