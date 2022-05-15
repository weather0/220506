package com.jyk.starbucks.vo;

import com.jyk.starbucks.app.MainMenu;

import lombok.Data;

@Data
public class SBMenuInfo {
	int mnorder; // 고유필드. 넘버링
	int vol; // 고유필드. 수량
	int mn_no; // 상번
	String mn_name; // 품명
	int mn_price; // 가격
	String mn_type1; // 음료/푸드
	String mn_type2; // 카테고리
	String mn_size0; // 숏
	String mn_size1; // 톨
	String mn_size2; // 그란데
	String mn_size3; // 벤티
	int mn_shot; // 샷추가
	String mn_esso; // 에스프레소 옵션: 블론드/디카페인/½디카페인
	int mn_frapro; // 프라푸치노 로스트
	String mn_syr_frap; // 프라푸치노 시럽
	int mn_syr_dol; // 돌체 시럽
	int mn_syr_van; // 바닐라 시럽
	int mn_syr_haz; // 헤이즐넛 시럽
	int mn_syr_crm; // 카라멜 시럽
	String mn_milk; // 우유 종류: 일반/저지방/무지방/두유/오트(귀리)
	String mn_milk_hot; // 음료 온도: 덜 뜨겁게/많이 뜨겁게
	String mn_milk_foam; // 우유 거품: 없이/적게/많이
	String mn_milk_vol; // 우유양(아이스음료는 100ml내 가감): 적게
	String mn_ice; // 얼음: 없이/적게/보통/많이
	String mn_java; // 자바칩: 자바칩/자바칩&토핑(반반)/통 자바칩 토핑
	int mn_java_frap; // 프라푸치노 자바칩
	String mn_whip; // 휘핑 크림: 에스프레소 휘핑/일반 휘핑 (없이/적게/보통/많이)
	String mn_drz; // 카라멜드리즐(적게/보통/많이) / 초콜릿드리즐(적게/보통/많이)
	String mn_lid; // 돔 리드로 변경
	String mn_etc; // 기타
	String mn_ntr; // 영양성분
	String mn_spec; // 설명


	public String toString() {
		
			System.out.print("│  " + mnorder + ". " + mn_name);
			MainMenu.korPrint(35, mn_name);
			System.out.printf("%6d",mn_price);
			System.out.println("  │");

		return null;
	}
	
	public String mnInfoString() {
		
		System.out.println("  " + mn_name);
		System.out.println();
		System.out.println(mn_spec);
		System.out.println();
		System.out.println("  "+mn_price+"원 (톨사이즈)");
		System.out.println();
		System.out.println("  ----------------------------------");
		System.out.println("  "+mn_size0+"   "+mn_size1+"   "+mn_size2+"   "+mn_size3);
		System.out.println();
		System.out.println("  q.매장컵   w.개인컵   e.일회용컵");
		System.out.println();

	return null;
}
	
	

}
