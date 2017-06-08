package com.champion.framework.crpyt;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:
 * @Auther： william
 * @Date：2017/6/7 0007 16:47
 */
public class CryptUtil {

    private static final String DEFAULT_ENCODING = "utf-8";
    private static final String ENCRYPT_ALGOGITHM_SHA1 = "SHA1";
    private static final String ENCRYPT_ALGOGITHM_DES = "DES";
    private static final String DEFAULT_RETURN = "";

    /**
     * @Description hex encode byte to String
     * @MethodName encodeHex
     * @param input
     * @return java.lang.String
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 16:57
     */
    public static String encodeHex(byte[] input) {
        return new String(Hex.encodeHex(input));
    }

    /**
     * @Description hex decode String to byte
     * @MethodName decodeHex
     * @param input
     * @return byte[]
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 16:58
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description Base64 encode byte[] to String
     * @MethodName encodeBase64
     * @param input
     * @return java.lang.String
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 16:52
     */
    public static String encodeBase64(byte[] input) {
        try {
            return new String(Base64.encodeBase64(input), DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DEFAULT_RETURN;
    }

    /**
     * @Description Base64 encode String to String
     * @MethodName encodeBase64
     * @param input
     * @return java.lang.String
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 16:54
     */
    public static String encodeBase64(String input) {
        try {
            return encodeBase64(input.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DEFAULT_RETURN;
    }

    /**
     * @Description Base64 decode String to byte[]
     * @MethodName decodeBase64
     * @param input
     * @return byte[]
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 17:00
     */
    public static byte[] decodeBase64(String input) {
        try {
            return Base64.decodeBase64(input.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description Base64 decode String to String
     * @MethodName decodeBase64String
     * @param input
     * @return java.lang.String
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 17:02
     */
    public static String decodeBase64String(String input) {
        try {
            return new String(Base64.decodeBase64(input.getBytes(DEFAULT_ENCODING)), DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DEFAULT_RETURN;
    }

    /**
     * @Description url encode String to String
     * @MethodName urlEncode
     * @param input
     * @return java.lang.String
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 17:03
     */
    public static String urlEncode(String input) {
        try {
            return URLEncoder.encode(input, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DEFAULT_RETURN;
    }

    /**
     * @Description url decode String to String
     * @MethodName urlDecode
     * @param input
     * @return java.lang.String
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 17:05
     */
    public static String urlDecode(String input) {
        try {
            return URLDecoder.decode(input, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DEFAULT_RETURN;
    }

    /**
     * @Description SHA1 encrypt then hex encode
     * @MethodName SHA1Digest
     * @param input
     * @return java.lang.String
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/7 0007 17:06
     */
    public static String SHA1Digest(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ENCRYPT_ALGOGITHM_SHA1);
            digest.update(input.getBytes(DEFAULT_ENCODING));
            byte messageDigest[] = digest.digest();
            return encodeHex(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return DEFAULT_RETURN;
    }
}
