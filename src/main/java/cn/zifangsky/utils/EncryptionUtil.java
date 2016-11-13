package cn.zifangsky.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;

public class EncryptionUtil {
	public static String DEFAULTKEY = "zifangsky";
	
	/**
	 * Base64 encode
	 */
	public static String base64Encode(String data) {
		return Base64.encodeBase64String(data.getBytes());
	}

	/**
	 * Base64 decode
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Decode(String data) throws UnsupportedEncodingException {
		return new String(Base64.decodeBase64(data.getBytes()), "utf-8");
	}

	/**
	 * md5
	 */
	public static String md5Hex(String data) {
		return DigestUtils.md5Hex(data);
	}

	/**
	 * sha1
	 */
	public static String sha1Hex(String data) {
		return DigestUtils.sha1Hex(data);
	}

	/**
	 * sha256
	 */
	public static String sha256Hex(String data) {
		return DigestUtils.sha256Hex(data);
	}

	/**
	 * sha512
	 * 
	 * @param data
	 * @return sha512Hex加密之后的密文
	 */
	public static String sha512Hex(String data) {
		return DigestUtils.sha512Hex(data);
	}
	
	/**
	 * hmacMd5Hex
	 * @param key  密钥
	 * @param data  待加密数据
	 * @return  hmacMd5Hex加密之后的密文
	 */
	public static String hmacMd5Hex(String key,String data){
		if(key == null){
			key = DEFAULTKEY;
		}
			
		return HmacUtils.hmacMd5Hex(key, data);
	}
	
	/**
	 * hmacSha1Hex
	 * @param key  密钥
	 * @param data  待加密数据
	 * @return  hmacSha1Hex加密之后的密文
	 */
	public static String hmacSha1Hex(String key,String data){
		if(key == null){
			key = DEFAULTKEY;
		}
		
		return HmacUtils.hmacSha1Hex(key, data);
	}
	
	/**
	 * hmacSha256Hex
	 * @param key  密钥
	 * @param data  待加密数据
	 * @return  hmacSha256Hex加密之后的密文
	 */
	public static String hmacSha256Hex(String key,String data){
		if(key == null){
			key = DEFAULTKEY;
		}
		
		return HmacUtils.hmacSha256Hex(key, data);
	}
	
	/**
	 * hmacSha512Hex
	 * @param key  密钥
	 * @param data  待加密数据
	 * @return  hmacSha512Hex加密之后的密文
	 */
	public static String hmacSha512Hex(String key,String data){
		if(key == null){
			key = DEFAULTKEY;
		}
		
		return HmacUtils.hmacSha512Hex(key, data);
	}

}