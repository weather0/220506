package com.jyk.starbucks.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.jyk.starbucks.dao.DataSource;
import com.jyk.starbucks.vo.MembershipInfo;

public class MembershipServiceImple implements MembershipService {
	private DataSource dataSource = DataSource.getInstance();
	private Connection conn = dataSource.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;
	MembershipInfo member = new MembershipInfo();

	// 회원가입
	@Override
	public int signUp(MembershipInfo vo) {
		int n = 0;
		String sql = "INSERT INTO MEMBERS VALUES(?,?,?,?,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
		try {
			Calendar cal = new GregorianCalendar();
			Timestamp ts = new Timestamp(cal.getTimeInMillis());
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getContact());
			psmt.setString(5, vo.getSecq());
			psmt.setString(6, vo.getSeca());
			psmt.setTimestamp(7, ts);
			psmt.setTimestamp(8, ts);
			psmt.setTimestamp(9, ts);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;

	}

//	
//	
//	
//	
//	
//	
//
	// 로그인
	@Override
	public int signIn(MembershipInfo vo) {
		int n = 0;
//		String sql = "UPDATE MEMBERS SET SIGNINTHIS = ?, SIGNINWHO = ? WHERE ID = ?"; // signwho=(현재로그인)
		String sql = "UPDATE MEMBERS SET SIGNINTHIS = ? WHERE ID = ?"; // signwho=(현재로그인)
		try {
			psmt = conn.prepareStatement(sql);
			Calendar cal = new GregorianCalendar();
			Timestamp ts = new Timestamp(cal.getTimeInMillis());
			psmt.setTimestamp(1, ts);
			psmt.setString(2, vo.getId());
//			psmt.setString(3, vo.getId());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	// 로그아웃
	@Override
	public int signOut(MembershipInfo vo) {
		int n = 0;
		String sql = "UPDATE MEMBERS SET SIGNINLAST = SIGNINTHIS WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

//	
//	
//	
//	
//	
//	
//
	// ID찾기
	@Override
	public String[] findID(String email, String contact) {
		String sql1 = "SELECT ID FROM MEMBERS WHERE EMAIL = ?";
		String sql2 = "SELECT ID FROM MEMBERS WHERE CONTACT = ?";
		String fi1 = null;
		String fi2 = null;
		String fi[] = new String[2];
		try {
			psmt = conn.prepareStatement(sql1);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if (rs.next()) {
				fi1 = rs.getString("id");
				fi[0] = fi1;
			}

			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, contact);
			rs = psmt.executeQuery();
			if (rs.next()) {
				fi2 = rs.getString("id");
				fi[1] = fi2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fi;
	}

//		
//		
//		
//		
//		
//		
//		
	// 비밀번호 찾기
	@Override
	public String[] findPW(String id) {
		String sql = "SELECT PASSWORD, SECQ, SECA FROM MEMBERS WHERE ID = ?";
		String pw = null;
		String secq = null;
		String seca = null;
		String fp[] = new String[3];
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				pw = rs.getString("password");
				secq = rs.getString("secq");
				seca = rs.getString("seca");
				fp[0] = pw;
				fp[1] = secq;
				fp[2] = seca;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fp;
	}

//		
//		
//		
//		
//		
//		
//		
	// 개인정보조회
	@Override
	public MembershipInfo memberView(MembershipInfo vo) {
		String sql = "SELECT * FROM MEMBERS WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setEmail(rs.getString("email"));
				member.setContact(rs.getString("contact"));
				member.setTier(rs.getString("tier"));
				member.setStar(rs.getInt("star"));
				member.setCoupon(rs.getInt("coupon"));
				member.setSignupdate(rs.getTimestamp("signupdate"));
				member.setSigninthis(rs.getTimestamp("signinthis"));
				member.setSigninlast(rs.getTimestamp("signinlast"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

//	
//	
//	
//	
//	
//	
//
	// 개인정보수정
	@Override
	public int memberUpdate(MembershipInfo vo, String mod) {
		int n = 0;
		try {
			switch (mod) {
			case "1":
				String sql1 = "UPDATE MEMBERS SET PASSWORD = ? WHERE ID = ?";
				psmt = conn.prepareStatement(sql1);
				psmt.setString(1, vo.getPassword());
				psmt.setString(2, vo.getId());
				break;
			case "y":
				String sqlsec = "UPDATE MEMBERS SET SECQ = ?, SECA = ? WHERE ID = ?";
				psmt = conn.prepareStatement(sqlsec);
				psmt.setString(1, vo.getSecq());
				psmt.setString(2, vo.getSeca());
				psmt.setString(3, vo.getId());
				break;
			case "2":
				String sql2 = "UPDATE MEMBERS SET EMAIL = ? WHERE ID = ?";
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, vo.getEmail());
				psmt.setString(2, vo.getId());
				break;
			case "3":
				String sql3 = "UPDATE MEMBERS SET CONTACT = ? WHERE ID = ?";
				psmt = conn.prepareStatement(sql3);
				psmt.setString(1, vo.getContact());
				psmt.setString(2, vo.getId());
				break;
			}
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;

	}

//	
//	
//	
//	
//	
//	
//
	// 회원탈퇴
	@Override
	public int memberDelete(MembershipInfo vo) {
		return 0;
	}

}
