package com.jyk.starbucks.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import com.jyk.starbucks.app.MainMenu;
import com.jyk.starbucks.vo.CardFace;
import com.jyk.starbucks.vo.CardInfo;
import com.jyk.starbucks.vo.CartInfo;
import com.jyk.starbucks.vo.SBMenuInfo;

public class SBMenuManager {

	// DB관련 전역 변수
	SBMenuService dao = new SBMenuService();
	CartInfo cart = new CartInfo();
	List<CartInfo> cartlist = new ArrayList<CartInfo>();
	List<SBMenuInfo> menuDisplay;
	String loginInfo;

	// 로그인세션 유지 관련
	public SBMenuManager(String loginInfo) {
		this.loginInfo = loginInfo;
	}

	// 스캐너 관련 전역 변수
	Scanner scn = new Scanner(System.in);
	String pick1 = null;
	String pick2 = null;
	String pick3 = null;
	String pick4 = null;
	String pick5 = null;
	int intpick = 0; // int pick;만 해도 되지만, 그래도 웬만하면 초기화를 해주자

//	// 스택 관련 전역 변수
//	Stack<Integer> st = new Stack();
//	int stn = 0;
	String drinkType[] = { "에스프레소", "콜드브루", "프라푸치노", "블렌디드", "피지오", "티바나", "브루드커피", "기타", "병음료" };
	String foodType[] = { "과일&요거트", "따뜻한 푸드", "브레드", "샌드위치", "스낵&미니디저트", "아이스크림", "케이크" };
	String types[];

	// 메뉴주문
	public void sbmenuOrder1() {
//		st.push(0); // 최초 스택에 0을 넣음: st = {0}
//		loop: while (true) { // 대while에는 break가 없어야 한다!(무한루프)
//
//			switch (stn) {
//			case 0:
		// 스택0 [선택지]
		System.out.println();
		System.out.println("────────────[ 주 문 메 뉴 ]────────────");
		System.out.println("┌────────┬────────┐ ┌────────┬────────┐");
		System.out.println("│ z.뒤로 │ m.메인 │ │ 1.음료 │ 2.푸드 │");
		System.out.println("└────────┴────────┘ └────────┴────────┘");
		

		String arr[] = { "z", "m", "1", "2" };

		while (true) { // 스캐너 입력값 유효성 검증 로직
			pick1 = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick1); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pick1.equals("z")) {
				MainMenu.cardMenu(); // <매장기능 만들고 그쪽으로 대체할 것>
			} else if (pick1.equals("m")) {
				MainMenu.subMenu();
			} else {
				types = (pick1.equals("1")) ? drinkType : foodType;
				sbmenuOrder2(types);
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

	public void sbmenuOrder2(String types[]) {
		System.out.println("───────────────────");
		for (int i = 0; i < types.length; i++) {
			System.out.println(i + 1 + ". " + types[i]);
		}
		System.out.println("───────────────────────────────");
		System.out.println("┌────────┬────────┐ ┌──────────────────┐");
		System.out.println("│ z.뒤로 │ m.메인 │ │ 카테고리 선택 >> │");
		System.out.println("└────────┴────────┘ └──────────────────┘");

		String arr[] = new String[types.length + 2]; // 선택지배열 생성(z,m자리 추가)
		arr[0] = "z";
		arr[1] = "m";
		for (int i = 2; i < types.length + 2; i++) {
			arr[i] = i - 1 + ""; // 형변환 String ← int+"" // arr[2] = 1이어야 하므로
		}
		while (true) { // 스캐너 입력값 유효성 검증 로직
			pick2 = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick2); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pick2.equals("z")) { // 상황2: '뒤로'
				sbmenuOrder1();
			} else if (pick2.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else { // 상황4: 숫자선택지 정상 입력
				intpick = Integer.parseInt(pick2);
				sbmenuOrder3(types);
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

	public void sbmenuOrder3(String types[]) {
		System.out.println("────────────[ 사이렌 오더 ]────────────");
		String type = types[intpick - 1];
		System.out.println(type);
		System.out.println("-------------------------------------");
		menuDisplay = dao.menuDisplay(type);
		menuDisplay.toString();

		System.out.println("-------------------------------------");
		System.out.println("┌────────┬────────┐ ┌──────────────┐");
		System.out.println("│ z.뒤로 │ m.메인 │ │ 상품 선택 >> │");
		System.out.println("└────────┴────────┘ └──────────────┘");

		// 스캐너 입력값 유효성 검증 로직
		String arr[] = new String[menuDisplay.size() + 2]; // 선택지배열 생성(z,m자리 추가)
		arr[0] = "z";
		arr[1] = "m";
		for (int i = 2; i < menuDisplay.size() + 2; i++) {
			arr[i] = i - 1 + ""; // 형변환 String ← int+"" // arr[2] = 1이어야 하므로
		}
		while (true) {
			pick3 = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick3); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pick3.equals("z")) { // 상황2: '뒤로'
				sbmenuOrder2(types);
			} else if (pick3.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else { // 상황4: 숫자선택지 정상 입력
				sbmenuOrder4(pick3);
				break;
			}
		}

	}

	public void sbmenuOrder4(String pick) {

		
		System.out.println("────────────[ 상 품 안 내 ]────────────");
		intpick = Integer.parseInt(pick);
		System.out.println(menuDisplay.get(intpick - 1).getMenu_name());
		// <개별상품 설명 추가>
		System.out.println("-----------------");
		// <기본사이즈정보 표시>
		System.out.println("기본사이즈 : "+menuDisplay.get(intpick - 1).getMenu_size1());
		// <컵상태표시>
			
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println("┌────────┬────────┐ ┌──────────────┬──────────┬──────────────┬────────────────┐");
		System.out.println("│ z.뒤로 │ m.메인 │ │ 1.사이즈변경 │ 2.컵선택 │ 3.퍼스널옵션 │ 4.장바구니담기 │");
		System.out.println("└────────┴────────┘ └──────────────┴──────────┴──────────────┴────────────────┘");
		
		String arr[] = new String[6]; // 선택지배열 생성(z,m자리 추가)
		arr[0] = "z";
		arr[1] = "m";
		for (int i = 2; i < arr.length; i++) {
			arr[i] = i - 1 + ""; // 형변환 String ← int+"" // arr[2] = 1이어야 하므로
		}
		while (true) {
			pick4 = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick4); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pick4.equals("z")) { // 상황2: '뒤로'
				sbmenuOrder3(types);
			} else if (pick4.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else if (pick4.equals("1")) {
				sbmenuOrder5();
			} else { // 상황4: 숫자선택지 정상 입력
				sbmenuOrder6();
				break;
			}
		}
	}
	
	
	public void sbmenuOrder5() {
		System.out.println("───────────────────────");
		System.out.println("1. "+menuDisplay.get(intpick - 1).getMenu_size1()+"(기본)");
		System.out.println("2. "+menuDisplay.get(intpick - 1).getMenu_size2()+"(+500원)");
		System.out.println("3. "+menuDisplay.get(intpick - 1).getMenu_size3()+"(+1000원)");
		System.out.println("──────────────────────────────────────────────────────────────");
		System.out.println("┌────────┬────────┐ ┌────────────────┐");
		System.out.println("│ z.뒤로 │ m.메인 │ │ 사이즈 선택 >> │");
		System.out.println("└────────┴────────┘ └────────────────┘");
		String arr[] = new String[5]; // 선택지배열 생성(z,m자리 추가)
		arr[0] = "z";
		arr[1] = "m";
		for (int i = 2; i < arr.length; i++) {
			arr[i] = i - 1 + ""; // 형변환 String ← int+"" // arr[2] = 1이어야 하므로
		}
		while (true) {
			pick5 = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick5); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pick5.equals("z")) { // 상황2: '뒤로'
				sbmenuOrder4(pick3);
			} else if (pick5.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else if (pick5.equals("1")) {
				sbmenuOrder5();
			} else { // 상황4: 숫자선택지 정상 입력
				sbmenuOrder6();
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
//	
//	
//	
////	// <장바구니 넣기>
//	intpick = Integer.parseInt(pick);
//	cart.setMenu_name(menuDisplay.get(intpick - 1).getMenu_name());
//	cart.setMenu_price(menuDisplay.get(intpick - 1).getMenu_price());
//	cart.setId(loginInfo);
//
//	cart.setVolume();
//
//	int m = dao.cartInsert(cart);
//	if (m != 0) {
//		System.out.println("장바구니 추가 완료되었습니다!!");
//	} else {
//		System.out.println("장바구니 추가 실패");
//	}

//	
//	
//

}
