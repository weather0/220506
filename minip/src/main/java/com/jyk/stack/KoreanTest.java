package com.jyk.stack;

public class KoreanTest {
	public static void main(String[] args) {
		String s = "욺저ㅣㅏ넘ㅋㅍ.ㅌ123:;ㅋㄴㅊ,프awem	 	f3fjd프ㅏㅣㅁ즏ㄼㄹ 	dfsm ㅓㅈㄷㄹ   	 9ㅁ0저,r름ㅇ자러3ㅈ     ㄹwaef m:";

		System.out.println(s.replaceAll("[ㄱ-ㅎㅏ-ㅣ가-힣]", ""));

	}

}
