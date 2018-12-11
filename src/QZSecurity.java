import java.math.BigInteger;
import java.net.InetAddress;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class QZSecurity {
	Cipher ecipher;
	Cipher dcipher;

	 QZSecurity()
	    throws Exception
	  {
	    try
	    {
      String skStr = new String(toByteArray("653134373033666630386662353731636662663762366162"));

	      SecretKey key = new SecretKeySpec(toByteArray(skStr.substring(8)), "DES");
	      this.ecipher = Cipher.getInstance("DES"); this.dcipher = Cipher.getInstance("DES");
	      this.ecipher.init(1, key); 
	      this.dcipher.init(2, key);
	      
	      
	      String accessId = toHexString(InetAddress.getLocalHost().getHostName().toLowerCase().getBytes());
	      System.out.println(encrypt("_QZv2_"+  accessId));
	    }
	    catch (Exception ex)
	    {
	      throw new Exception(ex);
	    }
	  }

	public String encrypt(String str) throws Exception {
		try {
			byte[] utf8 = str.getBytes("UTF8");

			byte[] enc = this.ecipher.doFinal(utf8);

			return new BASE64Encoder().encode(enc);
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	public String decrypt(String str) throws Exception {
		try {
			byte[] dec = new BASE64Decoder().decodeBuffer(str);

			byte[] utf8 = this.dcipher.doFinal(dec);

			return new String(utf8, "UTF8");
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	private static byte[] toByteArray(String hexStr) {
		byte[] bytes = new byte[hexStr.length() / 2];

		int i = 0;
		for (int j = 0; i < hexStr.length(); j++) {
			bytes[j] = new BigInteger(hexStr.substring(i, i + 2), 16)
					.byteValue();

			i += 2;
		}

		return bytes;
	}
	
	  public static String toHexString(byte[] bytes)
	  {
	    StringBuffer buf = new StringBuffer();

	    if (bytes == null)
	      buf.append("");
	    else {
	      for (int i = 0; i < bytes.length; i++)
	      {
	        int c = bytes[i] & 0xFF;
	        if (c <= 15) buf.append('0');
	        buf.append(Integer.toHexString(c));
	      }
	    }
	    return buf.toString();
	  }
	
	public static void main(String []args){
		try{
			new QZSecurity();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}