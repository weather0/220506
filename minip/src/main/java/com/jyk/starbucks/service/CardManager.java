package com.jyk.starbucks.service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import com.jyk.starbucks.app.MainMenu;
import com.jyk.starbucks.vo.CardFace;
import com.jyk.starbucks.vo.CardInfo;

public class CardManager {
	// DB관련 전역 변수
	CardService dao = new CardService();
	CardInfo card = new CardInfo();
//	CardFace cf = new CardFace(); // 이거 왜 생성했었지? 
	List<CardFace> display = dao.cardDisplay(); // 우변: cardDisplay()메서드가 return한 display
	String loginInfo;
	
	// 로그인세션 유지 관련
	// 로그인성공한 id는 멤버관리자.로그인성공 → MainMenu → 카드관리자로 이동됨
	public CardManager(String loginInfo) {
		this.loginInfo = loginInfo;
	}
	
	// 스캐너 관련 전역 변수
	Scanner scn = new Scanner(System.in);
	String pick = null;
	int intpick = 0; // int pick;만 해도 되지만, 그래도 웬만하면 초기화를 해주자

	// 스택 관련 전역 변수
	Stack<Integer> st = new Stack();
	int stn = 0;

	// 카드등록
	public void cardInsert() {
		st.push(0); // 최초 스택에 0을 넣음: st = {0}

		loop: while (true) { // 대while에는 break가 없어야 한다!(무한루프)

			switch (stn) {
			case 0:
				// 스택0 [선택지]
				System.out.println();
				System.out.println("────────────[ 카드 구매 ]────────────");
				System.out.println("    새로운 스타벅스 카드를 만나보세요!");
				System.out.println("───────────────────────────────────");
				display.toString();
				System.out.println("───────────────────────────────────");
				System.out.println("┌───────┐┌───────┐┌───────────────┐");
				System.out.println("│ z.뒤로 ││ m.메인 ││ 카드번호 선택 >> │");
				System.out.println("└───────┘└───────┘└───────────────┘");

				String arr[] = new String[display.size() + 2]; // 선택지배열 생성(z,m자리 추가)
				arr[0] = "z"; // z,m 배치
				arr[1] = "m";
				for (int i = 2; i < display.size() + 2; i++) { // 나머지 자리 채운다
					arr[i] = i - 1 + ""; // 형변환 String ← int+"" // arr[2] = 1이어야 하므로
				}

				while (true) { // 스캐너 입력값 유효성 검증 로직
					pick = scn.nextLine();
					boolean pickcheck = Arrays.asList(arr).contains(pick); // 배열 내 특정값 찾기
					if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
						System.out.println("올바른 키를 입력해주세요 >> ");
						continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
					} else if (pick.equals("z")) { // 상황2: '뒤로'
						MainMenu.cardMenu();
					} else if (pick.equals("m")) { // 상황3: '메인'
						MainMenu.subMenu();
					} else { // 상황4: 숫자선택지 정상 입력
						st.push(1); // 다음스택에 1을 넣음: st = {0, 1}
						break;
					}
				}

			case 1: // 스택1
				// 이번 스택은 예외적으로 선택지가 없는 케이스(아무 키 허용)이므로, 배열과 while문을 통한 스캐너 입력값 검증과정이 필요없다.

				intpick = Integer.parseInt(pick); // 형변환 int ← String
				System.out.println("───────────────────────");
				System.out.println(display.get(intpick - 1).getCard_name());
				// cardDisplay()메서드의return값인display배열에서.내가고른순번것만get해라.그CardFace필드중에서도name만
				// display = {(order,name,avl),(order,name,avl),...,(order,name,avl)}

				// <카드설명메서드 추가하기>

				System.out.println("────────────────────────────────────────────────");
				System.out.println("┌───────┐┌───────┐┌─────────────────────────────┐");
				System.out.println("│ z.뒤로 ││ m.메인 ││ 구매하러 가기 >> (아무 키나 입력) │");
				System.out.println("└───────┘└───────┘└─────────────────────────────┘");

				String pick1 = scn.nextLine();
				if (pick1.equals("z")) {
					st.pop(); // 맨 윗칸 스택을 빼냄: st = {0})
					stn = st.lastElement(); // 현재 맨 윗칸 스택의 값을 stn에 할당: stn = 0
					continue loop; // 맨 위 while 시작점으로 돌아감 → stn에 의해 case 0이 호출됨
				} else if (pick1.equals("m")) {
					MainMenu.subMenu();
				} else {
					st.push(2); // st = {0, 1, 2}
				}
			case 2:
				// 스택2 [선택지]

				System.out.println("-----------------------------");
				System.out.println("얼마를 충전하시겠습니까?");
				System.out.println("(카드 당 최대 50만원까지 보유 가능합니다)");
				System.out.println("0. 5천원 \n1. 1만원\n2. 2만원\n3. 3만원\n5. 5만원\n6. 다른금액");
				System.out.println("-----------------------------");
				System.out.println("┌───────┐┌───────┐┌────────────────┐");
				System.out.println("│ z.뒤로 ││ m.메인 ││ 구매 번호 선택 >> │");
				System.out.println("└───────┘└───────┘└────────────────┘");

				String arr2[] = { "z", "m", "0", "1", "2", "3", "5", "6" }; // 배열 생성

				loop2: while (true) { // 스캐너 입력값 유효성 검증 로직
					String pick2 = scn.nextLine();
					boolean pickcheck = Arrays.asList(arr2).contains(pick2);
					if (pickcheck == false) {
						System.out.println("올바른 키를 입력해주세요 >> ");
						continue;
					} else if (pick2.equals("z")) {
						st.pop();
						stn = st.lastElement();
						continue loop;
					} else if (pick2.equals("m")) {
						MainMenu.subMenu();
					} else {
						int intpick2 = Integer.parseInt(pick2);

						switch (intpick2) {
						case 0:
							card.setCard_in(5000);
							break;
						case 1:
							card.setCard_in(10000);
							break;
						case 2:
							card.setCard_in(20000);
							break;
						case 3:
							card.setCard_in(30000);
							break;
						case 5:
							card.setCard_in(50000);
							break;
						case 6:
							st.push(3); // st = {0, 1, 2, 3}
							break loop2;
						}
						// 카드 구매 확정에 따라 card 요소 최종 set하기
						card.setCard_no(CardService.genCardNo()); // 랜덤카드번호 생성 및 부여
						card.setCard_name(display.get(intpick - 1).getCard_name());
						card.setId(loginInfo);
						int c = dao.cardInsert(card);
						if (c != 0) {
							System.out.println("카드 구매가 완료되었습니다!!");
						} else {
							System.out.println("구매 실패");
						}
						continue loop;
					}
				}
			case 3:
				// 스택3 [선택지]
				System.out.println("┌───────┐┌──────────────────────┐");
				System.out.println("│ z.취소 ││ 금액 입력(1만원 단위) >> │");
				System.out.println("└───────┘└──────────────────────┘");

				String other = scn.nextLine();
				while (true) {
					try {
						if (other.equals("z")) {
							st.pop();
							stn = st.lastElement();
							continue loop;
						}
						int iother = Integer.parseInt(other);
						if (iother <= 0 || iother > 500000 || iother % 10000 != 0) {
							System.out.print("정확한 금액를 입력하세요(1만원 단위) >> ");
							other = scn.nextLine();
						} else {
							card.setCard_in(iother);
							break;
						}
					} catch (Exception e) {
						System.out.print("숫자만 입력하세요 >>");
						other = scn.nextLine();
					}
				}

				// 카드 구매 확정에 따라 card 요소 최종 set하기
				card.setCard_no(CardService.genCardNo()); // 랜덤카드번호 생성 및 부여
				card.setCard_name(display.get(intpick - 1).getCard_name());
				card.setId(loginInfo);
				int c = dao.cardInsert(card);
				if (c != 0) {
					System.out.println("카드 구매가 완료되었습니다!!");
				} else {
					System.out.println("구매 실패");
				}

				st.clear();
				stn = 0;
				continue loop; // 여까지 내려오는 경우는 딱 하나: 임의금액으로 카드구매한완료한 경우 뿐임
			} // 대switch문}
				// ..에는 break가 없어야 한다!
		} // 대while문↗}

	}

	//
	//
	//
	//
	//
	//

	public void cardList() {
		System.out.println("────────────[ 나의 카드 ]────────────");
		if (dao.cardList(loginInfo).isEmpty()) {
			System.out.println("보유중인 카드가 없습니다");
		} else {
			System.out.println("┌────────────────────────────────────────────┬────────┬─────────────────────┬────────────┬────────────┐");
			System.out.println("│                 카드이름                   │  잔액  │       카드번호      │  등록날짜  │  유효기간  │");
			System.out.println("├────────────────────────────────────────────┼────────┼─────────────────────┼────────────┼────────────┤");
			dao.cardList(loginInfo).toString();
			System.out.println("└────────────────────────────────────────────┴────────┴─────────────────────┴────────────┴────────────┘");
			cardTopUp();
		}
		System.out.println("───────────────────────────────────");
	}

	//
	//
	//
	//
	//
	//

	public void cardTopUp() {
		List<CardInfo> mycardList = dao.cardList(loginInfo);
		st.clear();
		stn = 0;
		st.push(0);
		// 교수님 질문 변수 card 초기화 해야됨?
		loop: while (true) {
			switch (stn) {
			case 0:
				// 스택0 [선택지]
				System.out.println("┌───────┐┌───────┐┌───────────────────────┐");
				System.out.println("│ z.뒤로 ││ m.메인 ││ 카드충전(카드번호 선택) >> │");
				System.out.println("└───────┘└───────┘└───────────────────────┘");
				String arr[] = new String[mycardList.size() + 2]; // 선택지배열 생성(z,m자리 추가)
				arr[0] = "z"; // z,m 배치
				arr[1] = "m";
				for (int i = 2; i < mycardList.size() + 2; i++) { // 나머지 자리 채운다
					arr[i] = i - 1 + ""; // 형변환 String ← int // arr[2] = 1이어야 하므로
				}

				while (true) { // 스캐너 입력값 유효성 검증 로직
					pick = scn.nextLine();
					boolean pickcheck = Arrays.asList(arr).contains(pick); // 배열 내 특정값 찾기
					if (pickcheck == false) { // 상황1: 완전 엉뚱한 값 입력
						System.out.println("올바른 키를 입력해주세요 >> ");
						continue; // {z, m, 1, 2, 3, ...} 중에 하나 제대로 입력할 때까지 루프
					} else if (pick.equals("z")) { // 상황2: '뒤로'
						MainMenu.cardMenu();
					} else if (pick.equals("m")) { // 상황3: '메인'
						MainMenu.subMenu();
					} else { // 상황4: 숫자선택지 정상 입력
						st.push(1); // 다음스택에 1을 넣음: st = {0, 1}
						break;
					}
				}

			case 1:
				// 스택1 [선택지]
				intpick = Integer.parseInt(pick); // 형변환 int ← String
				System.out.println("───────────────────────");
				System.out.println(mycardList.get(intpick - 1).toString());
				System.out.println("-----------------------------");
				System.out.println("얼마를 충전하시겠습니까?");
				System.out.println("(카드 당 최대 50만원까지 보유 가능합니다)");
				System.out.println("1. 1만원\n2. 2만원\n3. 3만원\n5. 5만원\n6. 다른금액");
				System.out.println("-----------------------------");
				System.out.println("┌───────┐┌───────┐┌────────────────┐");
				System.out.println("│ z.뒤로 ││ m.메인 ││ 구매 번호 선택 >> │");
				System.out.println("└───────┘└───────┘└────────────────┘");

				String arr2[] = { "z", "m", "1", "2", "3", "5", "6" }; // 배열 생성

				loop2: while (true) { // 스캐너 입력값 유효성 검증 로직
					String pick2 = scn.nextLine();
					boolean pickcheck = Arrays.asList(arr2).contains(pick2);
					if (pickcheck == false) {
						System.out.println("올바른 키를 입력해주세요 >> ");
						continue;
					} else if (pick2.equals("z")) {
						dao.cardList(loginInfo).toString();
						st.pop();
						stn = st.lastElement();
						continue loop;
					} else if (pick2.equals("m")) {
						MainMenu.subMenu();
					} else {
						int intpick2 = Integer.parseInt(pick2);

						switch (intpick2) {
						case 1:
							card.setCard_in(10000);
							break;
						case 2:
							card.setCard_in(20000);
							break;
						case 3:
							card.setCard_in(30000);
							break;
						case 5:
							card.setCard_in(50000);
							break;
						case 6:
							st.push(2); // st = {0, 1, 2}
							break loop2;
						}
						// 카드 충전 확정에 따라 card 요소 최종 set하기
						card.setCard_no(mycardList.get(intpick - 1).getCard_no());
						card.setId(loginInfo);
						int c = dao.cardTopUp(card);
						if (c != 0) {
							System.out.println("카드 충전이 완료되었습니다!!");
						} else {
							System.out.println("충전 실패");
						}
						continue loop;
					}
				}
			case 2:
				// 스택2 [선택지]
				System.out.println("┌───────┐┌──────────────────────┐");
				System.out.println("│ z.취소 ││ 금액 입력(1만원 단위) >> │");
				System.out.println("└───────┘└──────────────────────┘");

				String other = scn.nextLine();
				while (true) {
					try {
						if (other.equals("z")) {
							st.pop();
							stn = st.lastElement();
							continue loop;
						}
						int iother = Integer.parseInt(other);
						if (iother <= 0 || iother > 500000 || iother % 10000 != 0) {
							System.out.print("정확한 금액를 입력하세요(1만원 단위) >> ");
							other = scn.nextLine();
						} else if (iother + card.getCard_bal() >= 500000) {
							System.out.println("카드 잔액은 50만원을 초과할 수 없습니다. 다시 입력해 주세요 >> ");
							other = scn.nextLine();
						} else {
							card.setCard_in(iother);
							break;
						}
					} catch (Exception e) {
						System.out.print("숫자만 입력하세요 >>");
						other = scn.nextLine();
					}
				}

				// 카드 충전 확정에 따라 card 요소 최종 set하기
				card.setCard_no(mycardList.get(intpick - 1).getCard_no());
				card.setId(loginInfo);
				int c = dao.cardTopUp(card);
				if (c != 0) {
					System.out.println("카드 충전이 완료되었습니다!!");
				} else {
					System.out.println("충전 실패");
				}

				st.clear();
				stn = 0;
				continue loop; // 여까지 내려오는 경우는 딱 하나: 임의금액으로 카드충전완료한 경우 뿐임
			} // 대switch문}
				// ..에는 break가 없어야 한다!
		} // 대while문↗}

	}

}
