package com.jyk.starbucks.service;

import java.util.List;
import java.util.Scanner;

import com.jyk.MainMenu;
import com.jyk.starbucks.vo.CardFace;
import com.jyk.starbucks.vo.CardInfo;

public class CardManager {
	CardService dao = new CardService();
	CardInfo card = new CardInfo();
	CardFace cf = new CardFace();
	Scanner scn = new Scanner(System.in);
	String cur;
	List<CardFace> display = dao.cardDisplay(); // 우변: cardDisplay()메서드가 return한 display =
	int pick = 0; // 클래스단에서는 꼬일 일이 적어 굳이 초기화 안해주고 int pick;만 해도 되지만, 그래도 웬만하면 선언할 때 초기화를 해주자

	// 카드등록
	public void cardInsert() {

		while (true) {
			System.out.println();
			System.out.println("─────────[ 카드 구매 ]─────────");
			System.out.println("새로운 스타벅스 카드를 만나보세요!");
			System.out.println("─────────────────────────────");
			display.toString();
			System.out.println("─────────────────────────────");
			System.out.println("┌──────────┐┌───────────────┐");
			System.out.println("│ z.뒤로가기 ││ 카드번호 선택 >> │");
			System.out.println("└──────────┘└───────────────┘");
			cur = scn.nextLine();

			if (cur.equals("z")) { // case1: z를 입력한 경우
				MainMenu.cardMenu();
			} else {
				while (true) {
					try {
						pick = Integer.parseInt(cur);
						// case2: 숫자를 입력했으나, 출력된 카드목록의 범위를 넘어선 경우
						if (pick <= 0 || pick > display.get(display.size() - 1).getCardorder()) {
							// display배열 마지막 인덱스(size-1)에 들어있는 CardFace타입(order,name,avl) 중에서 cardorder만 get
							// display = {(order,name,avl),(order,name,avl),...,(order,name,avl)}
							System.out.print("정확한 카드번호를 입력하세요 >> ");
							cur = scn.nextLine();
						} else {
							break; // case1,2,3 모두 해당안되는 경우(=카드번호 정확히 입력한 경우)
						}

					} catch (Exception e) {
						System.out.print("숫자만 입력하세요 >>"); // case3: z도, 숫자도 아닌 영 엉뚱한 키를 입력한 경우
						cur = scn.nextLine();
					}
				}
			}

			System.out.println("───────────────────────");
			String cardPick = display.get(pick - 1).getCard_name(); // cardDisplay()메서드의return값인display배열에서.내가고른순번것만get해라.그CardFace필드중에서도name만
			System.out.println(cardPick);

			// <카드설명메서드 추가하기>

			boolean q = true;
			while (q) {
				System.out.println("───────────────────────");
				System.out.println("1.구매하러가기   2.뒤로가기");
				System.out.print("메뉴선택 >> ");
				try {
					pick = Integer.parseInt(scn.nextLine()); // 숫자범위예외처리하기
				} catch (Exception e) {
					System.out.println("숫자만 입력해주세요");
				}
				switch (pick) {
				case 1:
					card.setCard_name(cardPick);
					System.out.println("-----------------------");
					System.out.println("얼마를 충전하시겠습니까? (카드 당 최대 50만원까지 보유 가능합니다.)");
					System.out.println("0. 5천원 \n1. 1만원\n2. 2만원\n3. 3만원\n5. 5만원\n6. 다른금액\n7. 뒤로가기");
					System.out.println("-----------------------");
					System.out.print("메뉴선택 >> ");
					try {
						pick = Integer.parseInt(scn.nextLine());
					} catch (Exception e) {
						System.out.println("숫자만 입력해주세요");
					}
					switch (pick) {
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
					case 4:
						card.setCard_in(40000);
						break;
					case 5:
						card.setCard_in(50000);
						break;
					case 6:
						System.out.print("금액을 입력해 주세요(1만원 단위) >> ");
						while (true) {
							pick = Integer.parseInt(scn.nextLine());
							if (pick % 10000 == 0) {
								card.setCard_in(pick);
								break;
							} else {
								System.out.print("다시 입력해 주세요(1만원 단위로만 충전이 가능합니다) >> ");
							}
						}

						break;
					case 7: // 뒤로가기 → [구매하러가기]
						break;
					}
					

					System.out.println("구");

					card.setCard_no(CardService.genCardNo()); // 랜덤카드번호 생성 및 부여

					int c = dao.cardInsert(card);
					if (c != 0) {
						System.out.println("카드 구매 완료");
					} else {
						System.out.println("구매 실패");
					}
					MainMenu.cardMenu();

				case 2: // 뒤로가기
					break;

				}
			}
		}

	}

	
	
	
	public void cardList() {
		while (true) {

		}
	}

}
