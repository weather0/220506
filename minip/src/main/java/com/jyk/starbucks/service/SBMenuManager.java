package com.jyk.starbucks.service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import com.jyk.starbucks.app.MainMenu;
import com.jyk.starbucks.vo.CardFace;
import com.jyk.starbucks.vo.CardInfo;
import com.jyk.starbucks.vo.SBMenuInfo;

public class SBMenuManager {

	// DB관련 전역 변수
	SBMenuService dao = new SBMenuService();

	// 스캐너 관련 전역 변수
	Scanner scn = new Scanner(System.in);
	String pick = null;
	int intpick = 0; // int pick;만 해도 되지만, 그래도 웬만하면 초기화를 해주자

//	// 스택 관련 전역 변수
//	Stack<Integer> st = new Stack();
//	int stn = 0;

	// 메뉴주문
	public void sbmenuOrder() {
//		st.push(0); // 최초 스택에 0을 넣음: st = {0}
//		loop: while (true) { // 대while에는 break가 없어야 한다!(무한루프)
//
//			switch (stn) {
//			case 0:
		// 스택0 [선택지]
		System.out.println();
		System.out.println("────────────[ 주 문 메 뉴 ]────────────");
		System.out.println("┌───────┐┌───────┐┌───────┐┌───────┐");
		System.out.println("│ z.뒤로 ││ m.메인 ││ 1.음료 ││ 2.푸드 │");
		System.out.println("└───────┘└───────┘└───────┘└───────┘");

		String arr[] = { "z", "m", "1", "2" };

		while (true) { // 스캐너 입력값 유효성 검증 로직
			pick = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pick.equals("z")) {
				MainMenu.cardMenu(); // <매장기능 만들고 그쪽으로 대체할 것>
			} else if (pick.equals("m")) {
				MainMenu.subMenu();
			} else {
				String drinkType[] = { "에스프레소", "콜드브루", "프라푸치노", "블렌디드", "피지오", "티바나", "브루드커피", "기타", "병음료" };
				String foodType[] = { "과일&요거트", "따뜻한 푸드", "브레드", "샌드위치", "스낵&미니디저트", "아이스크림", "케이크" };
				String types[] = (pick.equals("1")) ? drinkType : foodType;
				menuType(types);
//						st.push(1); // 다음스택에 1을 넣음: st = {0, 1}
//						break;
			}

		}

//				st.clear();
//				stn = 0;
//				continue loop; // 여까지 내려오는 경우는 딱 하나: 임의금액으로 카드구매한완료한 경우 뿐임
//			} // 대switch문}
//				// ..에는 break가 없어야 한다!
//		} // 대while문↗}

	}

//	
//	
//	
//	
//	
//	
//

	public void menuType(String types[]) {

		String arr[] = new String[types.length + 2]; // 선택지배열 생성(z,m자리 추가)
		arr[0] = "z";
		arr[1] = "m";
		for (int i = 2; i < types.length + 2; i++) {
			arr[i] = i - 1 + ""; // 형변환 String ← int+"" // arr[2] = 1이어야 하므로
		}

		System.out.println("───────────────────");
		for (int i = 0; i < types.length; i++) {
			System.out.println(i + 1 + ". " + types[i]);
		}
		System.out.println("───────────────────────────────");
		System.out.println("┌───────┐┌───────┐┌───────────────┐");
		System.out.println("│ z.뒤로 ││ m.메인 ││ 카테고리 선택 >> │");
		System.out.println("└───────┘└───────┘└───────────────┘");

		while (true) { // 스캐너 입력값 유효성 검증 로직
			pick = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pick.equals("z")) { // 상황2: '뒤로'
				sbmenuOrder();
			} else if (pick.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else { // 상황4: 숫자선택지 정상 입력
				intpick = Integer.parseInt(pick);
				sirenOrder(types);
				break;
			}
		}

	}

//	
//	
//	
//	
//	
//	
//
	
	
	public void sirenOrder(String types[]) {
		System.out.println("────────────[ 사이렌 오더 ]────────────");
		String type = types[intpick - 1];
		System.out.println(type);
		System.out.println("-------------------------------------");
		List<SBMenuInfo> menuDisplay = dao.menuDisplay(type);
		menuDisplay.toString();

		String arr[] = new String[menuDisplay.size() + 2]; // 선택지배열 생성(z,m자리 추가)
		arr[0] = "z";
		arr[1] = "m";
		for (int i = 2; i < menuDisplay.size() + 2; i++) {
			arr[i] = i - 1 + ""; // 형변환 String ← int+"" // arr[2] = 1이어야 하므로
		}

		System.out.println("-------------------------------------");
		System.out.println("┌───────┐┌───────┐┌────────────┐");
		System.out.println("│ z.뒤로 ││ m.메인 ││ 상품 선택 >> │");
		System.out.println("└───────┘└───────┘└────────────┘");

		while (true) { // 스캐너 입력값 유효성 검증 로직
			pick = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pick.equals("z")) { // 상황2: '뒤로'
				menuType(types);
			} else if (pick.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else { // 상황4: 숫자선택지 정상 입력
				
				
				break;
			}
		}

	}
	
	public void viewCart() {
		
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
//

}
