package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void getConn() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context)initctx.lookup("java:comp/env");
			DataSource ds = (DataSource)envctx.lookup("jdbc/pool");
			//datasource
			con = ds.getConnection();
			
			
			} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	// 하나의 새로운 게시글이 넘어 와서 저장됩니다.
	public void insertBoard(BoardBean board) {
		// 빈클래스에 넘어오지 않았던 데이터를 세팅해주어야합니다.
		// 11개의필드 
		
		int ref = 0; // 글 그룹을 의미 쿼리를 실행 시켜서 가장 큰 ref 값을 가져온뒤 + 1해준다.
		int re_step = 1; // 새글 이니까
		int re_level = 1; // 새글 이니까
		
		try {
			getConn();
			// 가장 큰 ref값을 가져오는 쿼리 
			String refSQL = "SELECT max(ref) from board";
			// 쿼리 실행 객체 생성
			pstmt = con.prepareStatement(refSQL);
			rs = pstmt.executeQuery();

			// 쿼리의 결과값이 있다면 ?
			if(rs.next()) {
				ref = rs.getInt(1) + 1;
				
			}
			
			// 실제로 게시글 전체 값을 테이블에 저장
			String sql = "INSERT INTO board (writer, email, subject, password, reg_date, ref, re_step, re_level, readcount, content)" +
					"VALUES (?, ?, ?, ?, NOW(), ?, ?, ?, 0, ?)";


			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getEmail());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, board.getContent());
			
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Vector<BoardBean> getAllBoard() {
		Vector<BoardBean> v = new Vector<>();
		getConn();
		try {
			String sql = "SELECT * FROM board ORDER BY ref DESC,re_step ASC, re_level ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean bean = new BoardBean();
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				v.add(bean);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	public BoardBean getOneBoard(int num) {

		BoardBean bean = new BoardBean();
		
		try {
			getConn();

			// Board 데이터 검색 쿼리
			String sql = "SELECT * FROM board WHERE num = ?";	
			
			// 조회수 증가 쿼리
			String readsql= "UPDATE board SET readcount = readcount + 1 WHERE num = ?";
			pstmt = con.prepareStatement(readsql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//Num,Writer,email,subject,password,reg_date[date],ref,re_step,re_level,readcount,content
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
}
