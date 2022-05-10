package com.jyk;

import java.util.Scanner;

import com.jyk.starbucks.service.CardManager;
import com.jyk.starbucks.service.MembershipManager;

public class MainMenu {
	static Scanner scn = new Scanner(System.in);
	static MembershipManager e = new MembershipManager();
	static CardManager c = new CardManager();
	static int exc;

	public static void mainMenu() {
		while (true) {
			System.out.println("=======================");
			System.out.println("===== 1.멤버십 가입 =====");
			System.out.println("===== 2.로  그  인 =====");
			System.out.println("===== 3.앱  종  료 =====");
			System.out.println("=======================");
			System.out.print("번호를 눌러주세요 >> ");
			try {
				exc = Integer.parseInt(scn.nextLine());
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요");
				continue;
			}
			switch (exc) {
			case 1:
				e.signUp();
				break;
			case 2:
				e.SignIn();
				subMenu();
				break;
			case 3:
				System.out.println("앱이 종료되었습니다");
				System.exit(0); // 메뉴 이리저리 옮겨다니다 보면 어느새 무한 루프에 빠져있다. 아예 앱을 꺼버리자
				break;
			}
			break;
		}

	}

	public static void subMenu() {
		System.out.println("=======================");
		System.out.println("===== 1.주문매장찾기 =====");
		System.out.println("===== 2.스타벅스카드 =====");
		System.out.println("===== 3.개인정보조회 =====");
		System.out.println("===== 4.로 그 아 웃 =====");
		System.out.println("=======================");
		System.out.print("번호를 눌러주세요 >> ");
		int num = Integer.parseInt(scn.nextLine());
		switch (num) {
		case 1:
			break;
		case 2:
			cardMenu();
			break;
		case 3:
			e.memberView();
			break;
		case 4:
			e.signOut();
			mainMenu();
			break;
		}
	}

	public static void cardMenu() {
		System.out.println("=======================");
		System.out.println("===== 1.카 드 구 매 =====");
		System.out.println("===== 2.카 드 충 전 =====");
		System.out.println("===== 3.카 드 조 회 =====");
		System.out.println("===== 4.홈  메  뉴 =====");
		System.out.println("=======================");
		System.out.print("번호를 눌러주세요 >> ");
		int num = Integer.parseInt(scn.nextLine());
		switch (num) {
		case 1:
			c.cardInsert();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			subMenu();
			break;
		}
	}

}
