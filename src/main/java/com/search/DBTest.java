package com.search;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {
	public static void main(String[] args) {
		String url="jdbc:mysql://127.0.0.1:3306/SearchDB";
		String user="root";
		String password="@Shiva_9490";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,user,password);
			System.out.println("Connected Succesfully");
			con.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
	}

}
