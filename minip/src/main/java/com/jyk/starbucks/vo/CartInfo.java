package com.jyk.starbucks.vo;

import com.jyk.starbucks.app.MainMenu;

import lombok.Data;

@Data
public class CartInfo {
	int cartorder; // 고유필드. 넘버링
	int cartsum = 0; // 고유필드

	String id; // 아이디
//	int mn_no; // 상번
	String mn_name; // 품명
	int mn_price; // 기본가격
	String mysize; // 사이즈선택
	String cup; // 컵선택
	int vol; // 수량

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

	int psize0;
	int psize2;
	int psize3;
	int pshot;
	int pesso;
	int pvan;
	int phaz;
	int pcrm;
	int pwhip;
	int pdrz;

	public String toString() {
		if (mn_name != null) {
			System.out.print(" " + cartorder + ". " + mn_name);
			MainMenu.korPrint(40, mn_name);
			System.out.print("│");
			System.out.printf("%6d", mn_price);
			System.out.println(" │ 수량 " + vol);
		}

		if (mysize != null) {
			System.out.print("   사이즈 " + mysize);
			MainMenu.korPrint(34, mysize);
			System.out.print("│");
			System.out.printf("%6d", (psize0 + psize2 + psize3));
			System.out.println(" │ " + cup);
		}

		if (mn_esso != null) {
			System.out.print("   " + mn_esso);
			MainMenu.korPrint(41, mn_esso);
			System.out.print("│");
			System.out.printf("%6d", pesso);
			System.out.println(" │");
		}

		if (mn_shot != 0) {
			System.out.print("   샷                 양");
			System.out.printf("%3d", mn_shot);
			System.out.print("                 │");
			System.out.printf("%6d", pshot);
			System.out.println(" │");
		}

		if (mn_syr_van != 0) {
			System.out.print("   바닐라 시럽        양");
			System.out.printf("%3d", mn_syr_van);
			System.out.print("                 │");
			System.out.printf("%6d", pvan);
			System.out.println(" │");
		}

		if (mn_syr_haz != 0) {
			System.out.print("   헤이즐넛 시럽      양");
			System.out.printf("%3d", mn_syr_haz);
			System.out.print("                 │");
			System.out.printf("%6d", phaz);
			System.out.println(" │");
		}

		if (mn_syr_crm != 0) {
			System.out.print("   카라멜 시럽        양");
			System.out.printf("%3d", mn_syr_crm);
			System.out.print("                 │");
			System.out.printf("%6d", pcrm);
			System.out.println(" │");
		}

		if (mn_whip != null) {
			System.out.print("   " + mn_whip);
			MainMenu.korPrint(41, mn_whip);
			System.out.print("│");
			System.out.printf("%6d", pwhip);
			System.out.println(" │");
		}

		if (mn_drz != null) {
			System.out.print("   " + mn_drz);
			MainMenu.korPrint(41, mn_drz);
			System.out.print("│");
			System.out.printf("%6d", pdrz);
			System.out.println(" │");
		}
		System.out.println(" --------------------------------------------------------------");


		return null;
	}

}
