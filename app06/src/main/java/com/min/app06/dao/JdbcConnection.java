package com.min.app06.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

@Component // Spring Container에 JdbcConnection 객체를 저장해 둡니다.
public class JdbcConnection {

  /**
   * 접속 메소드
   * 1. MySQL Driver
   *    com.mysql.cj.jdbc.Driver
   * 2. MySQL URL 
   *    jdbc:mysql://127.0.0.1:3306/db_app06?serverTimezone=Asia/Seoul
   * 3. User
   *    greenit / greenit
   * @return java.sql.Connection DataBase 접속 정보를 처리하는 인터페이스 
   */
  public Connection getConnection() {
    Connection conn = null;
    try {
      String driver = "com.mysql.cj.jdbc.Driver";
      String url = "jdbc:mysql://127.0.0.1:3306/db_app06?serverTimezone=Asia/Seoul";
      String username = "greenit";
      String password = "greenit";
      // 드라이버 클래스 로드
      Class.forName(driver);
      // 접속 (DriverManager 클래스가 getConnection() 메소드가 접속을 수행합니다.)
      conn = DriverManager.getConnection(url, username, password);
            
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }
  /**
   * 접속 해제 메소드
   * @param java.sql.Connection DataBase 접속 정보를 처리하는 인터페이스 
   * @param java.sql.PreparedStatement 쿼리문을 실행하고 그 결과를 반환하는 인터페이스
   * @param java.sql.ResultSet 테이블의 행(Row) 정보를 가리키는 Cursor 를 제공하는 인터페이스
   */
  public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
    try {
      if(conn != null) conn.close();
      if(ps != null) ps.close();
      if(rs != null) rs.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
