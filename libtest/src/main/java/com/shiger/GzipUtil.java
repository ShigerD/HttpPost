package com.shiger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 
 * 
 * @author wu yibo
 */
public class GzipUtil {

	public static byte[] compress(byte[] bytes) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] result;
		try {
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			gzip.write(bytes);
			gzip.close();
			result = out.toByteArray();
		} finally {
			out.close();
		}
		return result;
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 * @throws IOException
	 * 
	 * @author wu yibo
	 */
	public static byte[] unCompress(byte[] bytes) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		GZIPInputStream gzip = null;
		byte[] result;
		try {
			gzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n = 0;
			while ((n = gzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			result = out.toByteArray();
		} finally {
			if (gzip != null) {
				gzip.close();
			}
			out.close();
			in.close();
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		String a = "aaaaaaaaaaa123456789";
		byte[] zip = compress(a.getBytes());
		System.out.println(new String(unCompress(zip)));
	}
}
