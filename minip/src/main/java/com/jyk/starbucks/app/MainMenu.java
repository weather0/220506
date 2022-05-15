package com.jyk.starbucks.app;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.jyk.starbucks.service.CardManager;
import com.jyk.starbucks.service.MembershipManager;
import com.jyk.starbucks.service.SBMenuManager;

public class MainMenu {
	static Scanner scn = new Scanner(System.in);
	static MembershipManager m = new MembershipManager();

//	멤버관리자와 카드관리자가 클래스가 나눠져 있어서 상호간 전역변수 공유가 당연히 안된다! 
//	로그인직후에 그쪽 전역변수를 c에 세팅해줘야함
	static CardManager c = null;
	static SBMenuManager s = null;
//	→ m.signIn에서 직접 subMenu로 진입함. 그쪽으로 코드 이동.

	static int n = 0;

	public static void mainMenu() {

//		try {
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⡛⠛⠋⠀⠀⠙⠛⠛⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⡦⠀⠀⠀⠀⢴⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠉⠻⣿⠃⠀⣠⣄⠀⠘⣿⠟⠉⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡌⠉⠙⠧⠀⠀⠀⠀⠐⠚⠛⠛⠛⠂⠀⠀⠀⠀⠼⠛⠉⢡⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠻⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀⢀⣀⣤⣤⣤⣤⣤⣤⣤⣤⣀⣀⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣿⣿⠟⢩⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣦⣶⣿⡿⣻⠟⠋⠁⠀⠀⠈⠉⠻⣿⢻⣿⣶⣤⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠋⠉⠀⠀⢀⣴⣿⢟⡟⣱⠏⣸⣧⣤⣤⣀⠀⠀⣀⣤⣤⣼⣧⠹⣮⠻⡝⣿⣦⡀⠀⠀⠉⠙⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⢣⡟⢰⡟⠀⣿⣧⣶⣮⣿⠆⢠⣿⣵⣶⣼⣿⡄⢹⡇⢹⡌⢿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⢀⣀⣤⣤⣠⣿⣿⣿⡇⣼⡇⢸⡇⠀⣿⠁⠈⠁⠈⠀⠀⠃⠀⠁⠀⣿⠃⢸⡷⠈⣿⠸⣿⣿⣿⣄⣤⣤⣄⣀⠀⠀⠙⢿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣥⣤⣶⡿⠟⠛⠛⣻⣿⣿⣿⣿⡇⢻⡇⠸⣿⡀⠹⣇⠀⠀⠀⠛⠛⠀⠀⠀⢰⡟⠀⣾⡇⢰⣿⠀⣿⣿⣿⣿⣟⠛⠛⠻⢿⣶⣤⣬⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⠛⠛⠋⠁⢀⣀⣤⣴⣿⣿⣿⣿⣿⣇⠘⣷⠀⢻⣧⠀⢻⣆⠀⠙⠻⠟⠋⠀⣰⡟⠀⣼⡟⠀⣾⠇⢸⣿⣿⣿⣿⣿⣦⣤⣄⡀⠈⠙⠛⠛⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣤⣤⣴⣾⠿⠛⠛⣿⣿⣿⣿⣿⣿⣿⡀⢻⡇⠈⣿⡆⠈⣿⣷⣤⣀⣀⣤⣾⣿⠃⢠⣿⠃⢸⣿⠀⣿⣿⣿⣿⣿⣿⣿⡛⠛⠿⣷⣦⣤⣤⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⠛⠛⠋⠁⣀⣤⣼⣿⣿⣿⣿⣿⣿⡿⠁⣾⠇⢠⣿⠇⢀⣿⠏⠉⠛⠛⠋⠙⣿⡄⠘⣿⡆⠸⣿⡀⢻⣿⣿⣿⣿⣿⣿⣧⣤⣀⠀⠉⠛⠛⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣧⣤⣶⣿⠿⠛⢻⣿⣿⣿⣿⣿⠟⢀⡾⠋⣠⡿⠋⢀⣾⠏⠀⠀⠀⠀⠀⠀⠹⣷⡄⠘⢿⣆⠘⢷⣄⠹⣿⣿⣿⣿⣿⣿⠛⠿⣿⣶⣤⣴⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣷⠛⠋⠁⣠⣴⣾⣿⣿⣿⣿⠋⣰⡿⠁⣼⡟⠁⣰⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣆⠀⢻⣧⠀⢻⣆⠘⣿⣿⣿⣿⣿⣦⣄⡀⠉⠛⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣗⣶⣿⠟⠛⢻⣿⣿⣿⠟⠀⣿⡇⠀⣿⡇⠀⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⠀⢸⣿⠇⢸⣿⠀⠻⢿⣿⣿⡟⠛⠻⣿⣶⢶⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⡍⠁⣠⣶⣿⠉⠁⠀⠀⠀⠘⣷⡀⠹⣷⡀⠘⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⠃⢀⣾⠟⢀⣾⠏⠀⠀⠀⠈⠉⣽⣶⣄⡈⢩⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⢁⣀⠀⠀⠀⢀⣤⣿⣿⡄⠙⣿⡄⠈⢿⣦⠀⠀⠀⠀⠀⠀⣰⡿⠃⢠⣾⠏⢠⣾⣿⣦⣀⠀⠀⠀⢀⣈⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⢴⣿⠟⠲⣶⣾⣿⣿⣿⢺⣿⠀⢸⣿⡄⠈⣿⣧⠀⠀⠀⠀⣸⣿⠁⢀⣿⡏⠀⣿⡏⣿⣿⣿⣿⣶⡖⠻⣿⣷⣴⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣅⣰⣾⡿⣿⣿⣿⠏⣸⡿⠀⢸⣿⠃⠀⣿⡿⠀⠀⠀⠀⢻⣿⠀⠈⣿⣇⠀⢿⣧⠙⣿⣿⣿⢿⣿⣦⣨⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⢠⣾⡿⠃⣰⡿⠁⢠⣿⠏⠀⣸⣿⠃⠀⠀⠀⠀⠈⢿⣧⠀⠘⣿⣆⠈⢿⣧⠈⢿⣷⣄⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣃⢸⣿⠁⢠⣿⠏⠀⣰⣿⠃⠀⠀⠀⠀⠀⠀⠈⣿⣧⠀⠸⣿⣆⠈⣿⣧⢈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣄⣿⣿⠀⢠⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡆⠀⢻⣿⣠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣾⣿⣧⣀⣀⣀⣀⣀⣀⣀⣀⣼⣿⣷⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⠋⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⡿⠁⠀⠀⠈⣿⣿⠀⠀⠀⠀⠀⠙⢿⠀⠀⠀⠀⠀⠙⡇⠀⠀⢸⣿⠀⠀⢸⡿⠋⠀⠀⠀⠀⢸⠀⠀⠀⠃⠀⠀⣀⠋⠁⠀⠀⠀⢨⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⠀⠀⠈⠉⠲⢿⣶⡆⠀⠀⣶⣶⠃⠀⠀⠀⠀⠸⣿⠀⠀⠀⠃⠀⠀⣼⠀⠀⠈⠁⠀⢀⡇⠀⠀⢸⣿⠀⠀⢸⠀⠀⢀⣴⣿⣶⣿⠀⠀⠀⠀⠀⣾⣿⠀⠀⠀⠉⠒⢾");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⠓⠢⠄⠀⠀⠀⣿⡇⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠈⣿⠀⠀⢰⠄⠀⠀⡇⠀⠀⠸⠟⠀⠀⢸⠀⠀⠀⠻⠿⠿⣿⠀⠀⠀⠀⠀⠘⣿⠛⠲⠤⠀⠀⠀⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⡀⠀⠀⠀⣀⣴⣿⡇⠀⠀⣿⡇⠀⠀⣶⣶⠀⠀⠘⠀⠀⠀⣆⠀⠀⢹⠀⠀⠀⠀⣀⣠⣿⣦⠀⠀⠀⠀⣠⣾⣷⣄⠀⠀⠀⠀⣹⠀⠀⠀⣦⠀⠀⠈⡀⠀⠀⠀⣀⣴⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
//			TimeUnit.MILLISECONDS.sleep(700);

			System.out.println("────────────────────────────────────────────────────────────────────────");
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("          스타벅스만의 특별한 혜택, 스타벅스 리워드를 만나보세요");
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("          스타벅스 리워드는 스타벅스를 사랑해주시는 고객님을 위해 ");
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("      별★을 적립하여 혜택으로 돌려드리는 특별한 리워드 서비스입니다.");
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("             등록된 스타벅스 카드를 매장에서 사용해보세요. ");
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("            스타벅스의 또 다른 즐거움을 느끼실 수 있습니다!");
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("────────────────────────────────────────────────────────────────────────");
			System.out.println();
			System.out.println("                         별★을 차곡차곡 쌓으세요!");
			System.out.println("               스타벅스의 다양한 혜택을 경험할 수 있습니다!");
			System.out.println();
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("                 30개의 별★이 모이면 Gold Level이 됩니다!");
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("       일 년 동안 30개의 별★이 더 모이면 Gold Level이 1년 연장됩니다.");
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("              Welcome Level  ▶  Green Level  ▶  Gold Level");
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
//			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("────────────────────────────────────────────────────────────────────────");
//			TimeUnit.MILLISECONDS.sleep(700);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println();
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│                                │");
		System.out.println("│         1. 멤버십 가입         │");
		System.out.println("│         2. 로  그  인          │");
		System.out.println("│         3. 앱  종  료          │");
		System.out.println("│                                │");
		System.out.println("│       번호를 눌러주세요 >>     │");
		System.out.println("│                                │");
		System.out.println("└────────────────────────────────┘");

		while (true) {
			String pick = scn.nextLine();
			if (pick.equals("1")) {
				m.signUp();
			} else if (pick.equals("2")) {
				m.signIn();
			} else if (pick.equals("3")) {
				System.out.println("앱이 종료되었습니다");
				System.exit(0); // 메뉴 이리저리 옮겨다니다 보면 어느새 무한 루프에 빠져있다. 아예 앱을 꺼버리자
			} else {
				System.out.println("올바른 키를 입력해주세요 >> ");
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

	public static void subMenu() {
		System.out.println("────────────────────────────────────────────────────────────────────────");
		System.out.println("                                                                     ");
		System.out.println("⠀⠀⢀⣤⣶⣾⡿⠻⢿⣷⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀            ");
		System.out.println("⠀⣰⣿⣿⡿⠝⠫⠦⠛⠹⣿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀         ⠀ ");
		System.out.println("⣰⡇⠛⢛⣷⣾⣏⢉⣽⢶⣿⡛⠏⢿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀        ⠀ ");
		System.out.println("⡯⠴⣲⣿⡟⡾⡉⣈⢹⡸⣿⣷⡖⠮⣇⠀⠀⠀⣴⡶⠶⠆⠶⣶⣶⠶⠀⣶⣶⣆⠀⢰⣶⢶⣶⡀⢰⣶⢶⣶⠀⣶⡆⢰⣶⠀⣰⣶⠶⡆⢰⣶⣰⣶⠂⣴⡶⠶⡆⠶ ");
		System.out.println("⣒⣭⣿⡿⣼⣗⡟⠒⢇⢗⣿⣿⣿⣝⡂⠀⠀⠀⣙⣿⣿⡆⠀⣿⣿⠀⣸⣿⣼⣿⡄⢸⣿⣾⣟⠁⢸⣿⢻⣿⡀⣿⣇⣸⣿⠀⣿⣇⣀⡀⢸⣿⣿⣇⠀⣙⣿⣿⡆⠀ ");
		System.out.println("⠹⣺⠿⢻⣸⢺⠀⠀⢨⢯⢿⠘⢿⡳⠁⠀⠀⠀⠙⠛⠛⠁⠀⠛⠋⠀⠛⠃⠀⠛⠃⠘⠛⠈⠛⠂⠘⠛⠛⠋⠀⠈⠛⠛⠋⠀⠈⠛⠛⠃⠘⠛⠈⠛⠃⠙⠛⠛⠁⠀  ");
		System.out.println("⠀⠱⣷⣾⡷⡧⡷⠀⡏⣏⡿⡷⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀          ");
		System.out.println("⠀⠀⠈⠙⠹⢱⠃⠀⢹⡼⠞⠉⠤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀            ⠀ ");
		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────");
//		System.out.println("");
//		System.out.println("고객님");
//		System.out.println("환경을 ");
//		System.out.println("");
		System.out.println();
		System.out.println("┌──────────────────────────────────┐");
		System.out.println("│                                  │");
		System.out.println("│         1. 주문매장 찾기         │");
		System.out.println("│         2. 스타벅스 카드         │");
		System.out.println("│         3. 개인정보 조회         │");
		System.out.println("│         4. 로  그  아 웃         │");
		System.out.println("│                                  │");
		System.out.println("│       번호를 눌러주세요 >>       │");
		System.out.println("│                                  │");
		System.out.println("└──────────────────────────────────┘");
		c = new CardManager(m.my); // 카드관리자에서도 로그인의 전역변수를 사용할 수 있게 세팅
		s = new SBMenuManager(m.my); // 주문관리자에서도 로그인의 전역변수를 사용할 수 있게 세팅
		while (true) {
			String pick = scn.nextLine();
			if (pick.equals("1")) {
				s.sbmenuOrder1();
			} else if (pick.equals("2")) {
				cardMenu();
			} else if (pick.equals("3")) {
				m.memberView();
			} else if (pick.equals("4")) {
				m.signOut();
				mainMenu();
			} else {
				System.out.println("올바른 키를 입력해주세요 >> ");
			}

		}

//		while (true) { // 스캐너 입력값 유효성 검증 로직
//			String arr[] = { "1", "2", "3", "4" };
//			String pick = scn.nextLine();
//			boolean pickcheck = Arrays.asList(arr).contains(pick);
//			if (pickcheck == false) {
//				System.out.println("올바른 키를 입력해주세요 >> ");
//				continue;
//			} else {
//				int intpick = Integer.parseInt(pick);
//				switch (intpick) {
//				case 1:
//					s.sbmenuOrder1();
//					break;
//				case 2:
//					cardMenu();
//					break;
//				case 3:
//					m.memberView();
//					break;
//				case 4:
//					m.signOut();
//					mainMenu();
//					break;
//				}
//			}
//		}

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

	public static void cardMenu() {
		System.out.println();
		System.out.println("┌──────────────────────────────┐");
		System.out.println("│                              │");
		System.out.println("│        1. 카드 구매          │");
		System.out.println("│        2. 카드 조회          │");
		System.out.println("│        3. 메인 메뉴          │");
		System.out.println("│                              │");
		System.out.println("│      번호를 눌러주세요 >>    │");
		System.out.println("│                              │");
		System.out.println("└──────────────────────────────┘");

		while (true) {
			String pick = scn.nextLine();
			if (pick.equals("1")) {
				c.cardInsert();
			} else if (pick.equals("2")) {
				c.cardList();
			} else if (pick.equals("3")) {
				subMenu();
			} else {
				System.out.println("올바른 키를 입력해주세요 >> ");
			}

		}

//		while (true) {
//			System.out.println();
//			System.out.println();
//			System.out.println("=======================");
//			System.out.println("===== 1.카 드 구 매 =====");
//			System.out.println("===== 2.카 드 조 회 =====");
//			System.out.println("===== 3.메 인 메 뉴 =====");
//			System.out.println("=======================");
//			System.out.print("번호를 눌러주세요 >> ");
//
//			String arr[] = { "1", "2", "3" };
//
//			String pick = scn.nextLine();
//			boolean pickcheck = Arrays.asList(arr).contains(pick);
//			if (pickcheck == false) {
//				System.out.println("올바른 키를 입력해주세요 >> ");
//				continue;
//			} else {
//				int intpick = Integer.parseInt(pick);
//				switch (intpick) {
//				case 1:
//					c.cardInsert();
//					break;
//				case 2:
//					c.cardList();
//					break;
//				case 3:
//					subMenu();
//					break;
//				}
//			}
//		}
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
	// 한글 문자열 줄맞춤 프리셋 (구글링해도 드럽게 안 나옴. 함수로 쥐어짜다가 얻어걸림!)
	// length는 양식틀 고정사이즈, kor은 견적낼 한글포함 문자열
	// 한글을 공백으로 치환하여 String.length메서드가 한글을 2글자로 인식하게 해서 길이를 뽑는다
	// 양식 사이즈와 kor 문자열의 견적길이 차이만큼 공백으로 메운다. (여기까지가 korPrint메서드)
	// 뒤에 "│" 칸막이는 메서드 호출하는 데서 알아서 적절히 (표처럼 칼럼은 가변적이므로)
	public static void korPrint(int length, String kor) {
		int k = length - kor.replaceAll("[ㄱ-ㅎㅏ-ㅣ가-힣]", "  ").length();
		for (int i = 0; i < k; i++) {
			System.out.print(" ");
		}

	}

}