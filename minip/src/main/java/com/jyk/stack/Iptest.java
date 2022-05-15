package com.jyk.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Iptest {
	public static void main(String[] args) {

		String cmd = "nslookup myip.opendns.com resolver1.opendns.com";
		Process process;
		try {
			process = Runtime.getRuntime().exec("cmd /c " + cmd);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "MS949"));
			String line = null;
			StringBuffer sb = new StringBuffer();
			sb.append(cmd);
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String nb = sb.substring(sb.length()-15, sb.length());
			System.out.println(nb.replaceAll("\\s|:",""));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

