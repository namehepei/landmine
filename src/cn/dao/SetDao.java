package cn.dao;

import cn.entity.GameSet;

interface SetDao {
	//��
	public void insert(GameSet gameSet);	
	//��
	public void update(GameSet gameSet);
	//��
	public GameSet select();
	//��ȡ����
	public Object[] getValues(GameSet gameSet);
	//�ر�����
	public void close();

}
