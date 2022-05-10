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
		String sql = "INSERT INTO MEMBERS VALUES(?,?,?,?,DEFAULT,DEFAULT,DEFAULT,?,?,NULL)";
		try {
			Calendar cal = new GregorianCalendar();
			Timestamp ts = new Timestamp(cal.getTimeInMillis());
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getContact());
			psmt.setTimestamp(4, ts);
			psmt.setTimestamp(5, ts);
			psmt.setTimestamp(6, ts);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;

	}

	// 로그인
	@Override
	public int signIn(MembershipInfo vo) {
		int n = 0;
		String sql = "UPDATE MEMBERS SET SIGNINTHIS = ?, SIGNINWHO = 1 WHERE ID = ?"; // signwho=1(현재로그인)
		try {
			psmt = conn.prepareStatement(sql);
			Calendar cal = new GregorianCalendar();
			Timestamp ts = new Timestamp(cal.getTimeInMillis());
			psmt.setTimestamp(1, ts);
			psmt.setString(2, vo.getId());
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
		String sql = "UPDATE MEMBERS SET SIGNINLAST = SIGNINTHIS, SIGNINWHO = NULL WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	// 접속세션초기화
	@Override
	public int rollback() {
		int n = 0;
		String sql = "UPDATE MEMBERS SET SIGNINWHO = NULL";
		try {
			psmt = conn.prepareStatement(sql);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	
	
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
				member.setContact(rs.getString("contact"));
				member.setTier(rs.getString("tier"));
				member.setSignupdate(rs.getTimestamp("signupdate"));
				member.setStar(rs.getInt("star"));
				member.setCoupon(rs.getInt("coupon"));
				member.setSigninthis(rs.getTimestamp("signinthis"));
				member.setSigninlast(rs.getTimestamp("signinlast"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	// 개인정보수정
	@Override
	public int memberUpdate(MembershipInfo vo) {
		int n = 0;
		String sql = "UPDATE MEMBERS SET PASSWORD = ?, CONTACT = ? WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getPassword());
			psmt.setString(2, vo.getContact());
			psmt.setString(3, vo.getId());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;

	}

	// 회원탈퇴
	@Override
	public int memberDelete(MembershipInfo vo) {
		return 0;
	}

}
