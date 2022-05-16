package com.jyk.starbucks.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import org.w3c.dom.css.ViewCSS;

import com.jyk.starbucks.app.MainMenu;
import com.jyk.starbucks.vo.CardFace;
import com.jyk.starbucks.vo.CardInfo;
import com.jyk.starbucks.vo.CartInfo;
import com.jyk.starbucks.vo.SBMenuInfo;

import oracle.net.aso.c;

public class SBMenuManager {

	// DB관련 전역 변수
	SBMenuService dao = new SBMenuService();
	String loginInfo; // 로그인세션
	List<SBMenuInfo> menuDisplay; // 상품리스트
	SBMenuInfo mnInfo = new SBMenuInfo(); // 단일상품정보(실시간 음료커스텀용 변수)
	List<CartInfo> cartlist = new ArrayList<CartInfo>(); // 카트내 상품 리스트
	CartInfo cart = new CartInfo(); // 단일 카트

	// dao -> mnInfo -> myshot, mysize, ... -> cart

	// 로그인세션 유지 관련
	public SBMenuManager(String loginInfo) {
		this.loginInfo = loginInfo;
	}

	// 스캐너 관련 전역 변수
	// (주의! 스택구조를 위해 각 변수는 자기 차례의 메소드 밖에서 새롭게 정의되어서는 안됨!!)
	Scanner scn = new Scanner(System.in);
	String pick1 = null;
	String pick2 = null;
	String pick3 = null;
	String pick4 = null;
	String pick5 = null;
	String pickV = null;
	String pickP = null;
	String pickC = null;
	int intpick1 = 0;
	int intpick2 = 0;
	int intpick3 = 0;
	int intpick4 = 0;
	int intpick5 = 0;
	int intpick6 = 0;
	int intpickP = 0;
	int intpickC = 0;
	String mnp = null; // 선택상품이름(기본키)

	// 나의 메뉴 관련 전역 변수
	int myshot = 0;
	String myesso = null;
	int myvanilla = 0;
	int myhazelnut = 0;
	int mycaramel = 0;
	String mymilk = null;
	String mymilkhot = null;
	String mymilkfoam = null;
	String mywhip = null;
	String mydrizzle = null;

//	String mycart = null; // 장바구니 번호

	// 가격 관련 변수
	int psize0 = 0; // 숏
	int psize2 = 0; // 그란데
	int psize3 = 0; // 벤티
	int pshot = 0;
	int pesso = 0;
	int pvan = 0;
	int phaz = 0;
	int pcrm = 0;
	int pwhip = 0;
	int pdrz = 0;

	// 스택 관련 전역 변수
	String drinkType[] = { "에스프레소", "콜드브루", "프라푸치노", "블렌디드", "피지오", "티바나", "브루드커피", "기타", "병음료" };
	String foodType[] = { "과일&요거트", "따뜻한 푸드", "브레드", "샌드위치", "스낵&미니디저트", "아이스크림", "케이크" };
	String types[];
//
//
//	
//	
//	
//	

//	
	// 메뉴주문
	public void sbmenuOrder1() {

		// 음료/푸드 선택
		System.out.println();
		System.out.println("┌─────────────────────────────┐");
		System.out.println("│                             │");
		System.out.println("│          O r d e r          │");
		System.out.println("│ ─────────────────────────── │");
		System.out.println("│          1. 음 료           │");
		System.out.println("│          2. 푸 드           │");
		System.out.println("│                             │");
		System.out.println("│              z.뒤로  m.메인 │");
		System.out.println("└─────────────────────────────┘");

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
				sbmenuOrder2();
			}

		}
	}

//	
//	
//	
//	
//	
//	

	// 카테고리 선택
	public void sbmenuOrder2() {
		System.out.println();
		System.out.println("┌───────────────────────────────┐");
		System.out.println("│                               │");
		System.out.println("│           O r d e r           │");
		System.out.println("│ ───────────────────────────── │");
		System.out.println("│                               │");
		for (int i = 0; i < types.length; i++) {
			System.out.print("│      " + (i + 1) + ". " + types[i]);
			MainMenu.korPrint(20, types[i]);
			System.out.println("  │");
		}
		System.out.println("│                               │");
		System.out.println("│                               │");
		System.out.println("│                z.뒤로  m.메인 │");
		System.out.println("└───────────────────────────────┘");

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
				intpick2 = Integer.parseInt(pick2);
				sbmenuOrder3();
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
	// 상품 선택(목록)
	public void sbmenuOrder3() {
		System.out.println();
		System.out.println("┌────────────────────────────────────────────────┐");
		System.out.println("│                                                │");
		System.out.println("│                     O r d e r                  │");
		System.out.println("│ ────────────────────────────────────────────── │");
		String type = types[intpick2 - 1];
		System.out.print("│  " + type);
		MainMenu.korPrint(35, type);
		System.out.println("           │");
		System.out.println("│  ---------------                               │");
		System.out.println("│                                                │");
		menuDisplay = dao.menuDisplay(type);
		menuDisplay.toString();
		System.out.println("│                                                │");
		System.out.println("│                                                │");
		System.out.println("│                                 z.뒤로  m.메인 │");
		System.out.println("└────────────────────────────────────────────────┘");

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
				sbmenuOrder2();
			} else if (pick3.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else { // 상황4: 숫자선택지 정상 입력

				intpick4 = Integer.parseInt(pick3);
				mnp = menuDisplay.get(intpick4 - 1).getMn_name(); // 선택상품이름(기본키) 전역변수mnp에 할당
				mnInfo = dao.menuInfo(mnp); // mnInfo(SBMenuInfo타입)는 실시간 음료 커스텀용 변수
				myshot = mnInfo.getMn_shot();
				myesso = mnInfo.getMn_esso();
				// 재주문하러 화면 돌아왔을때 이전에 세팅된 값 초기화
				myvanilla = 0;
				myhazelnut = 0;
				mycaramel = 0;
				mymilk = null;
				mymilkhot = null;
				mymilkfoam = null;
				mywhip = null;
				mydrizzle = null;
				psize0 = 0;
				psize2 = 0;
				psize3 = 0;
				pshot = 0;
				pesso = 0;
				pvan = 0;
				phaz = 0;
				pcrm = 0;
				pwhip = 0;
				pdrz = 0;
				sbmenuOrder4();
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
	// 주문화면
	public void sbmenuOrder4() {

		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");

		mnInfo.mnInfoString();

		System.out.println("  ┌──────────────┬────────────────┐");
		System.out.println("  │              │ c.장바구니담기 │");
		System.out.println("  │ p.퍼스널옵션 ├────────────────┤");
		System.out.println("  │              │ v.장바구니보기 │");
		System.out.println("  └──────────────┴────────────────┘");
		System.out.println("    사이즈와 컵을 모두 선택한 뒤 ");
		System.out.println("    장바구니에 담아주세요 >> ");
		System.out.println();
		System.out.println("                      z.뒤로  m.메인 ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = { "z", "m", "c", "0", "1", "2", "3", "q", "w", "e", "p", "v" };

		if (pick1.equals("2")) {
			while (true) {
				pick4 = scn.nextLine();
				boolean pickcheck = Arrays.asList(arr).contains(pick4); // 배열 내 특정값 찾기
				if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
					System.out.println("올바른 키를 입력해주세요 >> ");
					continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
				} else if (pick4.equals("z")) { // 상황2: '뒤로'
					sbmenuOrder3();
				} else if (pick4.equals("m")) { // 상황3: '메인'
					MainMenu.subMenu();
				} else if (pick4.equals("c")) {
					goCart();
				} else if (pick4.equals("v")) {
					viewCart();
				} else {
					System.out.println("푸드는 퍼스널옵션이 없습니다. 장바구니로 바로 진행해주세요 >> ");
					continue;
				}
			}

		} else {

			while (true) {
				pick4 = scn.nextLine();
				boolean pickcheck = Arrays.asList(arr).contains(pick4); // 배열 내 특정값 찾기
				if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
					System.out.println("올바른 키를 입력해주세요 >> ");
					continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
				} else if (pick4.equals("z")) { // 상황2: '뒤로'
					sbmenuOrder3();
				} else if (pick4.equals("m")) { // 상황3: '메인'
					MainMenu.subMenu();
				} else if (pick4.equals("v")) {
					viewCart();
				} else if (pick4.equals("0")) {
					cart.setMysize("숏"); // 넣고
					psize0 = -500;
					psize2 = 0;
					psize3 = 0;
					System.out.println("숏사이즈 선택 완료.");
					System.out.println("사이즈와 컵을 모두 선택하셨다면, ");
					System.out.println("장바구니에 담아주세요(c키) >> ");
					continue;
				} else if (pick4.equals("1")) {
					cart.setMysize("톨");
					System.out.println("톨사이즈 선택 완료.");
					System.out.println("사이즈와 컵을 모두 선택하셨다면, ");
					System.out.println("장바구니에 담아주세요(c키) >> ");
					continue;
				} else if (pick4.equals("2")) {
					cart.setMysize("그란데");
					psize0 = 0;
					psize2 = 500;
					psize3 = 0;
					System.out.println("그란데사이즈 선택 완료.");
					System.out.println("사이즈와 컵을 모두 선택하셨다면, ");
					System.out.println("장바구니에 담아주세요(c키) >> ");
					continue;
				} else if (pick4.equals("3")) {
					cart.setMysize("벤티");
					psize0 = 0;
					psize2 = 0;
					psize3 = 1000;
					System.out.println("벤티사이즈 선택 완료");
					System.out.println("사이즈와 컵을 모두 선택하셨다면, ");
					System.out.println("장바구니에 담아주세요(c키) >> ");
					continue;
				} else if (pick4.equals("q")) {
					cart.setCup("매장컵");
					System.out.println("매장컵 선택 완료.");
					System.out.println("사이즈와 컵을 모두 선택하셨다면, ");
					System.out.println("장바구니에 담아주세요(c키) >> ");
					continue;
				} else if (pick4.equals("w")) {
					cart.setCup("개인컵");
					System.out.println("개인컵 선택 완료.");
					System.out.println("사이즈와 컵을 모두 선택하셨다면, ");
					System.out.println("장바구니에 담아주세요(c키) >> ");
					continue;
				} else if (pick4.equals("e")) {
					cart.setCup("일회용컵");
					System.out.println("일회용컵 선택 완료.");
					System.out.println("사이즈와 컵을 모두 선택하셨다면, ");
					System.out.println("장바구니에 담아주세요(c키) >> ");
					continue;
				} else if (pick4.equals("p")) {
					if (cart.getMysize() != null && cart.getCup() != null) {
						sbmenuOrderP();
					} else {
						System.out.println("음료사이즈와 컵종류를 먼저 선택해주세요 >>");
						continue;
					}
				} else if (pick4.equals("c")) {
					if (cart.getMysize() != null && cart.getCup() != null) {
						goCart();
					} else {
						System.out.println("음료사이즈와 컵종류를 먼저 선택해주세요 >>");
						continue;
					}
				}
			}
		}
	}

//	
//	
//	
//	
//	
	// 나의선택보기
	public void sbmenuOrderV() {

		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("  " + mnp);
		System.out.println();
		System.out.println("  기본                         " + mnInfo.getMn_price() + "원");
		// 사이즈
		String mysize = cart.getMysize();
		if (mysize.equals("숏")) {
			System.out.println("  숏                          " + psize0 + "원");
		} else if (mysize.equals("톨")) {
			System.out.println("  톨                            기본");
		} else if (mysize.equals("그란데")) {
			System.out.println("  그란데                      +" + psize2 + "원");
		} else if (mysize.equals("벤티")) {
			System.out.println("  벤티                        +" + psize3 + "원");
		}

		// 에스프레소샷
		if (pshot >= 600) {
			System.out.println("  에스프레소 샷 " + myshot + "(추가" + (myshot - dao.menuInfo(mnp).getMn_shot()) + "*600)   +"
					+ pshot + "원");
		} else {
			System.out.println("  에스프레소 샷 " + myshot);
		}

		// 에쏘옵션
		if (pesso >= 300) {
			System.out.println("  " + myesso + "                    +" + pesso + "원");
		}
		// 바닐라시럽
		if (pvan >= 600) {
			System.out.println("  바닐라시럽 " + myvanilla + "                +" + pvan + "원");
		}
		// 헤이즐넛시럽
		if (phaz >= 600) {
			System.out.println("  헤이즐넛시럽 " + myhazelnut + "              +" + phaz + "원");
		}
		// 카라멜시럽
		if (pcrm >= 600) {
			System.out.println("  카라멜시럽 " + mycaramel + "                +" + pcrm + "원");
		}
		// 우유 종류
		if (mymilk != null && mymilk != " ") {
			System.out.println("  우유: " + mymilk);
		}
		// 우유 온도
		if (mymilkhot != null && mymilkhot != " ") {
			System.out.println("  우유 " + mymilkhot);
		}
		// 우유 거품
		if (mymilkfoam != null && mymilkfoam != " ") {
			System.out.println("  우유 거품 " + mymilkfoam);
		}
		// 휘핑
		if (pwhip >= 600) {
			System.out.println("  " + mywhip + "         +" + pwhip + "원");
		}
		// 드리즐
		if (pdrz >= 600) {
			System.out.println("  " + mydrizzle + "         +" + pdrz + "원");
		}

		// 총합계
		System.out.println("  ----------------------------------");
		System.out.println("  합계                         "
				+ (mnInfo.getMn_price() + psize0 + psize2 + psize3 + pshot + pesso + pvan + phaz + pcrm + pwhip + pdrz)
				+ "원");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("          ┌────────────────┐          ");
		System.out.println("          │ c.장바구니담기 │          ");
		System.out.println("          └────────────────┘          ");
		System.out.println("                      z.뒤로  m.메인 ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = { "z", "m", "c" };
		while (true) {
			pick4 = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick4); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pick4.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else if (pick4.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else if (pick4.equals("c")) {
				goCart();
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
//	

	// 수량선택->장바구니
	public void goCart() {
		System.out.println();
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│                              │");
		System.out.println("│           O r d e r          │");
		System.out.println("│ ──────────────────────────── │");
		System.out.println("│  상품수량을 선택해주세요 >>  │");
		System.out.println("│  (최대 20개)                 │");
		System.out.println("│                              │");
		System.out.println("│               z.뒤로  m.메인 │");
		System.out.println("└──────────────────────────────┘");
		System.out.println();
		String arr[] = new String[22]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		arr[1] = "m";
		for (int i = 2; i < 22; i++) {
			arr[i] = i - 1 + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickC = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickC); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickC.equals("z")) { // 상황2: '뒤로'
				sbmenuOrder4();
			} else if (pickC.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else {
				intpickC = Integer.parseInt(pickC);
				cart.setVol(intpickC);
				System.out.println("선택이 완료 되었습니다. 장바구니로 이동합니다");
				System.out.println();
				// cart 요소 나머지 손에 담기
				cart.setId(loginInfo);
				cart.setMn_name(mnp);
				cart.setMn_price(mnInfo.getMn_price());
				cart.setPsize0(psize0);
				cart.setPsize2(psize2);
				cart.setPsize3(psize3);
				cart.setPshot(pshot);
				cart.setPesso(pesso);
				cart.setPvan(pvan);
				cart.setPhaz(phaz);
				cart.setPcrm(pcrm);
				cart.setPwhip(pwhip);
				cart.setPdrz(pdrz);
				// DB CART테이블로 옮기기(장바구니확정)
				int n = dao.cartInsert(cart);
				if (n != 0) {
				} else {
					System.out.println("입력 실패");
				}
				cart = null; // 손은 비워짐

				viewCart();
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
	//

	// 장바구니
	public void viewCart() {
		System.out.println();
		System.out.println("┌────────────────────────────────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("                            O r d e r              ");
		System.out.println("  ───────────────────────────────────────────────────────────────  ");
		dao.cartList(loginInfo).toString();
		System.out.println("                        ┌─────────────────┐");
		System.out.println("                        │ c.주문하러 가기 │");
		System.out.println("                        └─────────────────┘");
		System.out.println("                                        x.비우기  z.뒤로  m.메인 ");
		System.out.println("└────────────────────────────────────────────────────────────────┘");
//				System.out.println();
//				System.out.println("┌───────────────────────────────────┐");
//				System.out.println("│                                   │");
//				System.out.println("│  장바구니 번호를 선택해주세요 >>  │");
//				System.out.println("│                                   │");
//				System.out.println("│                    z.뒤로  m.메인 │");
//				System.out.println("└───────────────────────────────────┘");

		String arr[] = { "z", "m", "c", "x" }; // 선택지배열 생성(z자리 추가)
		while (true) {
			pickC = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickC); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickC.equals("z")) { // 상황2: '뒤로'
				sbmenuOrder4();
			} else if (pickC.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else if (pickC.equals("x")) {
				System.out.println();
				System.out.println("┌─────────────────────────────────┐");
				System.out.println("│                                 │");
				System.out.println("│  장바구니가 완전히 비워집니다.  │");
				System.out.println("│  아무키나 눌러주세요 >>         │");
				System.out.println("│                                 │");
				System.out.println("│                  z.뒤로  m.메인 │");
				System.out.println("└─────────────────────────────────┘");
				pickC = scn.nextLine();
				if (pickC.equals("z")) {
					viewCart();
				} else if (pickC.equals("m")) {
					MainMenu.subMenu();
				} else {
					int n = dao.cartDelete(loginInfo);
					if (n != 0) {
						System.out.println("장바구니를 비웠습니다.");
						viewCart();
					} else {
						System.out.println("실패");
					}
				}

			} else {
				
				// 주문메서드로 이동

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
//	

	// 퍼스널옵션
	public void sbmenuOrderP() {

		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("  " + mnp);
		System.out.println();
		System.out.println("   1. 에스프레소 샷               >");
		System.out.println("   2. 에스프레소 옵션             >");
		System.out.println("  ----------------------------------");
		System.out.println("   3. 바닐라 시럽                 >");
		System.out.println("   4. 헤이즐넛 시럽               >");
		System.out.println("   5. 카라멜 시럽                 >");
		System.out.println("  ----------------------------------");
		System.out.println("   6. 우유 종류                   >");
		System.out.println("   7. 우유 온도                   >");
		System.out.println("   8. 우유 거품                   >");
		System.out.println("  ----------------------------------");
		System.out.println("   9. 휘핑 크림                   >");
		System.out.println("  ----------------------------------");
		System.out.println("  10. 드리즐                      >");
		System.out.println();
		System.out.println("  ────────────────────────────────── ");
		System.out.println("          ┌────────────────┐          ");
		System.out.println("          │ v.나의선택보기 │          ");
		System.out.println("          └────────────────┘          ");
		System.out.println("                      z.뒤로  m.메인 ");
		System.out.println("└────────────────────────────────────┘");

		// 스캐너 입력값 유효성 검증 로직
		String arr[] = new String[13]; // 선택지배열 생성(z,m자리 추가)
		arr[0] = "z";
		arr[1] = "m";
		arr[2] = "v";
		for (int i = 3; i < 13; i++) {
			arr[i] = i - 2 + ""; // 형변환 String ← int+"" // arr[3] = 1이어야 하므로
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrder4();
			} else if (pickP.equals("m")) { // 상황3: '메인'
				MainMenu.subMenu();
			} else if (pickP.equals("v")) {
				sbmenuOrderV();
			} else if (pickP.equals("1")) {
				shot();
			} else if (pickP.equals("2")) {
				esso();
			} else if (pickP.equals("3")) {
				vanilla();
			} else if (pickP.equals("4")) {
				hazelnut();
			} else if (pickP.equals("5")) {
				caramel();
			} else if (pickP.equals("6")) {
				milk();
			} else if (pickP.equals("7")) {
				milkhot();
			} else if (pickP.equals("8")) {
				milkfoam();
			} else if (pickP.equals("9")) {
				whip();
			} else if (pickP.equals("10")) {
				drizzle();

			}
		}

	}

//	
//	
//	
//	
//	
//	
	// 1.샷
	public void shot() {
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("  에스프레소 샷        기본 " + mnInfo.getMn_shot());
		System.out.println("                       현재 " + myshot);
		System.out.println();
		System.out.println("        ┌────────────────────┐        ");
		System.out.println("        │ 수량입력(1 ~ 9) >> │        ");
		System.out.println("        └────────────────────┘        ");
		System.out.println("                              z.뒤로  ");
		System.out.println("└────────────────────────────────────┘");
		String arr[] = new String[10]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		for (int i = 1; i < 10; i++) {
			arr[i] = i + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else {
				intpickP = Integer.parseInt(pickP);
				myshot = intpickP; // 화면 표시용 변수에 할당
				cart.setMn_shot(myshot); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();

				if (myshot > dao.menuInfo(mnp).getMn_shot()) { // 샷추가시에만
					pshot = (myshot - dao.menuInfo(mnp).getMn_shot()) * 600; // 추가금
				} else {
					pshot = 0;
				}
			}
		}

	}

//	
//	
//	
//	
//	
//	
	// 2.에쏘옵션
	public void esso() {
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("            에스프레소 옵션          ");
		System.out.println();
		System.out.println("      ┌──────────┐┌────────────┐       ");
		System.out.println("      │ 1.블론드 ││ 2.디카페인 │      ");
		System.out.println("      └──────────┘└────────────┘       ");
		System.out.println("                    x.초기화  z.뒤로  ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = new String[4]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		arr[3] = "x";
		for (int i = 1; i < 3; i++) {
			arr[i] = i + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else if (pickP.equals("x")) { // 초기화
				cart.setMn_esso(null);
				myesso = null;
				System.out.println("해당 옵션 초기화가 완료되었습니다");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				pesso = 0;
			} else if (pickP.equals("1")) {
				myesso = "블론드"; // 화면 표시용 변수에 할당
				cart.setMn_esso(myesso); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pesso = 600;
			} else if (pickP.equals("2")) {
				myesso = "디카페인"; // 화면 표시용 변수에 할당
				cart.setMn_esso(myesso); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pesso = 300;
			}
		}
	}

//	
//	
//	
//	
//	
//	
	// 3.바닐라시럽
	public void vanilla() {
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("              바닐라 시럽            ");
		System.out.println();
		System.out.println("        ┌────────────────────┐        ");
		System.out.println("        │ 수량입력(1 ~ 9) >> │        ");
		System.out.println("        └────────────────────┘        ");
		System.out.println("                    x.초기화  z.뒤로  ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = new String[11]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		arr[10] = "x";
		for (int i = 1; i < 10; i++) {
			arr[i] = i + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else if (pickP.equals("x")) { // 초기화
				myvanilla = 0;
				cart.setMn_syr_van(0);
				System.out.println("해당 옵션 초기화가 완료되었습니다");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				pvan = 0;
			} else {
				intpickP = Integer.parseInt(pickP);
				myvanilla = intpickP; // 화면 표시용 변수에 할당
				cart.setMn_syr_van(myvanilla); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pvan = 600;
			}
		}
	}

//	
//	
//	
//	
//	
//	
	// 4.헤이즐넛시럽
	public void hazelnut() {
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("              헤이즐넛 시럽          ");
		System.out.println();
		System.out.println("        ┌────────────────────┐        ");
		System.out.println("        │ 수량입력(1 ~ 9) >> │        ");
		System.out.println("        └────────────────────┘        ");
		System.out.println("                    x.초기화  z.뒤로  ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = new String[11]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		arr[10] = "x";
		for (int i = 1; i < 10; i++) {
			arr[i] = i + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else if (pickP.equals("x")) { // 초기화
				myhazelnut = 0;
				cart.setMn_syr_haz(0);
				System.out.println("해당 옵션 초기화가 완료되었습니다");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				phaz = 0;
			} else {
				intpickP = Integer.parseInt(pickP);
				myhazelnut = intpickP; // 화면 표시용 변수에 할당
				cart.setMn_syr_haz(myhazelnut); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				phaz = 600;
			}
		}
	}

//	
//	
//	
//	
//	
//	
	// 5.카라멜시럽
	public void caramel() {
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("              카라멜 시럽       ");
		System.out.println();
		System.out.println("        ┌────────────────────┐        ");
		System.out.println("        │ 수량입력(1 ~ 9) >> │        ");
		System.out.println("        └────────────────────┘        ");
		System.out.println("                    x.초기화  z.뒤로  ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = new String[11]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		arr[10] = "x";
		for (int i = 1; i < 10; i++) {
			arr[i] = i + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else if (pickP.equals("x")) { // 초기화
				mycaramel = 0;
				cart.setMn_syr_crm(0);
				System.out.println("해당 옵션 초기화가 완료되었습니다");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				pcrm = 0;
			} else {
				intpickP = Integer.parseInt(pickP);
				mycaramel = intpickP; // 화면 표시용 변수에 할당
				cart.setMn_syr_crm(mycaramel); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pcrm = 600;
			}
		}
	}

//	
//	
//	
//	
//	
//	
	// 6.우유종류
	public void milk() {
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("               우유 종류          ");
		System.out.println();
		System.out.println("              1. 일반             ");
		System.out.println("              2. 저지방             ");
		System.out.println("              3. 무지방             ");
		System.out.println("              4. 두유                ");
		System.out.println("              5. 오트(귀리)              ");
		System.out.println("                                      ");
		System.out.println("                    x.초기화  z.뒤로  ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = new String[7]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		arr[6] = "x";
		for (int i = 1; i < 6; i++) {
			arr[i] = i + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else if (pickP.equals("x")) { // 초기화
				mymilk = null;
				cart.setMn_milk(null);
				System.out.println("해당 옵션 초기화가 완료되었습니다");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
			} else if (pickP.equals("1")) {
				mymilk = "일반"; // 화면 표시용 변수에 할당
				cart.setMn_milk(mymilk); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
			} else if (pickP.equals("2")) {
				mymilk = "저지방"; // 화면 표시용 변수에 할당
				cart.setMn_milk(mymilk); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
			} else if (pickP.equals("3")) {
				mymilk = "무지방"; // 화면 표시용 변수에 할당
				cart.setMn_milk(mymilk); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
			} else if (pickP.equals("4")) {
				mymilk = "두유"; // 화면 표시용 변수에 할당
				cart.setMn_milk(mymilk); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
			} else if (pickP.equals("5")) {
				mymilk = "오트(귀리)"; // 화면 표시용 변수에 할당
				cart.setMn_milk(mymilk); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
			}
		}
	}

//	
//	
//	
//	
//	
//	
	// 7.우유온도
	public void milkhot() {
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("               우유 온도          ");
		System.out.println();
		System.out.println("             1. 덜 뜨겁게            ");
		System.out.println("             2. 많이 뜨겁게           ");
		System.out.println("                                      ");
		System.out.println("                    x.초기화  z.뒤로  ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = new String[4]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		arr[3] = "x";
		for (int i = 1; i < 3; i++) {
			arr[i] = i + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else if (pickP.equals("x")) { // 초기화
				mymilkhot = null;
				cart.setMn_milk_hot(null);
				System.out.println("해당 옵션 초기화가 완료되었습니다");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
			} else if (pickP.equals("1")) {
				mymilkhot = "덜 뜨겁게"; // 화면 표시용 변수에 할당
				cart.setMn_milk_hot(mymilkhot); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
			} else if (pickP.equals("2")) {
				mymilkhot = "많이 뜨겁게"; // 화면 표시용 변수에 할당
				cart.setMn_milk_hot(mymilkhot); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
			}
		}
	}

//	
//	
//	
//	
//	
//	
	// 8.우유거품
	public void milkfoam() {
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("               우유 거품          ");
		System.out.println();
		System.out.println("               1. 없이            ");
		System.out.println("               2. 적게             ");
		System.out.println("               3. 많이               ");
		System.out.println("                                      ");
		System.out.println("                    x.초기화  z.뒤로  ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = new String[5]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		arr[4] = "x";
		for (int i = 1; i < 4; i++) {
			arr[i] = i + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else if (pickP.equals("x")) { // 초기화
				mymilkfoam = null;
				cart.setMn_milk_foam(null);
				System.out.println("해당 옵션 초기화가 완료되었습니다");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
			} else if (pickP.equals("1")) {
				mymilkfoam = "없이"; // 화면 표시용 변수에 할당
				cart.setMn_milk_foam(mymilkfoam); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
			} else if (pickP.equals("2")) {
				mymilkfoam = "적게"; // 화면 표시용 변수에 할당
				cart.setMn_milk_foam(mymilkfoam); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
			} else if (pickP.equals("3")) {
				mymilkfoam = "많이"; // 화면 표시용 변수에 할당
				cart.setMn_milk_foam(mymilkfoam); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
			}
		}
	}

//	
//	
//	
//	
//	
//	
	// 9.휘핑크림
	public void whip() {
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("               휘핑 크림          ");
		System.out.println();
		System.out.println("        1. 에스프레소휘핑 적게      ");
		System.out.println("        2. 에스프레소휘핑 보통      ");
		System.out.println("        3. 에스프레소휘핑 많이      ");
		System.out.println("        4. 일반휘핑 적게            ");
		System.out.println("        5. 일반휘핑 보통            ");
		System.out.println("        6. 일반휘핑 많이            ");
		System.out.println("                                      ");
		System.out.println("                    x.초기화  z.뒤로  ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = new String[8]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		arr[7] = "x";
		for (int i = 1; i < 7; i++) {
			arr[i] = i + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else if (pickP.equals("x")) { // 초기화
				mywhip = null;
				cart.setMn_whip(null);
				System.out.println("해당 옵션 초기화가 완료되었습니다");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				pwhip = 0;
			} else if (pickP.equals("1")) {
				mywhip = "에스프레소휘핑 적게"; // 화면 표시용 변수에 할당
				cart.setMn_whip(mywhip); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pwhip = 600;
			} else if (pickP.equals("2")) {
				mywhip = "에스프레소휘핑 보통"; // 화면 표시용 변수에 할당
				cart.setMn_whip(mywhip); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pwhip = 600;
			} else if (pickP.equals("3")) {
				mywhip = "에스프레소휘핑 많이"; // 화면 표시용 변수에 할당
				cart.setMn_whip(mywhip); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pwhip = 600;
			} else if (pickP.equals("4")) {
				mywhip = "일반휘핑 적게"; // 화면 표시용 변수에 할당
				cart.setMn_whip(mywhip); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pwhip = 600;
			} else if (pickP.equals("5")) {
				mywhip = "일반휘핑 보통"; // 화면 표시용 변수에 할당
				cart.setMn_whip(mywhip); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pwhip = 600;
			} else if (pickP.equals("6")) {
				mywhip = "일반휘핑 많이"; // 화면 표시용 변수에 할당
				cart.setMn_whip(mywhip); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pwhip = 600;
			}
		}
	}

//	
//	
//	
//	
//	
//	
	// 10.드리즐
	public void drizzle() {
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("                                      ");
		System.out.println("               O r d e r              ");
		System.out.println("  ──────────────────────────────────  ");
		System.out.println("                드리즐          ");
		System.out.println();
		System.out.println("        1. 카라멜드리즐 적게      ");
		System.out.println("        2. 카라멜드리즐 보통      ");
		System.out.println("        3. 카라멜드리즐 많이      ");
		System.out.println("        4. 초콜릿드리즐 적게        ");
		System.out.println("        5. 초콜릿드리즐 보통        ");
		System.out.println("        6. 초콜릿드리즐 많이         ");
		System.out.println("                                      ");
		System.out.println("                    x.초기화  z.뒤로  ");
		System.out.println("└────────────────────────────────────┘");

		String arr[] = new String[8]; // 선택지배열 생성(z자리 추가)
		arr[0] = "z";
		arr[7] = "x";
		for (int i = 1; i < 7; i++) {
			arr[i] = i + ""; // 형변환 String ← int+""
		}
		while (true) {
			pickP = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pickP); // 배열 내 특정값 찾기
			if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
			} else if (pickP.equals("z")) { // 상황2: '뒤로'
				sbmenuOrderP();
			} else if (pickP.equals("x")) { // 초기화
				mydrizzle = null;
				cart.setMn_drz(null);
				System.out.println("해당 옵션 초기화가 완료되었습니다");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				pdrz = 0;
			} else if (pickP.equals("1")) {
				mydrizzle = "카라멜드리즐 적게"; // 화면 표시용 변수에 할당
				cart.setMn_drz(mydrizzle); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pdrz = 600;
			} else if (pickP.equals("2")) {
				mydrizzle = "카라멜드리즐 보통"; // 화면 표시용 변수에 할당
				cart.setMn_drz(mydrizzle); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pdrz = 600;
			} else if (pickP.equals("3")) {
				mydrizzle = "카라멜드리즐 많이"; // 화면 표시용 변수에 할당
				cart.setMn_drz(mydrizzle); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pdrz = 600;
			} else if (pickP.equals("4")) {
				mydrizzle = "초콜릿드리즐 적게"; // 화면 표시용 변수에 할당
				cart.setMn_drz(mydrizzle); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pdrz = 600;
			} else if (pickP.equals("5")) {
				mydrizzle = "초콜릿드리즐 보통"; // 화면 표시용 변수에 할당
				cart.setMn_drz(mydrizzle); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pdrz = 600;
			} else if (pickP.equals("6")) {
				mydrizzle = "초콜릿드리즐 많이"; // 화면 표시용 변수에 할당
				cart.setMn_drz(mydrizzle); // cart타입에도 담기
				System.out.println("선택이 완료 되었습니다.");
				System.out.println("선택 변경 입력 또는 z.뒤로가기를 눌러주세요 >>");
				System.out.println();
				pdrz = 600;
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
