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
	static CardManager c = null;
	// 멤버관리자와 카드관리자가 클래스가 나눠져 있어서 상호간 전역변수 공유가 당연히 안된다! 로그인직후에 그쪽 전역변수를 c에 세팅해줘야함
	static SBMenuManager s = null;
	static int n = 0;

	public static void mainMenu() {

		try {
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⡛⠛⠋⠀⠀⠙⠛⠛⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⡦⠀⠀⠀⠀⢴⣿⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠉⠻⣿⠃⠀⣠⣄⠀⠘⣿⠟⠉⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡌⠉⠙⠧⠀⠀⠀⠀⠐⠚⠛⠛⠛⠂⠀⠀⠀⠀⠼⠛⠉⢡⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠻⣿⣿⣿⣿⣿⣿⣿⣿⣄⠀⠀⠀⢀⣀⣤⣤⣤⣤⣤⣤⣤⣤⣀⣀⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣿⣿⠟⢩⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣦⣶⣿⡿⣻⠟⠋⠁⠀⠀⠈⠉⠻⣿⢻⣿⣶⣤⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠋⠉⠀⠀⢀⣴⣿⢟⡟⣱⠏⣸⣧⣤⣤⣀⠀⠀⣀⣤⣤⣼⣧⠹⣮⠻⡝⣿⣦⡀⠀⠀⠉⠙⠀⠀⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⢣⡟⢰⡟⠀⣿⣧⣶⣮⣿⠆⢠⣿⣵⣶⣼⣿⡄⢹⡇⢹⡌⢿⣿⣦⡀⠀⠀⠀⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⢀⣀⣤⣤⣠⣿⣿⣿⡇⣼⡇⢸⡇⠀⣿⠁⠈⠁⠈⠀⠀⠃⠀⠁⠀⣿⠃⢸⡷⠈⣿⠸⣿⣿⣿⣄⣤⣤⣄⣀⠀⠀⠙⢿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣥⣤⣶⡿⠟⠛⠛⣻⣿⣿⣿⣿⡇⢻⡇⠸⣿⡀⠹⣇⠀⠀⠀⠛⠛⠀⠀⠀⢰⡟⠀⣾⡇⢰⣿⠀⣿⣿⣿⣿⣟⠛⠛⠻⢿⣶⣤⣬⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⠛⠛⠋⠁⢀⣀⣤⣴⣿⣿⣿⣿⣿⣇⠘⣷⠀⢻⣧⠀⢻⣆⠀⠙⠻⠟⠋⠀⣰⡟⠀⣼⡟⠀⣾⠇⢸⣿⣿⣿⣿⣿⣦⣤⣄⡀⠈⠙⠛⠛⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣤⣤⣴⣾⠿⠛⠛⣿⣿⣿⣿⣿⣿⣿⡀⢻⡇⠈⣿⡆⠈⣿⣷⣤⣀⣀⣤⣾⣿⠃⢠⣿⠃⢸⣿⠀⣿⣿⣿⣿⣿⣿⣿⡛⠛⠿⣷⣦⣤⣤⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⠛⠛⠋⠁⣀⣤⣼⣿⣿⣿⣿⣿⣿⡿⠁⣾⠇⢠⣿⠇⢀⣿⠏⠉⠛⠛⠋⠙⣿⡄⠘⣿⡆⠸⣿⡀⢻⣿⣿⣿⣿⣿⣿⣧⣤⣀⠀⠉⠛⠛⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣧⣤⣶⣿⠿⠛⢻⣿⣿⣿⣿⣿⠟⢀⡾⠋⣠⡿⠋⢀⣾⠏⠀⠀⠀⠀⠀⠀⠹⣷⡄⠘⢿⣆⠘⢷⣄⠹⣿⣿⣿⣿⣿⣿⠛⠿⣿⣶⣤⣴⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣷⠛⠋⠁⣠⣴⣾⣿⣿⣿⣿⠋⣰⡿⠁⣼⡟⠁⣰⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣆⠀⢻⣧⠀⢻⣆⠘⣿⣿⣿⣿⣿⣦⣄⡀⠉⠛⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣗⣶⣿⠟⠛⢻⣿⣿⣿⠟⠀⣿⡇⠀⣿⡇⠀⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⠀⢸⣿⠇⢸⣿⠀⠻⢿⣿⣿⡟⠛⠻⣿⣶⢶⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⡍⠁⣠⣶⣿⠉⠁⠀⠀⠀⠘⣷⡀⠹⣷⡀⠘⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⠃⢀⣾⠟⢀⣾⠏⠀⠀⠀⠈⠉⣽⣶⣄⡈⢩⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⢁⣀⠀⠀⠀⢀⣤⣿⣿⡄⠙⣿⡄⠈⢿⣦⠀⠀⠀⠀⠀⠀⣰⡿⠃⢠⣾⠏⢠⣾⣿⣦⣀⠀⠀⠀⢀⣈⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⢴⣿⠟⠲⣶⣾⣿⣿⣿⢺⣿⠀⢸⣿⡄⠈⣿⣧⠀⠀⠀⠀⣸⣿⠁⢀⣿⡏⠀⣿⡏⣿⣿⣿⣿⣶⡖⠻⣿⣷⣴⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣅⣰⣾⡿⣿⣿⣿⠏⣸⡿⠀⢸⣿⠃⠀⣿⡿⠀⠀⠀⠀⢻⣿⠀⠈⣿⣇⠀⢿⣧⠙⣿⣿⣿⢿⣿⣦⣨⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⢠⣾⡿⠃⣰⡿⠁⢠⣿⠏⠀⣸⣿⠃⠀⠀⠀⠀⠈⢿⣧⠀⠘⣿⣆⠈⢿⣧⠈⢿⣷⣄⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣃⢸⣿⠁⢠⣿⠏⠀⣰⣿⠃⠀⠀⠀⠀⠀⠀⠈⣿⣧⠀⠸⣿⣆⠈⣿⣧⢈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣄⣿⣿⠀⢠⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡆⠀⢻⣿⣠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣾⣿⣧⣀⣀⣀⣀⣀⣀⣀⣀⣼⣿⣷⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⠋⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⡿⠁⠀⠀⠈⣿⣿⠀⠀⠀⠀⠀⠙⢿⠀⠀⠀⠀⠀⠙⡇⠀⠀⢸⣿⠀⠀⢸⡿⠋⠀⠀⠀⠀⢸⠀⠀⠀⠃⠀⠀⣀⠋⠁⠀⠀⠀⢨⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⠀⠀⠈⠉⠲⢿⣶⡆⠀⠀⣶⣶⠃⠀⠀⠀⠀⠸⣿⠀⠀⠀⠃⠀⠀⣼⠀⠀⠈⠁⠀⢀⡇⠀⠀⢸⣿⠀⠀⢸⠀⠀⢀⣴⣿⣶⣿⠀⠀⠀⠀⠀⣾⣿⠀⠀⠀⠉⠒⢾");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⠓⠢⠄⠀⠀⠀⣿⡇⠀⠀⣿⣿⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠈⣿⠀⠀⢰⠄⠀⠀⡇⠀⠀⠸⠟⠀⠀⢸⠀⠀⠀⠻⠿⠿⣿⠀⠀⠀⠀⠀⠘⣿⠛⠲⠤⠀⠀⠀⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⡀⠀⠀⠀⣀⣴⣿⡇⠀⠀⣿⡇⠀⠀⣶⣶⠀⠀⠘⠀⠀⠀⣆⠀⠀⢹⠀⠀⠀⠀⣀⣠⣿⣦⠀⠀⠀⠀⣠⣾⣷⣄⠀⠀⠀⠀⣹⠀⠀⠀⣦⠀⠀⠈⡀⠀⠀⠀⣀⣴⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(20);
			System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
			TimeUnit.MILLISECONDS.sleep(700);

			System.out.println("────────────────────────────────────────────────────────────────────────");
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("          스타벅스만의 특별한 혜택, 스타벅스 리워드를 만나보세요");
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("          스타벅스 리워드는 스타벅스를 사랑해주시는 고객님을 위해 ");
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("      별★을 적립하여 혜택으로 돌려드리는 특별한 리워드 서비스입니다.");
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("             등록된 스타벅스 카드를 매장에서 사용해보세요. ");
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("            스타벅스의 또 다른 즐거움을 느끼실 수 있습니다!");
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("────────────────────────────────────────────────────────────────────────");
			System.out.println();
			System.out.println("                         별★을 차곡차곡 쌓으세요!");
			System.out.println("               스타벅스의 다양한 혜택을 경험할 수 있습니다!");
			System.out.println();
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("                 30개의 별★이 모이면 Gold Level이 됩니다!");
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("       일 년 동안 30개의 별★이 더 모이면 Gold Level이 1년 연장됩니다.");
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("              Welcome Level  ▶  Green Level  ▶  Gold Level");
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println();
			TimeUnit.MILLISECONDS.sleep(30);
			System.out.println("────────────────────────────────────────────────────────────────────────");
			TimeUnit.MILLISECONDS.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│                                │");
		System.out.println("│         1. 멤버십 가입         │");
		System.out.println("│         2. 로  그  인          │");
		System.out.println("│         3. 앱  종  료          │");
		System.out.println("│                                │");
		System.out.println("└────────────────────────────────┘");
		System.out.print("번호를 눌러주세요 >> ");

		while (true) {
			String pick = scn.nextLine();
			if (pick.equals("1")) {
				m.signUp();
				break;
			} else if (pick.equals("2")) {
				m.SignIn(); // 로그인하고
				c = new CardManager(m.my); // 카드관리자에서도 로그인의 전역변수를 사용할 수 있게 세팅
				s = new SBMenuManager(m.my); // 주문관리자에서도 로그인의 전역변수를 사용할 수 있게 세팅
				subMenu();
				break;
			} else if (pick.equals("3")) {
				System.out.println("앱이 종료되었습니다");
				System.exit(0); // 메뉴 이리저리 옮겨다니다 보면 어느새 무한 루프에 빠져있다. 아예 앱을 꺼버리자
				break;
			} else {
				System.out.println("올바른 키를 입력해주세요 >> ");
			}

		}
//		
//		
		// 스캐너 입력값 유효성 검증 로직(선택지 보기가 많거나, 입력값을 함수의 매개변수로 활용시 유용)
//		String arr[] = { "1", "2", "3" };
//
//		while (true) { 
//			String pick = scn.nextLine();
//			boolean pickcheck = Arrays.asList(arr).contains(pick);
//			if (pickcheck == false) {
//				System.out.println("올바른 키를 입력해주세요 >> ");
//				continue;
//			} else {
//				int intpick = Integer.parseInt(pick);
//
//				switch (intpick) {
//				case 1:
//					m.signUp();
//					break;
//				case 2:
//					m.SignIn(); // 로그인하고
//					c = new CardManager(m.my); // 카드관리자에서도 로그인의 전역변수를 사용할 수 있게 세팅
//					s = new SBMenuManager(m.my); // 주문관리자에서도 로그인의 전역변수를 사용할 수 있게 세팅
//					subMenu();
//					break;
//				case 3:
//					System.out.println("앱이 종료되었습니다");
//					System.exit(0); // 메뉴 이리저리 옮겨다니다 보면 어느새 무한 루프에 빠져있다. 아예 앱을 꺼버리자
//					break;
//				}
//			}
//		}

	}

	public static void subMenu() {
		while (true) { // 스캐너 입력값 유효성 검증 로직
			System.out.println();
			System.out.println("=======================");
			System.out.println("===== 1.주문매장찾기 =====");
			System.out.println("===== 2.스타벅스카드 =====");
			System.out.println("===== 3.개인정보조회 =====");
			System.out.println("===== 4.로 그 아 웃 =====");
			System.out.println("=======================");
			System.out.print("번호를 눌러주세요 >> ");

			String arr[] = { "1", "2", "3", "4" };

			String pick = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick);
			if (pickcheck == false) {
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue;
			} else {
				int intpick = Integer.parseInt(pick);
				switch (intpick) {
				case 1:
					s.sbmenuOrder1();
					break;
				case 2:
					cardMenu();
					break;
				case 3:
					m.memberView();
					break;
				case 4:
					m.signOut();
					mainMenu();
					break;
				}
			}
		}

	}

	public static void cardMenu() {
		while (true) {
			System.out.println();
			System.out.println();
			System.out.println("=======================");
			System.out.println("===== 1.카 드 구 매 =====");
			System.out.println("===== 2.카 드 조 회 =====");
			System.out.println("===== 3.메 인 메 뉴 =====");
			System.out.println("=======================");
			System.out.print("번호를 눌러주세요 >> ");

			String arr[] = { "1", "2", "3" };

			String pick = scn.nextLine();
			boolean pickcheck = Arrays.asList(arr).contains(pick);
			if (pickcheck == false) {
				System.out.println("올바른 키를 입력해주세요 >> ");
				continue;
			} else {
				int intpick = Integer.parseInt(pick);
				switch (intpick) {
				case 1:
					c.cardInsert();
					break;
				case 2:
					c.cardList();
					break;
				case 3:
					subMenu();
					break;
				}
			}
		}
	}

}