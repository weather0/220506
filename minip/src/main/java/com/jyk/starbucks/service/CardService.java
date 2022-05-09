package com.jyk.starbucks.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jyk.starbucks.dao.DataSource;
import com.jyk.starbucks.vo.CardFace;
import com.jyk.starbucks.vo.CardInfo;

public class CardService {

	private DataSource dataSource = DataSource.getInstance();
	private Connection conn = dataSource.getConnection();
	private PreparedStatement psmt;
	private ResultSet rs;
	CardInfo card = new CardInfo();

	// 카드 넘버 생성
	public static String genCardNo() {
		int[] arr = new int[16];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 9) + 1;
		}
		String cn = Arrays.toString(arr).replaceAll("\\[|\\]|,|\\s", "");
		return cn.substring(0,4)+"-"+cn.substring(4,8)+"-"+cn.substring(8,12)+"-"+cn.substring(12,16);
	}

	
	// 카드 리스트
	public List<CardFace> cardList() {
		List<CardFace> list = new ArrayList<CardFace>();
		CardFace vo;
		String sql = "SELECT CARD_NAME FROM CARDSFACE WHERE CARD_AVL = 'Y' ORDER BY CARD_NAME";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 1;
			while(rs.next()) {
				vo = new CardFace();
				vo.setCard_order(i++);
				vo.setCard_name(rs.getString("card_name"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	// 카드 등록
	public int cardInsert(CardInfo vo) {
		int n = 0;
		String sql = "INSERT INTO CARDS VALUES(?,?,?,DEFAULT,?,0,DEFAULT,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getCard_no());
			psmt.setString(2, vo.getCard_name());
			psmt.setDate(3, (Date) vo.getCard_regdate());
			psmt.setInt(4, vo.getCard_in());
			psmt.setString(5, vo.getId());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;

	}

}
