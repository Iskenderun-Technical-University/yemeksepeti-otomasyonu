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
	
	public static void Select(String userName,String password)
	{
		connection=DatabaseUtil.Connect();
		String sql="select * from login where userName=? and password=?";
    	
    	try {
    		query=connection.prepareStatement(sql);
    		query.setString(1, userName);
    		query.setString(2, password);
    		
    		ResultSet result=query.executeQuery();
    		
    		if(!result.next())
    		{
    			System.out.println("Kullanıcı adı veya şifre hatalı");
    		}
    		else {
    			result.getString(1);
    			System.out.println("User ID: "+result.getString("userID"));
    			System.out.println("Title: "+result.getString("title"));
    			System.out.println("Username: "+result.getString("userName"));
    			System.out.println("Password: "+result.getString("password"));
    		}
    	} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
	}
	
	public static void Delete(String userName,String password)
	{
		connection=DatabaseUtil.Connect();
		String sql="delete from login where userName=? and password=?";
		
		try {
    		query=connection.prepareStatement(sql);
    		query.setString(1, userName);
    		query.setString(2, password);
    		query.executeUpdate();
    		
    		System.out.println("Kullanıcı silindi : "+userName);
    		
			} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
	}
	
	public static void Add(String userName,String password)
	{
		connection=DatabaseUtil.Connect();
		String sql="insert into login(userName, password, title) values(?,?,?)";
		
		try {
    		query=connection.prepareStatement(sql);
    		query.setString(1, userName);
    		query.setString(2, password);
    		query.setString(3, "0");
    		query.executeUpdate();
    		
    		System.out.println("Yeni kullanıcı eklendi : "+userName);
    		
    		} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
	}
	
	public static void Update(String userName,String password)
	{
		connection=DatabaseUtil.Connect();
		String sql="update login set password=? where userName=?";
		
		try {
    		query=connection.prepareStatement(sql);
    		query.setString(2, userName);
    		query.setString(1, password);
    		query.executeUpdate();
    		
    		System.out.println("Parola değiştirildi.");
    		
    		} catch (Exception e) {
    		System.out.println(e.getMessage().toString());
    	}
	}
	
	public static String MD5(String pass)
	{
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			byte[] encrypted=md5.digest(pass.getBytes());
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
