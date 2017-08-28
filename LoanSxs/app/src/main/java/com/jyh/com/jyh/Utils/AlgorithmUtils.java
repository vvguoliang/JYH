package com.jyh.com.jyh.Utils;

import android.annotation.SuppressLint;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by vvguoliang on 2017/8/23.
 * 算法
 */
@SuppressLint({"GetInstance","NewApi"})
public class AlgorithmUtils {

    public static String encrypt(String key, String data) throws Exception {
        byte[] rawKey = deriveKeyInsecurely( key, 32 ).getEncoded();
        byte[] result = encrypt( rawKey, data.getBytes() );
        return toHex( result );
    }

    public static String decrypt(String key, String encrypted) throws Exception {
        byte[] rawKey = deriveKeyInsecurely( key, 32 ).getEncoded();
        byte[] enc = toByte( encrypted );
        byte[] result = decrypt( rawKey, enc );
        return new String( result );
    }

    private static SecretKey deriveKeyInsecurely(String password, int keySizeInBytes) {
        byte[] passwordBytes = password.getBytes( StandardCharsets.US_ASCII );
        return new SecretKeySpec(
                InsecureSHA1PRNGKeyDerivator.deriveInsecureKey( passwordBytes, keySizeInBytes ),
                "AES" );
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );
        Cipher cipher = Cipher.getInstance( "AES" );
        cipher.init( Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec( new byte[cipher.getBlockSize()] ) );
        byte[] encrypted = cipher.doFinal( clear );
        return encrypted;
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec( raw, "AES" );
        Cipher cipher = Cipher.getInstance( "AES" );
        cipher.init( Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec( new byte[cipher.getBlockSize()] ) );
        return cipher.doFinal( encrypted );
    }

    private static String toHex(String txt) {
        return toHex( txt.getBytes() );
    }

    private static String fromHex(String hex) {
        return new String( toByte( hex ) );
    }

    private static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf( hexString.substring( 2 * i, 2 * i + 2 ), 16 ).byteValue();
        return result;
    }

    private static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer( 2 * buf.length );
        for (int i = 0; i < buf.length; i++) {
            appendHex( result, buf[i] );
        }
        return result.toString();
    }

    private final static String HEX = "0123456789ABCDEF";

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append( HEX.charAt( (b >> 4) & 0x0f ) ).append( HEX.charAt( b & 0x0f ) );
    }

}
