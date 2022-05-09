package com.jyk.starbucks.service;

import java.util.Scanner;

import com.jyk.MainMenu;
import com.jyk.starbucks.vo.CardInfo;


public class CardManager {
	CardService dao = new CardService();
	CardInfo card = new CardInfo();
	Scanner scn = new Scanner(System.in);
	
	
	//카드등록
	public void cardInsert() {
		System.out.println("\n──────[카드 구매]──────");
		System.out.println("구매하실 카드를 선택해 주세요");
		System.out.println("───────────────────────");
		dao.cardList().toString();
		System.out.println("───────────────────────");
//		System.out.print("ID >> ");
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

		
		
	}
	
	

}
