package com.globe_sh.cloudplatform.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;

public class StaticMethod {


	public static final byte PACK_DATA_INSIDE = 0x25;
	public static final byte PACK_DATA_OUTSIDE = 0x24;
	public static final int PACK_CONNECT_ID_LENGTH = 60;
	public static final int PACK_PACKAGE_CONTROL_LENGTH = 21;
	
	public static boolean isNull(String s) {
		if(s == null || "".equals(s))
			return true;
		
		return false;
	}
	
	public static boolean isNull(byte[] data) {
		if(data == null || data.length == 0)
			return true;
		
		return false;
	}
	
	public static boolean isNull(JSONArray array) {
		if(array == null || array.size() == 0)
			return true;
		
		return false;
	}
	
	@SuppressWarnings("rawtypes") 
	public static boolean isNull(List data) {
		if(data == null || data.size() == 0)
			return true;
		
		return false;
	}
	
	public static String ascii2String(byte[] data, int start, int length) {
		String s = "";
		int len = start + length;
		for(int i = start; i < len; i++) {
			byte b = data[i];
			char c = (char)b;
			s = s + c;
		}
		
		return s;
	}
	
	public static byte[] string2Ascii(String str) {
		byte[] data = null;
		try {
			data = str.getBytes("US-ASCII");
		} catch(Exception e) {
			
		}
		
		return data;
	}
	
	public static byte[] hexStringToByte(String hexString) {
		hexString = hexString.toLowerCase(); 
		final byte[] byteArray = new byte[hexString.length() >> 1]; 
		int index = 0; 
		for (int i = 0; i < hexString.length(); i++) { 
			if (index > hexString.length() - 1) 
				return byteArray; 
			byte highDit = (byte) (Character.digit(hexString.charAt(index), 16) & 0xFF); 
			byte lowDit = (byte) (Character.digit(hexString.charAt(index + 1), 16) & 0xFF); 
			byteArray[i] = (byte) (highDit << 4 | lowDit); index += 2; 
		} 
		
		return byteArray;
	}
	
	public static String bytesToHexString(byte[] src) {   
	    StringBuilder stringBuilder = new StringBuilder("");   
	    if (src == null || src.length == 0) {   
	        return null;   
	    }   
	    for (int i = 0; i < src.length; i++) {
	        int v = src[i] & 0xFF;   
	        String hv = Integer.toHexString(v);   
	        if (hv.length() < 2) {   
	            stringBuilder.append(0);   
	        }   
	        stringBuilder.append(hv);   
	    }   
	    return stringBuilder.toString();   
	}   
	
	public static String intToHexString(int value) {
		String str = "";
		str = Integer.toHexString(value);  
        if (str.length() < 2)    
        	str = "0x0" + str;
        else
        	str = "0x" + str;
		
		return str;
	}
	
	public static String getTimeString(byte[] data, int start) {
		String time = "";
		try {
			int yy = 2000 + data[start];
			time = time + yy;
			byte mm = data[start + 1];
			if(mm < 10)
				time = time + "-0" + mm;
			else
				time = time + "-" + mm;
			byte dd = data[start + 2];
			if(dd < 10)
				time = time + "-0" + dd;
			else
				time = time + "-" + dd;
			
			byte hh = data[start + 3];
			if(hh < 10)
				time = time + " 0" + hh;
			else
				time = time + " " + hh;
			byte mi = data[start + 4];
			if(mi < 10)
				time = time + ":0" + mi;
			else
				time = time + ":" + mi;
			byte ss = data[start + 5];
			if(ss < 10)
				time = time + ":0" + ss;
			else
				time = time + ":" + ss;
			short ms = ByteArrayUtil.getShortLowEnd(data,start+6);
			if(ms < 10)
				time = time + ".00" + ms;
			else if(ms < 100)
				time = time + ".0" + ms;
			else
				time = time + "." +ms;
			
			return time;
		} catch(Exception e) {
			
		}
		
		return null;
	}
	
	public static String getTimeString(Timestamp t) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String result = "";
		try {
			result = dateFormat.format(new Date(t.getTime()));
		} catch (Exception e) {
			result = "";
		}
		
		return result;
	}
	
	public static String getTimeString(int disday) {
		String ls_display = "";
		Calendar c;
		c = Calendar.getInstance();
		long realtime = c.getTimeInMillis();
		realtime += 86400000 * disday;
		c.setTimeInMillis(realtime);
		String _yystr = "", _mmstr = "", _ddstr = "", _hhstr = "", _mistr = "", _ssstr = "";
		int _yy = c.get(Calendar.YEAR);
		_yystr = _yy + "";
		int _mm = c.get(Calendar.MONTH) + 1;
		_mmstr = _mm + "";
		if (_mm < 10) {
			_mmstr = "0" + _mm;
		}
		int _dd = c.get(Calendar.DATE);
		_ddstr = _dd + "";
		if (_dd < 10) {
			_ddstr = "0" + _dd;
		}
		
		int _hh = c.get(Calendar.HOUR_OF_DAY);
		_hhstr = _hh + "";
		if (_hh < 10) {
			_hhstr = "0" + _hh;
		}

		int _mi = c.get(Calendar.MINUTE);
		_mistr = _mi + "";
		if (_mi < 10) {
			_mistr = "0" + _mi;
		}
		
		int _ss = c.get(Calendar.SECOND);
		_ssstr = _ss + "";
		if (_ss < 10) {
			_ssstr = "0" + _ss;
		}
		
		ls_display = _yystr + "-" + _mmstr + "-" + _ddstr + " " +
				_hhstr + ":" + _mistr + ":" + _ssstr + ".000";
		return ls_display;
	}
	
	public static byte[] getTimeBytes(int disday) {
		byte[] time = new byte[6];
		Calendar c;
		c = Calendar.getInstance();
		long realtime = c.getTimeInMillis();
		realtime += 86400000 * disday;
		c.setTimeInMillis(realtime);
		
		int _yy = c.get(Calendar.YEAR) - 2000;
		int _mm = c.get(Calendar.MONTH) + 1;
		int _dd = c.get(Calendar.DATE);
		int _hh = c.get(Calendar.HOUR_OF_DAY);
		int _mi = c.get(Calendar.MINUTE);
		int _ss = c.get(Calendar.SECOND);
		time[0] = (byte)_yy;
		time[1] = (byte)_mm;
		time[2] = (byte)_dd;
		time[3] = (byte)_hh;
		time[4] = (byte)_mi;
		time[5] = (byte)_ss;
		
		return time;
	}
	
	public static Timestamp getTimestamp() {
		long time = System.currentTimeMillis();
		Timestamp t = new Timestamp(time);
		
		return t;
	}
	
	public static Timestamp getTimestamp(String str) {
		Timestamp ret = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss.SSS");
			
			Date date = dateFormat.parse(str);
			long datelong = date.getTime();
			ret = new Timestamp(datelong);

		} catch (Exception e) {
		}
		return ret;
	}
	
	public static byte[] timeStampToBytes(Timestamp st) {
		byte[] ret = new byte[8];
		Calendar c;
		c = Calendar.getInstance();
		long realtime = st.getTime();
		c.setTimeInMillis(realtime);		
		try {
				int _yy = c.get(Calendar.YEAR) - 2000;
				int _mm = c.get(Calendar.MONTH) + 1;
				int _dd = c.get(Calendar.DATE);
				int _hh = c.get(Calendar.HOUR_OF_DAY);
				int _mi = c.get(Calendar.MINUTE);
				int _ss = c.get(Calendar.SECOND);
				int _ms = c.get(Calendar.MILLISECOND);
				
				ret[0] = (byte)_yy;
				ret[1] = (byte)_mm;
				ret[2] = (byte)_dd;
				ret[3] = (byte)_hh;
				ret[4] = (byte)_mi;
				ret[5] = (byte)_ss;
				ret[6] = (byte)((_ms >> 8) & 0xff);
				ret[7] = (byte)(_ms & 0xff);

		} catch (Exception e) {
		}
		return ret;
	}	

	public static Timestamp getTimestampOrigin(byte[] bytes, int start) {
		Timestamp ret = null;
		try {
			String newt = StaticMethod.getTimeString(bytes,start);
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		    Date parsedDate = dateFormat.parse(newt);
		    ret = new java.sql.Timestamp(parsedDate.getTime());
					
/*			ret = new Timestamp(bytes[start]+2000,bytes[start+1],bytes[start+2],
					bytes[start+3],bytes[start+4],bytes[start+5],
					ByteArrayUtil.getShortLowEnd(bytes,start+6)*1000000 );
*/					

			} catch (Exception e) {
		}
		return ret;
	}
	
	public static synchronized String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	public static byte[] Concat(byte[]...arrays)
	{
	    // Determine the length of the result array
	    int totalLength = 0;
	    for (int i = 0; i < arrays.length; i++)
	    {
	        totalLength += arrays[i].length;
	    }

	    // create the result array
	    byte[] result = new byte[totalLength];

	    // copy the source arrays into the result array
	    int currentIndex = 0;
	    for (int i = 0; i < arrays.length; i++)
	    {
	        System.arraycopy(arrays[i], 0, result, currentIndex, arrays[i].length);
	        currentIndex += arrays[i].length;
	    }

	    return result;
	}	
	
    public static byte[] floatToByteArray(float value) {
        int intBits =  Float.floatToIntBits(value);
        return new byte[] {
          (byte) (intBits >> 24), (byte) (intBits >> 16), (byte) (intBits >> 8), (byte) (intBits) };
    }   
    public static float byteArrayToFloat(byte[] bytes) {
        int intBits = 
          bytes[0] << 24 | (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8 | (bytes[3] & 0xFF);
        return Float.intBitsToFloat(intBits);  
    }    	
    public static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }    
}
