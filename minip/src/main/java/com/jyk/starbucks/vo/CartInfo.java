package com.jyk.starbucks.vo;

import lombok.Data;

@Data
public class CartInfo {
	int cartorder; // 고유필드. 넘버링
	String id; // 고유필드. 아이디
	int mn_no; // 상번
	String mn_name; // 품명
	String mysize; // 고유필드. 사이즈선택
	int vol; // 고유필드. 수량
	String cup; // 고유필드. 컵선택
	
	int mn_shot; // 샷추가
	String mn_esso; // 에스프레소 옵션: 블론드/디카페인/½디카페인
	int mn_syr_van; // 바닐라 시럽
	int mn_syr_haz; // 헤이즐넛 시럽
	int mn_syr_crm; // 카라멜 시럽
	String mn_milk; // 우유 종류: 일반/저지방/무지방/두유/오트(귀리)
	String mn_milk_hot; // 음료 온도: 덜 뜨겁게/많이 뜨겁게
	String mn_milk_foam; // 우유 거품: 없이/적게/많이
	String mn_whip; // 휘핑 크림: 에스프레소 휘핑/일반 휘핑 (없이/적게/보통/많이)
	String mn_drz; // 카라멜드리즐(적게/보통/많이) / 초콜릿드리즐(적게/보통/많이)
	
	int myprice; // 고유필드. 최종가격
	

}
