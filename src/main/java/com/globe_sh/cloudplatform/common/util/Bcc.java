package com.globe_sh.cloudplatform.common.util;

public class Bcc {

	public static byte getBcc(byte[] data) {
		byte ret = data[0];
		
		for (int i = 1; i <data.length; i++) {
			ret ^= data[i];
		}
		
		return ret;
	}
	
	public static byte getBccWithoutEnd(byte[] data) {
		byte ret = data[0];
		int len = data.length - 1;
		for (int i = 1; i < len; i++) {
			ret ^= data[i];
		}
		
		
		return ret;
	}
	
	public static byte getBcc(byte[] data, int start, int length) {
		byte ret = data[start];
		int len = start + length;
		for(int i = (start + 1); i < len; i++) {
			ret ^= data[i];
		}		
		
		return ret;
	}
}
