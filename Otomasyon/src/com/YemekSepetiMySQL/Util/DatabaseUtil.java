package com.YemekSepetiMySQL.Util;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DatabaseUtil {
	static Connection conn=null;
	static Connection connection=null;
    static PreparedStatement query=null;
    static ResultSet result=null;
	

	public static Connection Connect()
	{
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost/yemeksepeti","root","");
			return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
	}
	
	public static void Select()
	{

	}
	
	public static void Delete(Integer userID)
	{
		connection=DatabaseUtil.Connect();
		String sql="delete from login where userID=?";
		
		try {
    		query=connection.prepareStatement(sql);
    		query.setInt(1, userID);
    		query.executeUpdate();
    		
			} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
	}
	
	public static void Add(String userName,String password,String adress,String title)
	{
		connection=DatabaseUtil.Connect();
		String sql="insert into login(userName, password, title, adress) values(?,?,?,?)";
		
		try {
    		query=connection.prepareStatement(sql);
    		query.setString(1, userName);
    		query.setString(2, password);
    		query.setString(3, title);
    		query.setString(4, adress);
    		query.executeUpdate();
    		
    		} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
	}
	
	public static void Update(String sql)
	{
		connection=DatabaseUtil.Connect();
		try {
    		query=connection.prepareStatement(sql);
     		query.executeUpdate();
     		
    		} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
	}
	
	public static String MD5(String input)
	{
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			byte[] encrypted=md5.digest(input.getBytes());
			BigInteger no=new BigInteger(1,encrypted);
			String hash=no.toString(16);
			while(hash.length()<32) {
				hash="0"+hash;
			}
			return hash;
		} catch (NoSuchAlgorithmException e) {
			
			throw new RuntimeException(e);
		}
	}
	
}
