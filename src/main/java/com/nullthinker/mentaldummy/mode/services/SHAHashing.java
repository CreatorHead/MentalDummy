package com.nullthinker.mentaldummy.mode.services;

import java.security.MessageDigest;


public class SHAHashing {
	public String hashIt(String password)throws Exception
	{
		String salt = "&*$%@%_FGT^%^@%+(@^~@#$!%^&*(*)1234567890";
		password = password + salt;

		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		//convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();

		/*//convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
	    	System.out.println("Digest(in hex format):: " + hexString.toString());*/
	}
}
