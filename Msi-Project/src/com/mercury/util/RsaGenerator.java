package com.mercury.util;

import java.security.*;

import javax.crypto.Cipher;

public final class RsaGenerator {
	private static RsaGenerator instance;
	
	public static RsaGenerator getInstance(){
		if(instance == null){
			synchronized(RsaGenerator.class){
				if(instance == null){
					instance = new RsaGenerator();
				}
			}
		}
		return instance;
	}
	
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	private RsaGenerator(){
		init();
	}
	
	public void init(){
		try{
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024);
			KeyPair kp = kpg.generateKeyPair();
			publicKey = kp.getPublic();
			privateKey = kp.getPrivate();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("finally")
	public byte[] encrypt(byte[] input){
		byte[] result = null;
		try{
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			result = cipher.doFinal(input);
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			return result;
		}
	}
	
	@SuppressWarnings("finally")
	public byte[] decrypt(byte[] input){
		byte[] result = null;
		try{
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			result = cipher.doFinal(input);
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			return result;
		}		
	}
}
