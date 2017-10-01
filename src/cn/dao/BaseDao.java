package cn.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

//这个类不会涉及到类本身及其所有的属性/列名
public class BaseDao {
	//
	static String driver = null;
	static String url = null;
	static String username = null;
	static String password = null;
	static Connection conn = null;
	
	static{
		link();
	}
	//连接数据库(关联配置文件)
	public static void link(){
		Properties params = new Properties();
		String propertiesPath = "database.properties";
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(propertiesPath);
        //读取配置文件
		try {
			params.load(is);
			driver = params.getProperty("driver");
			url = params.getProperty("url");
			username = params.getProperty("username");
			password = params.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//加载驱动
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//建立连接
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//关闭数据库连接
	public static void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//获取列列名称,与类对应一致
	static String[] getAttributes(String table){
		ResultSetMetaData rsd = null;
		String[] attributes = null;
		try {
			rsd = selectObject(table, "myIndex", 1).getMetaData();
			int columns = rsd.getColumnCount();
			attributes = new String[columns];
			//rsd.getColumnName(i) i从1开始
			for(int i = 1;i <= columns;i++){
				attributes[i - 1] = rsd.getColumnName(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return attributes;
	}

	//增
	static boolean insertNoIndex(String table,Object[] values){
		boolean flag = false;
		StringBuffer sql = new StringBuffer("INSERT INTO ");
		sql.append(table);
		sql.append(" (");
		String[] attributes = getAttributes(table);
		for(int i = 1;i < attributes.length;i++){
			sql.append(attributes[i]);
			if(i < attributes.length - 1){
				sql.append(",");
			}
		}
		sql.append(") ");
		sql.append(" VALUES(");
		for(int i = 0;i < values.length;i++){
			sql.append(values[i]);
		}
		sql.append(")");
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			flag = pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	static boolean insert(String table,String[] attribute,Object[] value,int index){
		boolean flag = false;
		StringBuffer sql = new StringBuffer("INSERT INTO ");
		sql.append(table);
		sql.append(" SET ");
		for(int i = 0;i < attribute.length;i++){
			sql.append(attribute[i]);
			sql.append(" = ?");
			if(i < attribute.length - 1){
				sql.append(",");
			}
		}
		sql.append(" WHRER myindex = ");
		sql.append(index);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			for(int i = 1;i <= attribute.length;i++){
				pstmt.setObject(i,value[i - 1]);
			}
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	//删
	static boolean delete(String table,int index){
		boolean flag = false;
		StringBuffer sql = new StringBuffer("DELETE ");
		sql.append(table);
		sql.append(" WHERE myindex = ");
		sql.append(index);
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			flag = pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return flag;
		
	}
	//改(不改变myIndex的值,一一替换其他属性的值)
	static boolean update(String table,String[] attribute,Object[] value,int index){
		boolean flag = false;
		StringBuffer sql = new StringBuffer("UPDATE ");
		sql.append(table);
		sql.append(" SET ");
		for(int i = 1;i < attribute.length;i++){
			sql.append(attribute[i]);
			sql.append(" = ?");
			if(i < attribute.length - 1){
				sql.append(",");
			}
		}
		sql.append(" WHERE myindex = ");
		sql.append(index);
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			for(int i = 1;i < attribute.length;i++){
				pstmt.setObject(i,value[i]);
			}
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	//show
	static void show(ResultSet rs) {
		ResultSetMetaData rsd = null;
		try {
			rsd = rs.getMetaData();
			int columns = rsd.getColumnCount();
			for(int i = 1;i <= columns;i++){
				System.out.print(rsd.getColumnName(i));
				System.out.print("\t");
			}
			System.out.print("\n");
			while(rs.next()){
				for(int i = 1;i <= columns;i++){
					System.out.print(rs.getObject(i));
					System.out.print("\t");
				}
				System.out.print("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//table中所有的行
	//select 所有行
	static ResultSet selectAllObject(String table){
		String[] s = null;
		return selectObject(table, s, null);
	}
	//select column = value 的行
	//
	static ResultSet selectObject(String table,String column,Object value){
		String[] attributes = {column};
		Object[] values =  {value};
		return selectObject(table, attributes, values);
	}
	//select column[] = value 的行
	static ResultSet selectObject(String table,String[] attributes,Object[] values){
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ");
		sql.append(table);
		if(attributes != null){
			sql.append(" WHERE ");
			for(int i = 0;i < attributes.length;i++){
				sql.append(attributes[i]);
				sql.append(" = ");
				sql.append(values[i]);
				if(i < attributes.length - 1){
					sql.append(" AND ");
				}
			}
		}
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
