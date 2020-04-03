package com.globe_sh.cloudplatform.common.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtil {

	public static byte getFileCrc8(String file) {
		byte crc8 = 0;
		try {
			byte[] data = toByteArray(file);
			crc8 = CRC8.calcCrc8(data);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return crc8;
	}

    public static byte[] toByteArray(String file) {
    	byte[] data = null;
        File f = new File(file);
        if (!f.exists()) {
            return data;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            data = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
