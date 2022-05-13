package com.jyk.starbucks.service;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import com.jyk.starbucks.app.MainMenu;
import com.jyk.starbucks.vo.MembershipInfo;

public class MembershipManager {
	MembershipService dao = new MembershipServiceImple();
	MembershipInfo member = new MembershipInfo();
	Scanner scn = new Scanner(System.in);
	public String my; // 이때 MainMenu와 패키지가 달라서 반드시 public을 붙여줘야 한다!

	// 회원가입
	public void signUp() {
		System.out.println();
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│                                │");
		System.out.println("│          멤버십 가입           │");
		System.out.println("│ ────────────────────────────── │");
		System.out.println("│      ID를 입력해 주세요 >>     │");
		System.out.println("│                                │");
		System.out.println("│                         z.뒤로 │");
		System.out.println("└────────────────────────────────┘");

		while (true) {
			String id = scn.nextLine();
			if (id.equals("")) { // 무입력 대응
				System.out.println("ID를 입력해 주세요 >> ");
				continue;
			} else if (id.equals("z")) { // 뒤로가기
				MainMenu.mainMenu();
				break;
			}
			member.setId(id); // 입력한 id를 일단 member의 id필드에 세팅
			if (!id.equals(dao.memberView(member).getId())) {
				// 않다(!). 입력값이(id). 같지(equals)(같은 id를 가진 레코드가 DB테이블에서 이미 존재하여 memberView가 정상
				// 실행되고 getID한 것과)
				break; // 그대로 빠져나와라
			} else {
				System.out.println("이미 가입된 ID입니다. 다시 입력해주세요 >>");
			}
		}
//		
//		
		// 비밀번호 설정
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│                                │");
		System.out.println("│          멤버십 가입           │");
		System.out.println("│ ────────────────────────────── │");
		System.out.println("│   비밀번호를 입력해 주세요 >>  │");
		System.out.println("│                                │");
		System.out.println("│                         z.뒤로 │");
		System.out.println("└────────────────────────────────┘");

		loop: while (true) {
			String password = scn.nextLine();
			if (password.equals("")) { // 무입력 대응
				System.out.println("비밀번호를 입력해 주세요 >> ");
				continue;
			} else if (password.equals("z")) { // 뒤로가기
				MainMenu.mainMenu();
			} else {
				System.out.println("똑같이 한 번 더 입력해주세요 >> ");
				for (int i = 0; i < 6; i++) {
					if (i < 5) {
						String password2 = scn.nextLine();
						if (password2.equals("z")) { // 뒤로가기
							MainMenu.mainMenu();
						}
						if (password.equals(password2)) {
							member.setPassword(password);
							break loop; // 이중 반복문 break
						} else {
							System.out.println("비밀번호가 불일치합니다. 다시 정확히 입력해주세요 >> ");
						}
					} else {
						System.out.print("비밀번호 입력 5회 오류! 5초 후 메인으로 돌아갑니다");
						try {
							for (int j = 0; j < 5; j++) {
								TimeUnit.SECONDS.sleep(1);
								System.out.print(".");
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println();
						MainMenu.mainMenu();
					}
				}
			}
		}
//		
//		
		// 연락처 입력
		System.out.println("┌────────────────────────────────────┐");
		System.out.println("│                                    │");
		System.out.println("│            멤버십 가입             │");
		System.out.println("│ ────────────────────────────────── │");
		System.out.println("│  휴대폰 연락처를 입력해 주세요 >>  │");
		System.out.println("│  ('-'제외한 숫자만 입력)           │");
		System.out.println("│                                    │");
		System.out.println("│                             z.뒤로 │");
		System.out.println("└────────────────────────────────────┘");
		while (true) {
			String contact = scn.nextLine();
			if (contact.equals("z")) {
				MainMenu.mainMenu();
			}
			if (Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", contact)) {
				member.setContact(contact);
				// 회원정보 DB입력
				int n = dao.signUp(member);
				if (n != 0) {
					System.out.println("┌────────────────────────────────────┐");
					System.out.println("│                                    │");
					System.out.println("│    멤버십 가입이 완료되었습니다.   │");
					System.out.println("│ ────────────────────────────────── │");
					System.out.println("│     스타벅스 카드를 등록하시고     │");
					System.out.println("│     다양한 혜택을 누려보세요.      │");
					System.out.println("│                                    │");
					System.out.println("│                                    │");
					System.out.println("└────────────────────────────────────┘");
					// <축하메시지>
					System.out.print("아무 키나 눌러 주세요 >> ");
					scn.nextLine();
					MainMenu.mainMenu();
					break;
				} else {
					System.out.println("입력 실패");
				}

			} else {
				System.out.println("유효하지 않은 휴대폰 번호입니다. 다시 입력해 주세요 >> ");
			}

		}
	}

//	
//	
//	
//	
//	
//	

	// 로그인
	public void SignIn() {
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│                                │");
		System.out.println("│            로 그 인            │");
		System.out.println("│ ────────────────────────────── │");
		System.out.println("│      ID를 입력해 주세요 >>     │");
		System.out.println("│                                │");
		System.out.println("│                         z.뒤로 │");
		System.out.println("└────────────────────────────────┘");
		while (true) {
			String id = scn.nextLine();
			if (id.equals("z")) {
				MainMenu.mainMenu();
				break;
			} else {
				member.setId(id);
				if (!id.equals(dao.memberView(member).getId())) {
					System.out.println("올바른 ID를 입력해주세요 >>");
				} else
					break;
			}
		}
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│                                │");
		System.out.println("│            로 그 인            │");
		System.out.println("│ ────────────────────────────── │");
		System.out.println("│   비밀번호를 입력해 주세요 >>  │");
		System.out.println("│                                │");
		System.out.println("│                         z.뒤로 │");
		System.out.println("└────────────────────────────────┘");

		loop: while (true) {
			String password = scn.nextLine();
			if (password.equals("z")) { // 뒤로가기
				MainMenu.mainMenu();
				break;
			} else {
				for (int i = 0; i < 6; i++) {
					if (i < 5) {
						if (password.equals(dao.memberView(member).getPassword())) {
							my = member.getId(); // 비번일치(정상로그인)→로그인 성공한 id를 전역변수 my에 할당!!하고 빠져나옴
							break loop;
						} else {
							System.out.println("비밀번호가 불일치합니다. 다시 정확히 입력해주세요 >> ");
							password = scn.nextLine();
							if (password.equals("z")) { // 뒤로가기
								MainMenu.mainMenu();
							}
						}

					} else {
						System.out.print("비밀번호 입력 5회 오류! 5초 후 메인으로 돌아갑니다");
						try {
							for (int j = 0; j < 5; j++) {
								TimeUnit.SECONDS.sleep(1);
								System.out.print(".");
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println();
						MainMenu.mainMenu();
					}
				}
			}
		}
		// 로그인 성공 후 접속 시간 입력
		int n = dao.signIn(member);
		if (n != 0) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
			MainMenu.mainMenu();
		}
	}

//	
//	
//	
//	
//	
	// 로그아웃
	public void signOut() {
		dao.signOut(member);
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
	// 개인정보조회
	public void memberView() {
		System.out.println("\n─────[개인정보 조회 및 수정]─────");
		System.out.print("ID: " + member.getId());
		System.out.print("\n비밀번호입력 >> ");

		for (int i = 0; i < 6; i++) {
			if (i < 5) {
				String password = scn.nextLine();
				if (password.equals(dao.memberView(member).getPassword())) {
					dao.memberView(member).toString();
					while (true) {
						System.out.println("\n──────────────────────────────");
						System.out.print("1.개인정보 수정   2.홈메뉴복귀   >> ");
						int num = Integer.parseInt(scn.nextLine());
						switch (num) {
						case 1:
							memberUpdate();
							break;

						case 2:
							MainMenu.subMenu();
							break;
						}
					}
				} else {
					System.out.println("비밀번호가 불일치합니다. 다시 정확히 입력해주세요 >>");
				}
			} else {
				System.out.println("비밀번호 입력 5회 오류");
				MainMenu.mainMenu();
			}
		}
	}
//	
//	
//	
//	
//	
//	

	// 개인정보수정
	public void memberUpdate() {
		while (true) {
			System.out.println("\n─────[개인정보 수정]─────");
			System.out.print("1.연락처 수정   2.비밀번호 수정   3.조회   4.홈메뉴복귀   >> ");
			int num = Integer.parseInt(scn.nextLine());
			switch (num) {
			case 1:
				System.out.print("새 연락처 입력 >> ");
				member.setContact(scn.nextLine());
				member.setPassword(dao.memberView(member).getPassword());
				int n = dao.memberUpdate(member);
				if (n != 0) {
					System.out.println("수정 완료");
				} else {
					System.out.println("수정 실패");
				}
				break;

			case 2:
				System.out.print("새 비밀번호 입력 >> ");
				String password = scn.nextLine();
				System.out.print("비밀번호 재입력 >> ");
				while (true) {
					String password2 = scn.nextLine();
					if (password.equals(password2)) {
						member.setPassword(password);
						member.setContact(dao.memberView(member).getContact());
						break;
					} else {
						System.out.println("비밀번호가 불일치합니다. 다시 정확히 입력해주세요 >>");
					}
				}

				n = dao.memberUpdate(member);
				if (n != 0) {
					System.out.println("수정 완료");
				} else {
					System.out.println("수정 실패");
				}
				break;

			case 3:
				dao.memberView(member).toString();
				break;

			case 4:
				MainMenu.subMenu();
				break;
			}
		}

	}

}
