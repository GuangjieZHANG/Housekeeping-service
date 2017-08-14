package com.manage.util;

import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;

public class DESPlus {

	public static String encrypt(String strKey, String strIn) throws Exception {
		strKey = "1q2w3e4r";
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey);

		Cipher encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		return byteArr2HexStr(encryptCipher.doFinal((strIn.getBytes())));
	}

	public static String decrypt(String strKey, String strIn) throws Exception {
		strKey = "1q2w3e4r";
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey);

		Cipher decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
		return new String(decryptCipher.doFinal((hexStr2ByteArr(strIn))));
	}

	private static Key getKey(String strKey) throws Exception {

		byte[] arrBTmp = strKey.getBytes(); // ��ȡ�ֽ���
		byte[] arrB = new byte[8]; // ����һ���յ�8λ�ֽ����飨Ĭ��ֵΪ0��

		// ��ԭʼ�ֽ�����ת��Ϊ8λ
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// �����Կ
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	private static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// ÿ��byte�������ַ���ܱ�ʾ�������ַ�ĳ��������鳤�ȵ�����
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// �Ѹ���ת��Ϊ����
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// С��0F������Ҫ��ǰ�油0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	private static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// �����ַ��ʾһ���ֽڣ������ֽ����鳤�����ַ��ȳ���2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public static void main(String[] args) {
		try {
			String Code = "000000";
			String codE;
			codE = DESPlus.encrypt("0", Code);

			System.out.println("����ǰ���ִ���" + Code);
			// System.out.println("����������Կ��" + key);
			System.out.println("���ܺ���ִ���" + codE);
			System.out.println("���ܺ���ִ���" + DESPlus.decrypt("0", codE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
