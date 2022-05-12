package com.jyk.starbucks.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jyk.starbucks.dao.DataSource;
import com.jyk.starbucks.vo.CardFace;
import com.jyk.starbucks.vo.CardInfo;
import com.jyk.starbucks.vo.SBMenuInfo;

public class SBMenuService {
	
	private DataSource dataSource = DataSource.getInstance();
	private Connection conn = dataSource.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;
	CardInfo card = new CardInfo();
	MembershipManager m = new MembershipManager();
	
	
	// 메뉴 목록
		public List<SBMenuInfo> menuDisplay(String type) { 
			List<SBMenuInfo> display = new ArrayList<SBMenuInfo>();
			SBMenuInfo vo;
			String sql = "SELECT MENU_NAME, MENU_PRICE, MENU_AVL FROM MENU WHERE MENU_TYPE2 = ? ORDER BY MENU_NAME";
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, type);
				rs = psmt.executeQuery();
				int i = 1; 
				while (rs.next()) { 
					vo = new SBMenuInfo();
					vo.setMenuorder(i++); 
					vo.setMenu_name(rs.getString("menu_name"));
					vo.setMenu_price(rs.getInt("menu_price"));
					vo.setMenu_avl(rs.getInt("menu_avl"));
					display.add(vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return display;

		}
	
	
	

}
