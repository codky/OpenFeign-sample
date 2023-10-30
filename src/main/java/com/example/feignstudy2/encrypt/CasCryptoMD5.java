package com.example.feignstudy2.encrypt;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CasCryptoMD5 {
	private static final String md5val[] = {"0", "1", "2",
			"3", "4", "5", "6", "7", "8",
			"9", "a", "b", "c", "d", "e",
			"f"};

	/**<pre>
	 *String 을 입력받아 
	 * </pre>
	 *@param 
	 *@exception NullPointerException
	 *@return String 
	 **/
	public static String getMD5(String in){
		try
		{
			MessageDigest md5enc = MessageDigest.getInstance("MD5");			
			md5enc.reset();
			md5enc.update(in.getBytes());
			byte md5res[] = md5enc.digest();			
			return toMD5String(md5res);
		}
		catch(NoSuchAlgorithmException e)
		{
			return null;
		}
	}

	public static String toMD5String(byte in[])
	{
		byte ch = 0x00;
		int i = 0; 
		if(in == null || in.length <= 0)
			return null;		

		StringBuffer out = new StringBuffer(in.length * 2);
    
		while (i < in.length)
		{
			ch = (byte) (in[i] & 0xF0);
			ch = (byte) (ch >>> 4);			
			ch = (byte) (ch & 0x0F);			
			out.append(md5val[ (int) ch]); 			
			ch = (byte) (in[i] & 0x0F); 			
			out.append(md5val[ (int) ch]); 			
			i++;
		}
		String rslt = new String(out);
		return rslt;		
	} 
}
