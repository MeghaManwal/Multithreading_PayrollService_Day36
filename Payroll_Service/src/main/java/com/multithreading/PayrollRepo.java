package com.multithreading;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PayrollRepo {
	
	public void insertMultipleRecord(PayrollData value) throws SQLException {
		
		Connection connection = null;
		PreparedStatement prepstatement = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll__service", "root", "");
			
		try {
			connection.setAutoCommit(false);
			String query = "insert into payroll(Name,department,gender, basicpay) value(?,?,?,?)";
			prepstatement = connection.prepareStatement(query);
			prepstatement.setString(1, value.Name);
			prepstatement.setString(2, value.department);
			prepstatement.setString(3, value.gender);
			prepstatement.setInt(4, value.basicPay);
			
			prepstatement.executeUpdate();
			connection.commit();

		}catch (SQLException e) {
			e.printStackTrace();
				connection.rollback();
				System.out.println("Rolled back Successfully");
			}catch (Exception e1) {
				e1.printStackTrace();
			}
		 }catch (Exception e) {
			e.printStackTrace();
		 }finally {
		 	if(connection != null) {
		 		connection.close();
			}
			if(prepstatement != null) {
				prepstatement.close();
			}
		 }
		
	   }

}
