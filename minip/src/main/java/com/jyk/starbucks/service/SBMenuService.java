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
	

//	
//		
//	
//		
//	
//		
//	
//		
	// 장바구니
	public List<SBMenuInfo> viewCart() {
		List<SBMenuInfo> viewCart = new ArrayList<SBMenuInfo>();
		SBMenuInfo vo;
		String sql = "CREATE OR REPLACE VIEW V_CART AS SELECT W.ID, M.MENU_NAME, M.MENU_PRICE, M.MENU_CART, FROM MENU M, MEMBERS W WHERE W.SIGNINWHO = 1 AND M.MENU_CART IS NOT NULL";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				vo = new SBMenuInfo();
				vo.setMenuorder(i++);
				vo.setMenu_name(rs.getString("menu_name"));
				vo.setMenu_price(rs.getInt("menu_price"));
				vo.setMenu_avl(rs.getInt("menu_avl"));
				vo.setCart(rs.getInt("menu_cart"));
				viewCart.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewCart;
		

	}
	
	
	// 카드 충전
		public int cardTopUp(CardInfo vo) {
			int n = 0;
			String id = null;
			String sql1 = "SELECT ID FROM MEMBERS WHERE SIGNINWHO = 1"; // 현재 로그인 세션 검증
			String sql2 = "UPDATE CARDS SET CARD_IN = CARD_IN + ? WHERE CARD_NO = ? AND ID = ?";
			try {
				psmt = conn.prepareStatement(sql1);
				rs = psmt.executeQuery();
				if (rs.next()) {
					id = rs.getString("id");
				}
				psmt = conn.prepareStatement(sql2);
				psmt.setInt(1, vo.getCard_in());
				psmt.setString(2, vo.getCard_no());
				psmt.setString(3, id);
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
//		

}
