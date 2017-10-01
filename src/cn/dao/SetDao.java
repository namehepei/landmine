package cn.dao;

import cn.entity.GameSet;

interface SetDao {
	//增
	public void insert(GameSet gameSet);	
	//改
	public void update(GameSet gameSet);
	//查
	public GameSet select();
	//提取属性
	public Object[] getValues(GameSet gameSet);
	//关闭连接
	public void close();

}
