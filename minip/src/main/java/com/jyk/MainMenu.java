package com.jyk;

import java.util.Scanner;

import com.jyk.starbucks.service.MembershipManager;

public class MainMenu {
	private Scanner scn = new Scanner(System.in);
	MembershipManager e = new MembershipManager();

	private void mainMenu() {
		boolean b = true;
		do {
			System.out.println("=======================");
			System.out.println("===== 1.멤버십 가입 =====");
			System.out.println("===== 2.로  그  인 =====");
			System.out.println("===== 3.앱  종  료 =====");
			System.out.println("=======================");
			System.out.print("번호를 눌러주세요 >> ");
			int num = scn.nextInt();
			switch(num) {
			case 1:
				e.memberInsert();
				break;
			case 2:
				e.memberSignIn();
				break;
			case 3:
				b = false;
				break;
			}
		} while (b);
		scn.close();

	}

	public void run() {
		mainMenu();
	}
}
