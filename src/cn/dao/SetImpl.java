package cn.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.dao.BaseDao;
import cn.entity.GameSet;

public class SetImpl implements SetDao{
	String table = "landmineset";

	@Override
	//增加没有MyIndex属性(自增)的对象
	public void insert(GameSet gameSet) {
		Object[] values = getValues(gameSet);
		int length = values.length;
		Object[] valuesNoIndex = new Object[length - 1];
		for(int i = 1;i < length;i++){
			valuesNoIndex[i - 1] = values[i];
		}
		BaseDao.insertNoIndex(table, valuesNoIndex);
	}

	@Override
	public void update(GameSet gameSet){
		//属性列表
		String[] attributes = BaseDao.getAttributes(table);
		//values
		Object[] values = getValues(gameSet);
		if(values.length != attributes.length)
			values = null;	
		BaseDao.update(table, attributes, values, (Integer) values[0]);	
	}

	@Override
	public GameSet select() {
		GameSet gameSet = new GameSet(0,0,0);
		ResultSet rs = BaseDao.selectObject(table, "myIndex", 1);
		try {
			while(rs.next()){
				gameSet.setMyIndex(rs.getInt(1));
				gameSet.setxLength(rs.getInt(2));
				gameSet.setyLength(rs.getInt(3));
				gameSet.setMineNum(rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gameSet;
	}

	@Override
	public Object[] getValues(GameSet gameSet) {
		Object[] values = {gameSet.getMyIndex(),gameSet.getxLength(),
				gameSet.getyLength(),gameSet.getMineNum()};
		return values;
	}

	@Override
	public void close() {
		BaseDao.close();
	}	
	
	public static void main(String[] args){
		SetImpl set = new SetImpl(); 
		GameSet gameSet = new GameSet(9, 9, 1);
		String[] attributes = BaseDao.getAttributes(set.table);
		//values
		Object[] values = set.getValues(gameSet);
		System.out.print(attributes);
		System.out.print(values);
		
	}
}
