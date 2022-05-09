package com.jyk.starbucks.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jyk.starbucks.dao.DataSource;
import com.jyk.starbucks.vo.MembershipInfo;


public class MembershipServiceImple implements MembershipService {
	private DataSource dataSource = DataSource.getInstance();
	private Connection conn = dataSource.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;
	MembershipInfo member = new MembershipInfo();
	

	//개인정보조회
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
				member.setSignupdate(rs.getDate("signupdate"));
				member.setTier(rs.getString("tier"));
				member.setStar(rs.getInt("star"));
				member.setCoupon(rs.getInt("coupon"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	//회원가입
	@Override
	public int memberInsert(MembershipInfo vo) {
		int n = 0;
		String sql = "INSERT INTO MEMBERS VALUES(?,?,?,DEFAULT,DEFAULT,DEFAULT,DEFAULT)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			psmt.setString(3, vo.getContact());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
		
	}

	//개인정보수정
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

	//회원탈퇴
	@Override
	public int memberDelete(MembershipInfo vo) {
		return 0;
	}
	
	
	

	
}
