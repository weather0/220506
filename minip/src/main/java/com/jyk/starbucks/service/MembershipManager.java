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
	String pw;

	// 회원가입
	public void signUp() {
		System.out.println();
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│                                │");
		System.out.println("│          멤버십 가입           │");
		System.out.println("│ ────────────────────────────── │");
		System.out.println("│      ID를 입력해 주세요 >>     │");
		System.out.println("│                                │");
		System.out.println("│                     z.메인으로 │");
		System.out.println("└────────────────────────────────┘");

		while (true) {
			String id = scn.nextLine();
			if (id.equals("z")) { // 뒤로가기
				MainMenu.mainMenu();
				continue; // 혹시나 메서드 호출이 안됐을 시
			}
			if (Pattern.matches("^[a-zA-Z0-9]{3,20}$", id)) {
				member.setId(id); // 입력한 id를 일단 member의 id필드에 세팅
				if (!id.equals(dao.memberView(member).getId())) {
					// 않다(!). 입력값이(id). 같지(equals)(같은 id를 가진 레코드가 DB테이블에서 이미 존재하여 memberView가 정상
					// 실행되고 getID한 것과)
					break; // 그대로 빠져나와라
				} else {
					System.out.println("이미 가입된 ID입니다. 다시 입력해주세요 >>");
					continue;
				}

			} else {
				System.out.println("다시 입력해 주세요 (영문자, 숫자 3~20자 이내) >> ");
			}
		}
//		
//		
		// 비밀번호 설정
		System.out.println("┌──────────────────────────────────────────────────┐");
		System.out.println("│                                                  │");
		System.out.println("│                   멤버십 가입                    │");
		System.out.println("│ ──────────────────────────────────────────────── │");
		System.out.println("│            비밀번호를 입력해 주세요 >>           │");
		System.out.println("│                                                  │");
		System.out.println("│ (영문자, 숫자, 특수문자 반드시 포함 8~20자 이내, │");
		System.out.println("│ 특수문자는 ~ ! @ # $ % ^ & + = - 만 가능합니다)  │");
		System.out.println("│                                                  │");
		System.out.println("│                                       z.메인으로 │");
		System.out.println("└──────────────────────────────────────────────────┘");

		pass: while (true) {
			String password = scn.nextLine();
			if (password.equals("z")) { // 뒤로가기
				MainMenu.mainMenu();
				continue;
			}
			if (Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&+=-])[a-zA-Z\\d~!@#$%^&+=-]{8,20}$", password)) {
				System.out.println("똑같이 한 번 더 입력해주세요 >> ");
				for (int i = 0; i < 6; i++) {
					if (i < 5) {
						String password2 = scn.nextLine();
						if (password2.equals("z")) { // 뒤로가기
							MainMenu.mainMenu();
						}
						if (password.equals(password2)) {
							member.setPassword(password);
							break pass; // 이중 반복문 break
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
			} else {
				System.out.println(" 규칙에 맞게 다시 입력해 주세요 >> ");
				System.out.println("(영문자, 숫자, 특수문자 반드시 포함 8~20자 이내,");
				System.out.println(" 특수문자는 ~ ! @ # $ % ^ & + = - 만 가능합니다)");
			}
		}

//		
//		
		// 보안질문 설정
		System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
		System.out.println("│                                                                          │");
		System.out.println("│                               멤버십 가입                                │");
		System.out.println("│ ──────────────────────────────────────────────────────────────────────── │");
		System.out.println("│                       보안 질문을 선택해 주세요 >>                       │");
		System.out.println("│                                                                          │");
		System.out.println("│                (이 질문은 암호 분실 시 신원을 확인하고                   │");
		System.out.println("│                 암호를 복구하는 데 사용됩니다)                           │");
		System.out.println("│                                                                          │");
		MembershipInfo.secqlistString();
		System.out.println("│                                                                          │");
		System.out.println("│                                                               z.메인으로 │");
		System.out.println("└──────────────────────────────────────────────────────────────────────────┘");

		String secQ = scn.nextLine();
		sec: while (true) {
			try {
				if (secQ.equals("z")) { // 뒤로가기
					MainMenu.mainMenu();
					continue;
				}
				int intsecQ = Integer.parseInt(secQ);
				if (intsecQ <= 0 || intsecQ > MembershipInfo.secqlist().length) {
					System.out.println("정확한 번호를 선택해 주세요 >> ");
					secQ = scn.nextLine();
					continue;
				} else {
					while (true) {
						System.out.println(MembershipInfo.secqlist()[intsecQ - 1]);
						System.out.println("답문을 작성해 주세요 >> ");
						String secA = scn.nextLine();
						if (secA.equals("z")) { // 뒤로가기
							MainMenu.mainMenu();
							continue;
						}
						if (secA.equals("")) { // 미입력 검증
							continue;
						}
						member.setSecq(MembershipInfo.secqlist()[intsecQ - 1]);
						member.setSeca(secA); // 보안질문,답변 확정
						break sec;
					}
				}
			} catch (Exception e) {
				System.out.println("번호만 입력해 주세요 >> ");
				secQ = scn.nextLine();
			}
		}
//		
//		
		// 이메일 입력
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│                                │");
		System.out.println("│          멤버십 가입           │");
		System.out.println("│ ────────────────────────────── │");
		System.out.println("│    이메일을 입력해 주세요 >>   │");
		System.out.println("│                                │");
		System.out.println("│                     z.메인으로 │");
		System.out.println("└────────────────────────────────┘");
		while (true) {
			String email = scn.nextLine();
			if (email.equals("z")) {
				MainMenu.mainMenu();
			}
			if (Pattern.matches("^[_a-zA-Z0-9-\\.]+@[_a-zA-Z0-9-\\.]+\\.[_a-zA-Z0-9-\\.]+$$", email)) {
				member.setEmail(email);
				break;
			} else {
				System.out.println("유효하지 않은 이메일 양식입니다. 다시 입력해 주세요 >> ");
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
		System.out.println("│                         z.메인으로 │");
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
					System.out.println("└────────────────────────────────────┘");
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
//	
//	
//	
//	
//	
//	

	// 로그인
	public void signIn() {
		System.out.println("┌────────────────────────────────┐");
		System.out.println("│                                │");
		System.out.println("│            로 그 인            │");
		System.out.println("│ ────────────────────────────── │");
		System.out.println("│      ID를 입력해 주세요 >>     │");
		System.out.println("│                                │");
		System.out.println("│ i.ID찾기  p.PW찾기  z.메인으로 │");
		System.out.println("└────────────────────────────────┘");
		while (true) {
			String login = scn.nextLine();
			if (login.equals("z")) { // 뒤로가기
				MainMenu.mainMenu();
				continue;
			}
			if (login.equals("i")) { // ID찾기로 이동
				findID();
				continue;
			}
			if (login.equals("p")) { // PW찾기로 이동
				findPW();
				continue;
			} else {
				member.setId(login);
				if (!login.equals(dao.memberView(member).getId())) {
					System.out.println("등록되지 않은 ID입니다. 다시 입력해주세요 >> ");
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

		String password = scn.nextLine();
		if (password.equals("z")) { // 뒤로가기
			signIn();
		}
		for (int i = 0; i < 6; i++) {
			if (i < 5) {
				if (password.equals(dao.memberView(member).getPassword())) {
					my = member.getId(); // 비번일치(정상로그인)→로그인 성공한 id를 전역변수 my에 할당!!하고 빠져나옴
					break;
				} else {
					System.out.println("비밀번호가 불일치합니다. 다시 정확히 입력해주세요 >> ");
					password = scn.nextLine();
					if (password.equals("z")) { // 뒤로가기
						signIn();
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

		// 로그인 성공 후 접속 시간 입력
		int n = dao.signIn(member);
		if (n != 0) {
//			System.out.println("로그인 성공");
			MainMenu.subMenu();
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
//	
//	
//	
	// ID 찾기
	public void findID() {
		System.out.println("┌──────────────────────────────────────────┐");
		System.out.println("│                                          │");
		System.out.println("│                  ID 찾기                 │");
		System.out.println("│ ──────────────────────────────────────── │");
		System.out.println("│ 가입 시 등록한 이메일을 입력해 주세요 >> │");
		System.out.println("│                                          │");
		System.out.println("│                                   z.뒤로 │");
		System.out.println("└──────────────────────────────────────────┘");
		String email = null;
		email = scn.nextLine();
		if (email.equals("z")) { // 뒤로가기
			signIn();
		}
		System.out.println("┌──────────────────────────────────────────┐");
		System.out.println("│                                          │");
		System.out.println("│                  ID 찾기                 │");
		System.out.println("│ ──────────────────────────────────────── │");
		System.out.println("│ 가입 시 등록한 연락처를 입력해 주세요 >> │");
		System.out.println("│                                          │");
		System.out.println("│                                   z.뒤로 │");
		System.out.println("└──────────────────────────────────────────┘");
		String contact = null;
		contact = scn.nextLine();
		if (contact.equals("z")) { // 뒤로가기
			signIn();
		}
		String fi[] = dao.findID(email, contact);
		if (fi[0] != null && fi[1] != null) {
			if (fi[0].equals(fi[1])) {
				System.out.println("고객님의 ID는 " + fi[0] + " 입니다.");
				System.out.println("아무 키나 눌러 주세요 (로그인 화면으로 이동) >> ");
			} else {
				System.out.println("입력하신 이메일과 연락처로 등록된 ID가 각각 상이합니다.");
				System.out.println("고객센터로 문의 바랍니다.");
				System.out.println();
				System.out.println("아무 키나 눌러 주세요 (로그인 화면으로 이동) >> ");
			}
		} else if (fi[0] != null) {
			System.out.println("이메일은 존재하나, 등록되지 않은 연락처입니다. ");
			System.out.println("아무 키나 눌러 주세요 (로그인 화면으로 이동) >> ");
		} else if (fi[1] != null) {
			System.out.println("연락처는 존재하나, 등록되지 않은 이메일입니다. ");
			System.out.println("아무 키나 눌러 주세요 (로그인 화면으로 이동) >> ");
		} else {
			System.out.println("등록되지 않은 이메일과 연락처입니다. ");
			System.out.println("아무 키나 눌러 주세요 (로그인 화면으로 이동) >> ");
		}
		scn.nextLine();
		signIn();
	}

//	
//	
//	
//	
//	
//	
//	
	// PW 찾기
	public void findPW() {
		System.out.println("┌──────────────────────────────────────────┐");
		System.out.println("│                                          │");
		System.out.println("│               비밀번호 찾기              │");
		System.out.println("│ ──────────────────────────────────────── │");
		System.out.println("│   가입 시 등록한 ID를 입력해 주세요 >>   │");
		System.out.println("│                                          │");
		System.out.println("│                                   z.뒤로 │");
		System.out.println("└──────────────────────────────────────────┘");
		String id = null;
		id = scn.nextLine();
		while (true) {
			if (id.equals("z")) { // 뒤로가기
				signIn();
			}
			String fp[] = dao.findPW(id);
			if (fp[0] != null) { // 정상 등록된 ID일 경우
				System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
				System.out.println("│                                                                          │");
				System.out.println("│                              비밀번호 찾기                               │");
				System.out.println("│ ──────────────────────────────────────────────────────────────────────── │");
				System.out.println("│                가입 시 설정한 보안 질문을 선택해 주세요 >>               │");
				System.out.println("│                                                                          │");
				MembershipInfo.secqlistString();
				System.out.println("│                                                                          │");
				System.out.println("│                                                                   z.뒤로 │");
				System.out.println("└──────────────────────────────────────────────────────────────────────────┘");

				String secq = scn.nextLine();
				while (true) {
					try {
						if (secq.equals("z")) { // 뒤로가기
							signIn();
							continue;
						}
						int intsecq = Integer.parseInt(secq);
						if (intsecq <= 0 || intsecq > MembershipInfo.secqlist().length) {
							System.out.println("정확한 번호를 선택해 주세요 >> ");
							secq = scn.nextLine();
							continue;
						} else {
							String mysecq = MembershipInfo.secqlist()[intsecq - 1];
							System.out.println(mysecq);
							System.out.println("가입 시 설정한 보안 답변을 입력해 주세요 >> ");
							String seca = scn.nextLine();
							if (seca.equals("z")) { // 뒤로가기
								signIn();
								continue;
							}
							// 나의 질답세트와 검증대상 ID의 질답세트가 정확히일치하면
							if (mysecq.equals(fp[1]) && seca.equals(fp[2])) {
								System.out.println("고객님의 비밀번호는 " + fp[0] + " 입니다.");
								System.out.println("아무 키나 눌러 주세요 (로그인 화면으로 이동) >> ");
								scn.nextLine();
								signIn();
							} else {
								// 보안 질문 때려맞추기 방지를 위해 질문만 일치 여부 따로 안알랴줌
								System.out.println("보안 질문 또는 답변이 일치하지 않습니다.");
								System.out.println("보안 질문 번호를 다시 선택해 주세요 >> ");
								secq = scn.nextLine();
								System.out.println();
								continue;
							}
						}
					} catch (Exception e) {
						System.out.println("번호만 입력해 주세요 >> ");
						secq = scn.nextLine();
					}
				}
			} else { // 미등록 ID일 경우
				System.out.println("등록되지 않은 ID입니다. 다시 입력해주세요 >> ");
				id = scn.nextLine();
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
		System.out.println();
		System.out.println("┌──────────────────────────────────────┐");
		System.out.println("│                                      │");
		System.out.println("│        개인정보 조회 및 수정         │");
		System.out.println("│ ──────────────────────────────────── │");
		System.out.println("│                                      │");
		member.idString();
		System.out.println("│   비밀번호를 입력해 주세요 >>        │");
		System.out.println("│                                      │");
		System.out.println("│                               z.뒤로 │");
		System.out.println("└──────────────────────────────────────┘");

		String password = scn.nextLine();
		if (password.equals("z")) { // 뒤로가기
			MainMenu.subMenu();
		}
		pw = password; // 전역변수pw에 지역변수password 할당(나중에 뒤로가기할 때 분기점으로 바로 가도록)
		memberView2(password);
	}

	// 조회화면 분기점
	public void memberView2(String pw) {
		for (int i = 0; i < 6; i++) {
			if (i < 5) {
				if (pw.equals(dao.memberView(member).getPassword())) {
					System.out.println("┌──────────────────────────────────────────────────┐");
					System.out.println("│                                                  │");
					System.out.println("│              개인정보 조회 및 수정               │");
					System.out.println("│ ──────────────────────────────────────────────── │");
					System.out.println("│                                                  │");
					dao.memberView(member).toString();
					System.out.println("│                                                  │");
					System.out.println("│   수정을 원하시는 항목을 선택해 주세요 >>        │");
					System.out.println("│                                                  │");
					System.out.println("│                                           z.뒤로 │");
					System.out.println("└──────────────────────────────────────────────────┘");
					while (true) {
						String mod = scn.nextLine();
						if (mod.equals("z")) { // 뒤로가기
							MainMenu.subMenu();
						}
						if (mod.equals("1") || mod.equals("2") || mod.equals("3")) {
							memberUpdate(mod);
						} else {
							System.out.println("정확한 번호를 입력해 주세요 >> ");
						}
					}
				} else {
					System.out.println("비밀번호가 불일치합니다. 다시 정확히 입력해주세요 >> ");
					pw = scn.nextLine();
					if (pw.equals("z")) { // 뒤로가기
						MainMenu.subMenu();
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
//	
//	
//	
//	
//
//	
//	
//	

	// 개인정보수정
	public void memberUpdate(String mod) {
		switch (mod) {
//		
//		
//		
		// 비밀번호 변경
		case "1":
			System.out.println();
			System.out.println("┌────────────────────────────────────┐");
			System.out.println("│                                    │");
			System.out.println("│           비밀번호 변경            │");
			System.out.println("│ ────────────────────────────────── │");
			System.out.println("│   새 비밀번호를 입력해 주세요 >>   │");
			System.out.println("│                                    │");
			System.out.println("│                             z.뒤로 │");
			System.out.println("└────────────────────────────────────┘");

			pw: while (true) {
				String password = scn.nextLine();
				if (password.equals("z")) { // 뒤로가기
					memberView2(pw); // 전역변수pw (여기 진입전에 위에서 이미 값을 넣어놓았다)
				}
				if (password.equals(pw)) {
					System.out.println("비밀번호가 이전과 동일합니다. 다시 입력해 주세요 >> ");

				} else if (Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&+=-])[a-zA-Z\\d~!@#$%^&+=-]{8,20}$",
						password)) {
					System.out.println("똑같이 한 번 더 입력해주세요 >> ");
					for (int i = 0; i < 6; i++) {
						if (i < 5) {
							String password2 = scn.nextLine();
							if (password2.equals("z")) { // 뒤로가기
								memberView2(pw);
							}
							if (password.equals(password2)) {
								member.setPassword(password);
								pw = password; // 전역변수 재설정
								break pw; // 이중 반복문 break
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
				} else {
					System.out.println(" 규칙에 맞게 다시 입력해 주세요 >> ");
					System.out.println("(영문자, 숫자, 특수문자 반드시 포함 8~20자 이내,");
					System.out.println(" 특수문자는 ~ ! @ # $ % ^ & + = - 만 가능합니다)");
				}
			}

			int n = dao.memberUpdate(member, mod);
			if (n != 0) {
//				System.out.println("변경이 완료되었습니다");
			} else {
				System.out.println("수정 실패");
			}
			System.out.println();
			System.out.println("┌────────────────────────────────────┐");
			System.out.println("│                                    │");
			System.out.println("│   비밀번호 변경이 완료되었습니다   │");
			System.out.println("│ ────────────────────────────────── │");
			System.out.println("│   보안 질문도 변경을 원하시면      │");
			System.out.println("│   y키를 눌러주세요 >>              │");
			System.out.println("│                                    │");
			System.out.println("│                             z.뒤로 │");
			System.out.println("└────────────────────────────────────┘");

			String yes = scn.nextLine();
			while (true) {
				if (yes.equals("z")) { // 뒤로가기
					memberView2(pw);
				} else if (yes.equals("y")) {
					break;
				} else {
					System.out.println("올바른 키를 입력해주세요 >> ");
					yes = scn.nextLine();
				}
			}

			// 보안질문 설정
			System.out.println("┌──────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│                                                                          │");
			System.out.println("│                             보안질문 변경                                │");
			System.out.println("│ ──────────────────────────────────────────────────────────────────────── │");
			System.out.println("│                       보안 질문을 선택해 주세요 >>                       │");
			System.out.println("│                                                                          │");
			System.out.println("│                (이 질문은 암호 분실 시 신원을 확인하고                   │");
			System.out.println("│                 암호를 복구하는 데 사용됩니다)                           │");
			System.out.println("│                                                                          │");
			MembershipInfo.secqlistString();
			System.out.println("│                                                                          │");
			System.out.println("│                                                                   z.뒤로 │");
			System.out.println("└──────────────────────────────────────────────────────────────────────────┘");

			String secQ = scn.nextLine();
			sec: while (true) {
				try {
					if (secQ.equals("z")) { // 뒤로가기
						memberView2(pw);
						continue;
					}
					int intsecQ = Integer.parseInt(secQ);
					if (intsecQ <= 0 || intsecQ > MembershipInfo.secqlist().length) {
						System.out.println("정확한 번호를 선택해 주세요 >> ");
						secQ = scn.nextLine();
						continue;
					} else {
						while (true) {
							System.out.println(MembershipInfo.secqlist()[intsecQ - 1]);
							System.out.println("답문을 작성해 주세요 >> ");
							String secA = scn.nextLine();
							if (secA.equals("z")) { // 뒤로가기
								memberView2(pw);
								continue;
							}
							if (secA.equals("")) { // 미입력 검증
								continue;
							}
							member.setSecq(MembershipInfo.secqlist()[intsecQ - 1]);
							member.setSeca(secA); // 보안질문,답변 확정
							break sec;
						}
					}
				} catch (Exception e) {
					System.out.println("번호만 입력해 주세요 >> ");
					secQ = scn.nextLine();
				}
			}
			n = dao.memberUpdate(member, yes);
			if (n != 0) {
				System.out.println("변경이 완료되었습니다");
			} else {
				System.out.println("수정 실패");
			}
			break; // case1 break;
//			
//			
//			
		// 이메일 변경
		case "2":
			System.out.println();
			System.out.println("┌────────────────────────────────────┐");
			System.out.println("│                                    │");
			System.out.println("│            이메일 변경             │");
			System.out.println("│ ────────────────────────────────── │");
			System.out.println("│    새 이메일을 입력해 주세요 >>    │");
			System.out.println("│                                    │");
			System.out.println("│                             z.뒤로 │");
			System.out.println("└────────────────────────────────────┘");
			while (true) {
				String email = scn.nextLine();
				if (email.equals("z")) {
					memberView2(pw);
				}
				if (Pattern.matches("^[_a-zA-Z0-9-\\.]+@[_a-zA-Z0-9-\\.]+\\.[_a-zA-Z0-9-\\.]+$$", email)) {
					member.setEmail(email);
					break;
				} else {
					System.out.println("유효하지 않은 이메일 양식입니다. 다시 입력해 주세요 >> ");
				}
			}
			n = dao.memberUpdate(member, mod);
			if (n != 0) {
				System.out.println("변경이 완료되었습니다");
			} else {
				System.out.println("수정 실패");
			}
			break; // case2 break;

//		
//		
//		
		// 연락처 변경
		case "3":
			System.out.println();
			System.out.println("┌────────────────────────────────────┐");
			System.out.println("│                                    │");
			System.out.println("│            연락처 변경             │");
			System.out.println("│ ────────────────────────────────── │");
			System.out.println("│    새 연락처를 입력해 주세요 >>    │");
			System.out.println("│                                    │");
			System.out.println("│                             z.뒤로 │");
			System.out.println("└────────────────────────────────────┘");
			while (true) {
				String contact = scn.nextLine();
				if (contact.equals("z")) {
					memberView2(pw);
				}
				if (Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", contact)) {
					member.setContact(contact);
					break;
				} else {
					System.out.println("유효하지 않은 휴대폰 번호입니다. 다시 입력해 주세요 >> ");
				}
			}
			n = dao.memberUpdate(member, mod);
			if (n != 0) {
				System.out.println("변경이 완료되었습니다");
			} else {
				System.out.println("수정 실패");
			}
			break; // case3 break;
		}
		memberView2(pw);
	}

}
