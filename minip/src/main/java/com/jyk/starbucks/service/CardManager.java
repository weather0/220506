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
	CardFace cf = new CardFace();
	List<CardFace> display = dao.cardDisplay(); // 우변: cardDisplay()메서드가 return한 display =

	// 스캐너 관련 전역 변수
	Scanner scn = new Scanner(System.in);
	String pick = null;
	int intpick = 0; // 클래스단에서는 꼬일 일이 적어 굳이 초기화 안해주고 int pick;만 해도 되지만, 그래도 웬만하면 선언할 때 초기화를 해주자

	
	// 스택 관련 전역 변수
	Stack<Integer> st = new Stack();
	int stn = 0;

	// 카드등록
	public void cardInsert() {
		st.push(0);

		while (true) {

			switch (stn) {
			case 0: // 스택0
				System.out.println();
				System.out.println("─────────[ 카드 구매 ]─────────");
				System.out.println("새로운 스타벅스 카드를 만나보세요!");
				System.out.println("─────────────────────────────");
				display.toString();
				System.out.println("─────────────────────────────");
				System.out.println("┌──────────┐┌───────────────┐");
				System.out.println("│ z.뒤로가기 ││ 카드번호 선택 >> │");
				System.out.println("└──────────┘└───────────────┘");
				st.push(1);

			case 1:
				String arr[]= new String[display.size()+1];
				
				arr[0] = "z";
				for(int i =1; i<display.size(); i++) {
					arr[i]=i+"";
				}
				
				
				
				while(true){
				pick = scn.nextLine();

				boolean pickcheck = Arrays.asList(arr).contains(pick);
				if (pickcheck == false) {
					System.out.println("올바른 키를 입력해주세요");
					continue;
				} else if (pick.equals("z")) { // 상황1: z를 입력한 경우
					MainMenu.cardMenu();
				} else {
					intpick = Integer.parseInt(pick);
				

					// 스캐너 예외 처리 양식(while문째로 복붙하자)
//					while (true) {
//						try {
//							intpick = Integer.parseInt(pick);
//							// 상황2: 숫자를 입력했으나, 출력된 카드목록의 범위를 넘어선 경우(음수 또는 큰수)
//							if (intpick <= 0 || intpick > display.get(display.size() - 1).getCardorder()) {
//								// display배열 마지막 인덱스(size-1)에 들어있는 CardFace타입(order,name,avl) 중에서 cardorder만 get
//								// display = {(order,name,avl),(order,name,avl),...,(order,name,avl)}
//								System.out.print("정확한 카드번호를 입력하세요 >> ");
//								pick = scn.nextLine();
//							} else {
//								break; // 상황1,2,3 모두 해당안되는 경우(=정확히 입력한 경우)
//							}
//
//						} catch (Exception e) {
//							System.out.print("숫자만 입력하세요 >>"); // 상황3: z도, 숫자도 아닌 영 애먼 키를 입력한 경우
//							pick = scn.nextLine();
//						}
//					}
				}

				System.out.println("───────────────────────");
				System.out.println(display.get(intpick - 1).getCard_name());
				// cardDisplay()메서드의return값인display배열에서.내가고른순번것만get해라.그CardFace필드중에서도name만

				// <카드설명메서드 추가하기>

				System.out.println("───────────────────────────────────────────");
				System.out.println("┌──────────┐┌─────────────────────────────┐");
				System.out.println("│ z.뒤로가기 ││ 구매하러 가기 >> (아무 키나 입력) │");
				System.out.println("└──────────┘└─────────────────────────────┘");

				if (scn.nextLine().equals("z")) {
					st.pop();
					stn = st.lastElement();
					break;
				} else {
					st.push(2);
					break;
				}
				}
			case 2: // 스택2
				System.out.println("-----------------------------");
				System.out.println("얼마를 충전하시겠습니까?");
				System.out.println("(카드 당 최대 50만원까지 보유 가능합니다)");
				System.out.println("0. 5천원 \n1. 1만원\n2. 2만원\n3. 3만원\n5. 5만원\n6. 다른금액");
				System.out.println("-----------------------------");
				System.out.println("┌──────────┐┌────────────────┐");
				System.out.println("│ z.뒤로가기 ││ 구매 번호 선택 >> │");
				System.out.println("└──────────┘└────────────────┘");

				String arr2[] = { "z", "1", "2", "3", "5", "6" };
				
				while(true){
				pick = scn.nextLine();

				boolean pickcheck = Arrays.asList(arr2).contains(pick);
				if (pickcheck == false) {
					System.out.println("올바른 키를 입력해주세요");
					continue;
				} else if (pick.equals("z")) { // 상황1: z를 입력한 경우
					st.pop();
					stn = st.lastElement();
					break;
				} else {
					int intpick2 = Integer.parseInt(pick);

//					while (true) {
//						try {
//							intpick2 = Integer.parseInt(pick);
//							// 상황2: 숫자를 입력했으나, 출력된 카드목록의 범위를 넘어선 경우(음수 또는 큰수)
//							if (intpick2 < 0 || intpick2 > 6 || intpick2 == 4) {
//								System.out.print("정확한 번호를 입력하세요 >> ");
//								pick = scn.nextLine();
//							} else {
//								break; // 상황1,2,3 모두 해당안되는 경우(=정확히 입력한 경우)
//							}
//
//						} catch (Exception e) {
//							System.out.print("숫자만 입력하세요 >>"); // 상황3: z도, 숫자도 아닌 영 애먼 키를 입력한 경우
//							pick = scn.nextLine();
//						}
//					}

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
						System.out.print("금액을 입력해 주세요(1만원 단위) >> ");
						String other = scn.nextLine();

						while (true) {
							try {
								int intother = Integer.parseInt(other);
								// 상황2: 숫자를 입력했으나, 출력된 카드목록의 범위를 넘어선 경우(음수 또는 큰수)
								if (intother < 0 || intother > 500000 || intother % 10000 != 0) {
									System.out.print("정확한 금액를 입력하세요(1만원 단위) >> ");
									other = scn.nextLine();
								} else {
									card.setCard_in(intother);
									break; // 상황1,2,3 모두 해당안되는 경우(=정확히 입력한 경우)
								}

							} catch (Exception e) {
								System.out.print("숫자만 입력하세요 >>"); // 상황3: z도, 숫자도 아닌 영 애먼 키를 입력한 경우
								other = scn.nextLine();
							}
						}
						break;
					}

					// 카드 구매 확정에 따라 card 요소 최종 set하기
					card.setCard_no(CardService.genCardNo()); // 랜덤카드번호 생성 및 부여
					card.setCard_name(display.get(intpick - 1).getCard_name());
					int c = dao.cardInsert(card);
					if (c != 0) {
						System.out.println("카드 구매가 완료되었습니다!!");
					} else {
						System.out.println("구매 실패");
					}

					st.push(3);
				}
			} break;
			}
		}

	}

	public void cardList() {
		while (true) {

		}
	}

}
