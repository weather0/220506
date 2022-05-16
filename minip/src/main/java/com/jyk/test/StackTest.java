package com.jyk.test;

import java.util.Scanner;
import java.util.Stack;

import com.jyk.starbucks.app.MainMenu;

public class StackTest {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		Stack<Integer> st = new Stack();
		int stn = 0;

		// 뒤로가기 로직
		st.push(0);
		while (true) {
			switch (stn) {
			case 0:
				// 예외적으로 case0과 case1의 관계만 한묶음이라고 이해할 것
				// 하단 주석처리한 부분들 기본 양식이니깐 실전에서 켜서 활용할 것
				System.out.println("뒤로가기0 / 번호선택");
				st.push(1);

			case 1:
//				pick = scn.nextLine();
				if (scn.nextLine().equals("z")) {
					// 첫 case이므로 아예 다른 메소드로 넘김 처리
//					MainMenu.cardMenu();
				} else {
					// 스캐너 예외 처리 양식(while-try-if-else-catch)

					// 여기에 구현할 컨텐츠 코드 작성

				System.out.println("뒤로가기1 / 번호선택");
					if (scn.nextLine().equals("z")) {
						st.pop();
						stn = st.lastElement();
						break;
					} else {
					st.push(2);
					}
				}

			case 2:
				System.out.println("뒤로가기2 / 번호선택");
//				pick = scn.nextLine();
//				int intpick2 = Integer.parseInt(pick);
				if (scn.nextLine().equals("z")) {
					st.pop();
					stn = st.lastElement();
					break;
				} else {
					// 스캐너 예외 처리 양식(while-try-if-intpick2-else-catch)
					// 여기에 구현할 컨텐츠 코드 작성
					st.push(3);
				}

			case 3:
				System.out.println("뒤로가기3 / 번호선택");
//				pick = scn.nextLine();
//				int intpick3 = Integer.parseInt(pick);
				if (scn.nextLine().equals("z")) {
					st.pop();
					stn = st.lastElement();
					break;
				} else {
					// 스캐너 예외 처리 양식(while-try-if-intpick3-else-catch)
					// 여기에 구현할 컨텐츠 코드 작성
					st.push(4);
				}

			case 4:
				System.out.println("뒤로가기4 / 번호선택");
//				pick = scn.nextLine();
//				int intpick4 = Integer.parseInt(pick);
				if (scn.nextLine().equals("z")) {
					st.pop();
					stn = st.lastElement();
					break;
				} else {
					// 스캐너 예외 처리 양식(while-try-if-intpick4-else-catch)
					// 여기에 구현할 컨텐츠 코드 작성
					st.push(5);
				}

			case 5:
				System.out.println("뒤로가기5 / 번호선택");
//				pick = scn.nextLine();
//				int intpick5 = Integer.parseInt(pick);
				if (scn.nextLine().equals("z")) {
					st.pop();
					stn = st.lastElement();
					break;
				} else {
					// 스캐너 예외 처리 양식(while-try-if-intpick5-else-catch)
					// 여기에 구현할 컨텐츠 코드 작성
				}

			}

		}

//		break; // case마다 break의 위치 설정이 키포인트!! 그래야 case시작점부터 안깨고 다음 코드로 넘어갈수 있다.
		// case에서의 break는 사실 case의 사용을 유의미하게 하는 필수 요소이다. break를 걸어야 선택된 케이스"만" 실행됨.
		// 쉽게생각해서 case n의 break를 없앤다 = 'case n+1'코드 줄 자체를 없애는 것과 같은 의미.

//─────────────────────────────────────────────────
//		s.push(1); //0번째
//		s.push(99); //1번째
//		s.push(100); //2번째
////		s.push("a"); //3번째
////		s.push("b"); //4번째
//		s.set(2,9);//2번째값을 9로 변경
////		s.pop();
//		
//		
//		
//		System.out.println(s.size()); //스택크기
//		System.out.println(s.lastElement()); //막인덱스의 값
//		System.out.println(s.peek()); //top에있는 값. lastElement와 뭔차이?(구글안나옴)
//		System.out.println(s.elementAt(1)); //특정 인덱스
////		System.out.println(s.);
//
//		
//		if (s.elementAt(1)==99) {
//			System.out.println("y");
//		}else {
//			System.out.println("N");
//		}

//		s.push(2);
//		s.push(3);
//		s.push(4);
//		s.push(5);

	}

}
