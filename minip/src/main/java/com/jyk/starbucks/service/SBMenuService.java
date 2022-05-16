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
import com.jyk.starbucks.vo.MembershipInfo;
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
				mnInfo.setMn_syr_van(rs.getInt("mn_syr_van"));
				mnInfo.setMn_syr_haz(rs.getInt("mn_syr_haz"));
				mnInfo.setMn_syr_crm(rs.getInt("mn_syr_crm"));
				mnInfo.setMn_milk(rs.getString("mn_milk"));
				mnInfo.setMn_milk_hot(rs.getString("mn_milk_hot"));
				mnInfo.setMn_milk_foam(rs.getString("mn_milk_foam"));
				mnInfo.setMn_whip(rs.getString("mn_whip"));
				mnInfo.setMn_drz(rs.getString("mn_drz"));
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
		String sql = "INSERT INTO CART VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMn_name());
			psmt.setInt(2, vo.getMn_price());
			psmt.setString(3, vo.getMysize());
			psmt.setString(4, vo.getCup());
			psmt.setInt(5, vo.getVol());
			psmt.setInt(6, vo.getMn_shot());
			psmt.setString(7, vo.getMn_esso());
			psmt.setInt(8, vo.getMn_syr_van());
			psmt.setInt(9, vo.getMn_syr_haz());
			psmt.setInt(10, vo.getMn_syr_crm());
			psmt.setString(11, vo.getMn_milk());
			psmt.setString(12, vo.getMn_milk_hot());
			psmt.setString(13, vo.getMn_milk_foam());
			psmt.setString(14, vo.getMn_whip());
			psmt.setString(15, vo.getMn_drz());
			psmt.setString(16, vo.getId());
			psmt.setInt(17, vo.getPsize0());
			psmt.setInt(18, vo.getPsize2());
			psmt.setInt(19, vo.getPsize3());
			psmt.setInt(20, vo.getPshot());
			psmt.setInt(21, vo.getPesso());
			psmt.setInt(22, vo.getPvan());
			psmt.setInt(23, vo.getPhaz());
			psmt.setInt(24, vo.getPcrm());
			psmt.setInt(25, vo.getPwhip());
			psmt.setInt(26, vo.getPdrz());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	// 장바구니 조회
	public List<CartInfo> cartList(String id) { // CART테이블의 VO로서 CartInfo타입으로 ArrayList에 담음
		List<CartInfo> cartList = new ArrayList<CartInfo>();
		CartInfo vo;
		String sql = "SELECT * FROM CART WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			int i = 1;
			int cartsum = 0;
			while (rs.next()) {
				vo = new CartInfo();
				vo.setCartorder(i++);
				vo.setMn_name(rs.getString("mn_name"));
				vo.setMn_price(rs.getInt("mn_price"));
				vo.setMysize(rs.getString("mysize"));
				vo.setCup(rs.getString("cup"));
				vo.setVol(rs.getInt("vol"));
				vo.setMn_shot(rs.getInt("mn_shot"));
				vo.setMn_esso(rs.getString("mn_esso"));
				vo.setMn_syr_van(rs.getInt("mn_syr_van"));
				vo.setMn_syr_haz(rs.getInt("mn_syr_haz"));
				vo.setMn_syr_crm(rs.getInt("mn_syr_crm"));
				vo.setMn_milk(rs.getString("mn_milk"));
				vo.setMn_milk_hot(rs.getString("mn_milk_hot"));
				vo.setMn_milk_foam(rs.getString("mn_milk_foam"));
				vo.setMn_whip(rs.getString("mn_whip"));
				vo.setMn_drz(rs.getString("mn_drz"));
				vo.setPsize0(rs.getInt("psize0"));
				vo.setPsize2(rs.getInt("psize2"));
				vo.setPsize3(rs.getInt("psize3"));
				vo.setPshot(rs.getInt("pshot"));
				vo.setPesso(rs.getInt("pesso"));
				vo.setPvan(rs.getInt("pvan"));
				vo.setPhaz(rs.getInt("phaz"));
				vo.setPcrm(rs.getInt("pcrm"));
				vo.setPwhip(rs.getInt("pwhip"));
				vo.setPdrz(rs.getInt("pdrz"));
				cartsum += vo.getVol()*(vo.getMn_price() + vo.getPsize0() + vo.getPsize2() + vo.getPsize3() + vo.getPshot()
						+ vo.getPesso() + vo.getPvan() + vo.getPhaz() + vo.getPcrm() + vo.getPwhip() + vo.getPdrz());
				vo.setCartsum(cartsum);
				cartList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

//		
//	
//		
//	
//		
//	
//		
	// 장바구니 삭제

	public int cartDelete(String id) {
		int n = 0;
		try {
			String sql = "DELETE FROM CART WHERE ID = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
}
