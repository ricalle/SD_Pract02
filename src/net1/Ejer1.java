package net1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Ejer1 {
	public static void main(String[] args) {
		System.out.println("Introduce una IP o dominio web: ");
		Scanner s = new Scanner(System.in);
		String host = s.nextLine();
		try {
			InetAddress[] iads = InetAddress.getAllByName(host);
			if(isIP(host)) {
				System.out.println("Has intoducido IP, hosts asociados son:");
				for(InetAddress ia : iads) {
					System.out.println(ia.getHostName());
				}
			}else {
				System.out.println("Has intoducido dominio, IP asociadas son:");
				for(InetAddress ia : iads) {
					System.out.println(ia.getHostAddress());
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		s.close();
	}
	
	public static boolean isIP(String ip) {
		String [] partes = ip.split("\\.");
		if(partes.length != 4) return false;
		try {
			for(String part : partes) {
				int parseInt = Integer.parseInt(part);
				if (parseInt < 0 || parseInt > 255) {
					return false;
				}
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return true;
	}
}
