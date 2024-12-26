package com.min.app06.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.app06.dto.ContactDto;

/*
 * JDBC
 * 1. Java DataBase Connection
 * 2. Java에서 DataBase에 접근할 수 있도록 해 주는 Programming API
 * 3. WebApplication - JDBC Interface - MySQL JDBC Driver - MySQL DBMS
 *        app06      - ContactDaoImpl - mysql-connector-j - MySQL Server
 */
/*
 * DAO
 * 1. DataBase Access Object 
 * 2. 데이터 베이스에 접속해서 DB 처리를 수행하는 객체입니다.
 * 3. 기본적으로 Singleton Patten으로 생성합니다. (스프링은 Spring Container 에 bean을 만들 때 기본적으로 singleton 으로 만듭니다.)
 */
@Repository // DAO를 구현한 클래스라는 의미를 가진 @Component입니다. (Spring Container에 bean으로 등록됩니다.) 
            // Persistence Layer(영속 계층)에서 동작합니다.
            // 데이터 엑세스 예외 처리(Rollback) 기능을 가집니다.

public class ContactDaoImpl implements IContactDao {
  
  @Autowired //Spring Container에서 JdbcConnection 클래스 타입의 bean 을 가져와서 필드에 넣습니다.(필드 주입)
  private JdbcConnection jdbcConnection;
  
  private Connection conn;
  private PreparedStatement ps;
  private ResultSet rs;
  
  @Override
  public List<ContactDto> getContactList() {
    // SELECT 결과 목록을 저장할 List 입니다.
    List<ContactDto> contacts = new ArrayList<ContactDto>();
    // 데이터베이스에 접속합니다.
    conn = jdbcConnection.getConnection();
    try {
      // 실행한 쿼리문입니다.
      String sql = "SELECT contact_id, last_name, first_name, email, mobile, create_dt FROM tbl_contact";
      // 쿼리문을 실행할 PreparedStatement 객체를 만드는 방법입니다.
      ps = conn.prepareStatement(sql);
      // SELECT 쿼리를 실행하는 방법입니다.
      rs = ps.executeQuery(); 
      // SELECT 결과 행(Row)이 있으면 순서대로 하나씩 반복합니다.
      while(rs.next()) {
        // 결과 행(Row)의 각 열(Column) 값을 가져옵니다.
        int contact_id = rs.getInt("contact_id");
        String last_name = rs.getString("last_name");
        String first_name = rs.getString("first_name");
        String email = rs.getString("email");
        String mobile = rs.getString("mobile");
        Date create_dt = rs.getDate("create_dt");
        // 각 열(Column)의 값을 ContactDto 객체로 만듭니다.
        // @Builder (빌더 패턴)을 이용해 보겠습니다.
        ContactDto contactDto = ContactDto.builder()
                                  .contact_id(contact_id)
                                  .last_name(last_name)
                                  .first_name(first_name)
                                  .email(email)
                                  .mobile(mobile)
                                  .create_dt(create_dt)
                                  .build();
        // 완성된 ContactDto 객체를 List에 저장합니다.
        contacts.add(contactDto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 사용한 자원을 반납합니다.
    jdbcConnection.close(conn, ps, rs);
    // SELECT 결과 목록 반환합니다.
    return contacts;
  }

  @Override
  public ContactDto getContactById(int contact_id) {
    // 결과 행 1개를 저장할 ContactDto 객체를 만듭니다. 결과 행이 없을 수도 있으르모 null 값을 초기화
    ContactDto contactDto = null;
    // 데이터베이스에 접속합니다.
    conn = jdbcConnection.getConnection();
    try {
      // 실행할 쿼리문을 가장 먼저 만들어야 합니다.
      String sql = "SELECT contact_id, last_name, first_name, email, mobile, create_dt FROM tbl_contact WHERE contact_id = ?";
      // PreparedStatment 객체를 만듭니다.
      ps = conn.prepareStatement(sql);
      // 쿼리문에 물음표(위치 홀더)가 존재하면 해당 위치에 값을 전달해야 합니다.
      // 각 물음표(위치 홀더)의 구분은 숫자로 합니다. 첫 번째 물음표의 위치는 1입니다.
      ps.setInt(1, contact_id); // 첫 번째 물음표(위치 홀더)에 contact_id 를 전달합니다.
      // 쿼리문을 실행하고 결과를 ResultSet으로 받습니다.
      rs = ps.executeQuery();
      // 결과 행은 0 또는 1개이므로 if문으로 결과 행의 존재 여부를 파악합니다.
      if(rs.next()) {
        //  결과 행(Row)의 각 열(Column) 값을 가져와서 ContactDto 객체로 만듭니다.
        // @Builder (빌더 패턴)을 이용해 보겠습니다.
        contactDto = ContactDto.builder()
                        .contact_id(rs.getInt("contact_id"))
                        .last_name( rs.getString("last_name"))
                        .first_name(rs.getString("first_name"))
                        .email(rs.getString("email"))
                        .mobile(rs.getString("mobile"))
                        .create_dt(rs.getDate("create_dt"))
                        .build();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 사용한 자원을 반납합니다.
    jdbcConnection.close(conn, ps, rs);
    // 결과 행 1개를 저장한 ContactDto 객체를 반환합니다.
    return contactDto;
  }

  @Override
  public int getContactCount() {
    int amount = 0;
    conn = jdbcConnection.getConnection();
    try {
      String sql = "SELECT COUNT(*) AS amount FROM tbl_contact";
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      if(rs.next()) {
        amount = rs.getInt("amount");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    jdbcConnection.close(conn, ps, rs);
    return amount;
  }
 
  @Override // 등록
  public int register(ContactDto contactDto) {
    // 등록 결과를 저장할 변수입니다.
    int result = 0; // 초기 상태는 등록 실패 상태입니다.
    
    // 데이터베이스에 접속합니다.
    conn = jdbcConnection.getConnection();
    try {
      // 실행할 쿼리문을 작성합니다. 인자 값은 ?로 표시합니다.
      String aql = "INSERT INTO tbl_contact VALUES (null, ?, ?, ?, ?, CURDATE())";
      // PresparedStatement 객체를 생성합니다.
      ps = conn.prepareStatement(aql);
      // 쿼리문에 인자 값을 전달합니다.
      ps.setString(1, contactDto.getLast_name());
      ps.setString(2, contactDto.getFirst_name());
      ps.setString(3, contactDto.getEmail());
      ps.setString(4, contactDto.getMobile());
      // 쿼리문을 실행하고 결과를 받습니다. 결과가 0이면 등록 실패이고, 1이면 등록 성공입니다.
      result = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 사용한 자원을 반납합니다.
    jdbcConnection.close(conn, ps, rs);
    //등록 결과를 반환합니다.
    return result;
  }

  @Override // 수정
  public int modify(ContactDto contactDto) { 
    int result = 0;
    conn = jdbcConnection.getConnection();
    try {
      String sql = "UPDATE tbl_contact SET last_name = ?, first_name = ?, email = ?, mobile = ? WHERE contact_id = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, contactDto.getLast_name());
      ps.setString(2, contactDto.getFirst_name());
      ps.setString(3, contactDto.getEmail());
      ps.setString(4, contactDto.getMobile());
      ps.setInt(5, contactDto.getContact_id());
      result = ps.executeUpdate();
      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    jdbcConnection.close(conn, ps, rs);
    return result;
  }

  @Override // 삭제
  public int remove(int contact_id) {
    int result = 0;
    conn = jdbcConnection.getConnection();
    try {
      String sql = "DELETE FROM tbl_contact WHERE contact_id = ?";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, contact_id);
      result = ps.executeUpdate();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    jdbcConnection.close(conn, ps, rs);
    
    return result;
  }



}
