package com.p.project;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;

//MySQL, JDBC 연동 테스트 코드
public class MySQLConnectionTest{

private static final String DRIVER="com.mysql.cj.jdbc.Driver";
private static final String URL="jdbc:mysql://localhost:3306/spring_ex?useSSL=false&serverTimezone=Asia/Seoul";
private static final String USER="root";
private static final String PASSWORD="12345";

	@Test
	public void testConnection() throws Exception{
		Class.forName(DRIVER);
		
		try(Connection connection=DriverManager.getConnection(URL, USER, PASSWORD)){
			System.out.println(connection);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
