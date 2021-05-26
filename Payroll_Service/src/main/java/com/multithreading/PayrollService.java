package com.multithreading;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class PayrollService {
	
	 public static void main(String[] args) {
			
		    PayrollData[] payrolldata = {
				new PayrollData("Mark","HR","M",60000),
				new PayrollData("Jeff","Sales","M",40000),
				new PayrollData("Bill","Marketing","M",60000),
				new PayrollData("Tracy","IT","F",80000),
				new PayrollData("Clare","HR","F",55000),
				new PayrollData("Jermy","Finance","M",67000)
		    };
		    
			PayrollRepo repo = new PayrollRepo();	
			Instant start = Instant.now();
			
			Arrays.stream(payrolldata).forEach(value -> {
				Runnable task = () ->{
					try {
						repo.insertMultipleRecord(value);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				};
			});
			
			Instant end = Instant.now();
			System.out.println("Duration without thread:" +Duration.between(start, end));
		}
}
