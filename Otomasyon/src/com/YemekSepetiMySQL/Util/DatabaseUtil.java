package com.YemekSepetiMySQL.Util;
import java.sql.*;

public class DatabaseUtil {
	static Connection conn=null;
	
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
}
