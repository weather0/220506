package com.jyk.starbucks.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.jyk.starbucks.dao.DataSource;
import com.jyk.starbucks.vo.CardFace;
import com.jyk.starbucks.vo.CardInfo;
import com.jyk.starbucks.vo.CartInfo;
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
		String sql = "SELECT MN_NAME, MN_PRICE FROM MENU WHERE MN_TYPE2 = ? ORDER BY MN_NAME";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, type);
			rs = psmt.executeQuery();
			int i = 1;
			while (rs.next()) {
				vo = new SBMenuInfo();
				vo.setMnorder(i++);
				vo.setMn_name(rs.getString("mn_name"));
				vo.setMn_price(rs.getInt("mn_price"));
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
	// 상품 정보
	public SBMenuInfo menuInfo(String pick) {
		SBMenuInfo mnInfo = new SBMenuInfo();
		String sql = "SELECT * FROM MENU WHERE MN_NAME = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pick);
			rs = psmt.executeQuery();
			if (rs.next()) {
				mnInfo.setMn_no(rs.getInt("mn_no"));
				mnInfo.setMn_name(rs.getString("mn_name"));
				mnInfo.setMn_price(rs.getInt("mn_price"));
				mnInfo.setMn_type1(rs.getString("mn_type1"));
				mnInfo.setMn_type2(rs.getString("mn_type2"));
				mnInfo.setMn_size0(rs.getString("mn_size0"));
				mnInfo.setMn_size1(rs.getString("mn_size1"));
				mnInfo.setMn_size2(rs.getString("mn_size2"));
				mnInfo.setMn_size3(rs.getString("mn_size3"));
				mnInfo.setMn_shot(rs.getInt("mn_shot"));
				mnInfo.setMn_esso(rs.getString("mn_esso"));
				mnInfo.setMn_frapro(rs.getInt("mn_frapro"));
				mnInfo.setMn_syr_frap(rs.getString("mn_syr_frap"));
				mnInfo.setMn_syr_dol(rs.getInt("mn_syr_dol"));
				mnInfo.setMn_syr_van(rs.getInt("mn_syr_van"));
				mnInfo.setMn_syr_haz(rs.getInt("mn_syr_haz"));
				mnInfo.setMn_syr_crm(rs.getInt("mn_syr_crm"));
				mnInfo.setMn_milk(rs.getString("mn_milk"));
				mnInfo.setMn_milk_hot(rs.getString("mn_milk_hot"));
				mnInfo.setMn_milk_foam(rs.getString("mn_milk_foam"));
				mnInfo.setMn_milk_vol(rs.getString("mn_milk_vol"));
				mnInfo.setMn_ice(rs.getString("mn_ice"));
				mnInfo.setMn_java(rs.getString("mn_java"));
				mnInfo.setMn_java_frap(rs.getInt("mn_java_frap"));
				mnInfo.setMn_whip(rs.getString("mn_whip"));
				mnInfo.setMn_drz(rs.getString("mn_drz"));
				mnInfo.setMn_lid(rs.getString("mn_lid"));
				mnInfo.setMn_etc(rs.getString("mn_etc"));
				mnInfo.setMn_ntr(rs.getString("mn_ntr"));
				mnInfo.setMn_spec(rs.getString("mn_spec"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mnInfo;

	}

//	
//	
//
//	
//
//	
//
//
//
//
//
//
//

	// 장바구니 등록
	public int cartInsert(CartInfo vo) {
		int n = 0;
		String sql2 = "INSERT INTO CART VALUES(?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, vo.getMn_name());
//				psmt.setInt(2, vo.getMn_price());
			psmt.setInt(3, vo.getVol());
			psmt.setString(4, vo.getId());
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
	//
	//

//	// 상품 선택
//	public int selectItem(SBMenuInfo vo, String id) {
//		int n = 0;
////		String id = null;
////		String sql1 = "SELECT ID FROM MEMBERS WHERE SIGNINWHO = 1"; // 현재 로그인 세션 검증
//		String sql2 = "UPDATE MENU SET CART = ? WHERE CARD_NO = ? AND ID = ?";
//		try {
////			psmt = conn.prepareStatement(sql1);
////			rs = psmt.executeQuery();
////			if (rs.next()) {
////				id = rs.getString("id");
////			}
//			psmt = conn.prepareStatement(sql2);
//			psmt.setInt(1, v.getCard_in());
//			psmt.setString(2, v.getCard_no());
//			psmt.setString(3, id);
//			n = psmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return n;
//	}
//
//
//
//
//
//
//
//
//
//

	// 장바구니담기
//	public List<SBMenuInfo> viewCart() {
//		List<SBMenuInfo> viewCart = new ArrayList<SBMenuInfo>();
//		SBMenuInfo vo;
//		String sql = "CREATE OR REPLACE VIEW V_CART AS SELECT X.ID, M.MN_NAME, M.MN_PRICE FROM MENU M, MEMBERS X WHERE X.ID = ? AND M.MN_NAME = ?";
//		try {
//			psmt = conn.prepareStatement(sql);
//			rs = psmt.executeQuery();
//			int i = 1;
//			while (rs.next()) {
//				vo = new SBMenuInfo();
//				vo.setMenuorder(i++);
//				vo.setMn_name(rs.getString("mn_name"));
//				vo.setMn_price(rs.getInt("mn_price"));
//				vo.setCart(rs.getInt("mn_cart"));
//				viewCart.add(vo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return viewCart;
//
//	}

//		
//	
//		
//	
//		
//	
//		

}
