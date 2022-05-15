package com.jyk.starbucks.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.sound.midi.Sequence;

import com.jyk.starbucks.app.MainMenu;

import lombok.Data;

@Data
public class MembershipInfo {
	String id;
	String password;
	String email;
	String contact;
	String secq;
	String seca;
	Timestamp signupdate;
	Timestamp signinthis;
	Timestamp signinlast;
	String tier;
	int star;
	int coupon;

	// 보안질문 리스트
	public static String[] secqlist() {
		String secqlist[] = new String[18]; // 항목 추가,삭제시 배열 크기도 수정(Out of Bound예외 주의!)
		secqlist[0] = "1. 10대 시절에 가장 친하게 지냈던 친구의 이름은 무엇입니까?";
		secqlist[1] = "2. 첫 애완동물의 이름은 무엇입니까?";
		secqlist[2] = "3. 영화관에서 처음으로 관람한 영화는 무엇입니까?";
		secqlist[3] = "4. 처음으로 비행기를 타고 방문한 곳은 어디입니까?";
		secqlist[4] = "5. 초등학교 또는 중학교 시절 가장 좋아했던 선생님의 성함은 무엇입니까?";
		secqlist[5] = "6. 꿈의 직업은 무엇입니까?";
		secqlist[6] = "7. 가장 좋아했던 동화책의 제목은 무엇입니까?";
		secqlist[7] = "8. 처음으로 소유했던 자동차의 모델명은 무엇입니까?";
		secqlist[8] = "9. 어린 시절의 별명은 무엇입니까?";
		secqlist[9] = "10. 학창 시절 가장 좋아했던 영화 배우 또는 영화 속 캐릭터는 누구입니까?";
		secqlist[10] = "11. 학창 시절 가장 좋아했던 밴드 또는 가수는 누구입니까?";
		secqlist[11] = "12. 부모님이 처음 만난 도시는 어디입니까?";
		secqlist[12] = "13. 첫 직장 상사의 이름은 무엇입니까?";
		secqlist[13] = "14. 어린 시절을 보낸 동네 이름은 무엇입니까?";
		secqlist[14] = "15. 처음으로 가보았던 해변의 이름은 무엇입니까? ";
		secqlist[15] = "16. 처음으로 구매한 음반 또는 CD는 무엇입니까?";
		secqlist[16] = "17. 가장 좋아하는 스포츠 팀의 이름은 무엇입니까?";
		secqlist[17] = "18. 처음 배운 요리는 무엇입니까?";
		return secqlist;
	}

//	
//	
	// 보안질문 리스트 출력 양식
	public static String secqlistString() {
		for (int s = 0; s < secqlist().length; s++) {
			System.out.print("│ " + secqlist()[s]);
			MainMenu.korPrint(72, secqlist()[s]);
			System.out.println(" │");
		}
		return null;
	}

//	
//	
//	
//	
	// 세션ID 출력 양식
	public void idString() {
		System.out.print("│   ID: " + id);
		MainMenu.korPrint(30, id);
		System.out.println(" │");
	}

//
//
//
//	
	// 회원정보 출력 양식
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd (E) HH:mm:ss");
		//
		System.out.print("│      아  이  디 : " + id);
		MainMenu.korPrint(30, id);
		System.out.println(" │");
		//
		System.out.println("│   1. 비 밀 번 호: ***                            │");
		//
		System.out.print("│   2. 이  메  일 : " + email);
		MainMenu.korPrint(30, email);
		System.out.println(" │");
		//
		if (contact.length() == 11) {
			System.out.print("│   3. 연  락  처 : " + contact.substring(0, 3) + "-" + contact.substring(3, 7) + "-"
					+ contact.substring(7, 11));
			System.out.println("                  │");
		} else {
			System.out.print("│   3. 연  락  처 : " + contact.substring(0, 3) + "-" + contact.substring(3, 6) + "-"
					+ contact.substring(6, 10));
			System.out.println("                   │");
		}
		//
		System.out.print("│      가  입  일 : " + sdf.format(signupdate));
		System.out.println("       │");
		//
		System.out.print("│      현재 로그인: " + sdf.format(signinthis));
		System.out.println("       │");
		//
		System.out.print("│      최종 로그인: " + sdf.format(signinlast));
		System.out.println("       │");
		//
		return null;
	}

}
