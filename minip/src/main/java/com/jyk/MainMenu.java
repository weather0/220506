package com.jyk;

import java.util.Scanner;

import com.jyk.starbucks.service.MembershipManager;

public class MainMenu {
	static Scanner scn = new Scanner(System.in);
	static MembershipManager e = new MembershipManager();

	public static void mainMenu() {
//		boolean b = true;
//		do {
		while (true) {
			System.out.println("=======================");
			System.out.println("===== 1.멤버십 가입 =====");
			System.out.println("===== 2.로  그  인 =====");
			System.out.println("===== 3.앱  종  료 =====");
			System.out.println("=======================");
			System.out.print("번호를 눌러주세요 >> ");
			int num = Integer.parseInt(scn.nextLine());
			switch (num) {
			case 1:
				e.memberInsert();
				break;
			case 2:
				e.memberSignIn();
				subMenu();
				break;
			case 3:
				System.out.println("앱이 종료되었습니다");
				System.exit(0);
//				b = false;
				break;
			}
			break;
		}
//		} while(b);

	}

	public static void subMenu() {
		System.out.println("=======================");
		System.out.println("===== 1.주문매장찾기 =====");
		System.out.println("===== 2.개인정보조회 =====");
		System.out.println("===== 3.로 그 아 웃 =====");
		System.out.println("=======================");
		System.out.print("번호를 눌러주세요 >> ");
		int num = Integer.parseInt(scn.nextLine());
		switch (num) {
		case 1:
			break;
		case 2:
			e.memberView();
			break;
		case 3:
			mainMenu();
			break;
		}
	}
	
	
	
}
