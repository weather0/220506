package com.jyk.starbucks.service;

import java.util.Scanner;

import com.jyk.MainMenu;
import com.jyk.starbucks.vo.CardFace;
import com.jyk.starbucks.vo.CardInfo;

public class CardManager {
	CardService dao = new CardService();
	CardInfo card = new CardInfo();
	CardFace cf = new CardFace();
	Scanner scn = new Scanner(System.in);
	int exc;

	// 카드등록
	public void cardInsert() {
		
		while(true) {
			System.out.println("\n──────[카드 구매]──────");
			System.out.println("새로운 스타벅스 카드를 만나보세요!");
			System.out.println("───────────────────────");
			dao.cardDisplay().toString(); // cardDisplay()메서드가 return한 display는 CardFace타입이므로 재정의한 toString으로 출력
			System.out.println("───────────────────────");
			System.out.print("구매하실 카드를 선택해 주세요 >> ");
			try {
				exc = Integer.parseInt(scn.nextLine());
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요");
				continue;
			}
			System.out.println("───────────────────────");
			String cardPick = dao.cardDisplay().get(exc - 1).getCard_name(); // cardDisplay()메서드의return값인display배열에서.내가고른순번것만get해라.그CardFace필드중에서도name만
			System.out.println(cardPick);
			
			//카드설명메서드 추가
			
			
			System.out.println("───────────────────────");
			System.out.println("1.구매하기   2.뒤로가기");
			System.out.print("메뉴선택 >> ");
			try {
				exc = Integer.parseInt(scn.nextLine());
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요");
				continue;
			}
			if(exc == 1) {
				break;
			} else
			
			
			
		}
		
		
		
		
		
		card.setCard_name(cardPick);
		System.out.println("-----------------------");
		System.out.println("얼마를 충전하시겠습니까? (카드 당 최대 50만원까지 보유 가능합니다.)");
		System.out.println("0. 5천원 \n1. 1만원\n2. 2만원\n3. 3만원\n5. 5만원\n6. 다른금액\n7. 뒤로가기");
		System.out.println("-----------------------");
		System.out.print("메뉴선택 >> ");
//		while (true) {
			int buy = Integer.parseInt(scn.nextLine());
			switch (buy) {
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
				buy = Integer.parseInt(scn.nextLine());
				while (true) {
					if (buy % 10000 == 0) {
						card.setCard_in(buy);
						break;
					} else {
						System.out.print("다시 입력해 주세요(1만원 단위로만 충전이 가능합니다) >> ");
					}
				}

				break;
			case 7:
				break;
			}
			
			card.setCard_no(CardService.genCardNo()); //랜덤카드번호 생성 및 부여
			
			int c = dao.cardInsert(card);
			if (c != 0) {
				System.out.println("카드 구매 완료");
			} else {
				System.out.println("구매 실패");
			}
			MainMenu.cardMenu();
//		}
	}

	// 카드충전
	public void cardTopup() {
		System.out.println("\n──────[카드 충전]──────");
		
		
		
		// 복붙상태 수정예정
		System.out.println("새로운 스타벅스 카드를 만나보세요!");
		System.out.println("───────────────────────");
		dao.cardDisplay().toString(); // cardDisplay()메서드가 return한 display는 CardFace타입이므로 재정의한 toString으로 출력
		System.out.println("───────────────────────");
		System.out.print("구매하실 카드를 선택해 주세요 >> ");
		int n = Integer.parseInt(scn.nextLine());
		System.out.println("───────────────────────");
		String cardPick = dao.cardDisplay().get(n - 1).getCard_name(); // cardDisplay()메서드의return값인display배열에서.내가고른순번것만get해라.그CardFace필드중에서도name만
		card.setCard_name(cardPick);
		System.out.println(cardPick);
		System.out.println("-----------------------");
		System.out.println("얼마를 충전하시겠습니까? (카드 당 최대 50만원까지 보유 가능합니다.)");
		System.out.println("0. 5천원 \n1. 1만원\n2. 2만원\n3. 3만원\n5. 5만원\n6. 다른금액\n7. 뒤로가기");
		System.out.println("-----------------------");
		System.out.print("번호를 선택해 주세요 >> ");
		while (true) {
			n = Integer.parseInt(scn.nextLine());
			switch (n) {
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
					n = Integer.parseInt(scn.nextLine());
					if (n % 10000 == 0) {
						card.setCard_in(n);
						break;
					} else {
						System.out.print("다시 입력해 주세요(1만원 단위로만 충전이 가능합니다) >> ");
					}
				}

				break;
			case 7:
				break;

			}
		}

	}

}

//		while(true) {
//			String id = scn.nextLine();
//			member.setId(id);
//			if(!id.equals(dao.memberView(member).getId())) {
//				break;
//			} else {
//				System.out.println("이미 가입된 ID입니다. 다시 입력해주세요 >>");
//			}
//		}
//		System.out.print("비밀번호 입력  >> ");
//		String password = scn.nextLine();
//		System.out.print("비밀번호 재입력 >> ");
//		while (true) {
//			String password2 = scn.nextLine();
//			if (password.equals(password2)) {
//				member.setPassword(password);
//				break;
//			} else {
//				System.out.println("비밀번호가 불일치합니다. 다시 정확히 입력해주세요 >>");
//			}
//		}
//		System.out.print("연락처 >> ");
//		member.setContact(scn.nextLine());
//
//		int n = dao.memberInsert(member);
//		if (n != 0) {
//			System.out.println("입력 완료");
//		} else {
//			System.out.println("입력 실패");
//		}
//		MainMenu.mainMenu();
