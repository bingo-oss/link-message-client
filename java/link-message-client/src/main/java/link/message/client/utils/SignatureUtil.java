package link.message.client.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SignatureUtil {
	private static final String HMAC_SHA1 = "HmacSHA1";
	public  static final String TIMESTAMP = "timestamp";
	public  static final String NONCE     = "nonce";
	public  static final String SIGNATURE = "signature";
	
	/**
     * 对Url进行签名
     * @param url 待签名的Url
     * @param token 签名用的token
     * @param postData url提交参数
     * @return url?timestamp=xx&nonce=xx&signature=xx
     */
    /*public static String signature(String url,String token,List<NameValuePair> postData){
    	String timeStamp = String.valueOf(System.currentTimeMillis());
        String nonce = SignatureUtil.getRandomString(8);

        String[] paramArray = new String[postData.size()];
        int i = 0;
        for (NameValuePair pair : postData) {
			if(Strings.isEmpty(pair.getValue())){
				continue;
			}
			paramArray[i] = pair.getValue();
            i++;
		}
        
        String sign = getSignature(token, timeStamp, nonce, paramArray);
        String signedUrl = url;
        signedUrl = Urls.appendParam(signedUrl, TIMESTAMP, timeStamp);
        signedUrl = Urls.appendParam(signedUrl, NONCE, nonce);
        signedUrl = Urls.appendParam(signedUrl, SIGNATURE, sign);

        return signedUrl;        
    }*/
    
    
    
    /**
     * 对Url进行签名
     * @param url 待签名的Url
     * @param token 签名用的token
     * @param postData url提交参数
     * @return url?timestamp=xx&nonce=xx&signature=xx
     */
    /*public static String signature(String url,String token, MultiValueMap<String, Object> postData)
    {
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String nonce = SignatureUtil.getRandomString(8);

        String[] paramArray = new String[postData.size()];
        int i = 0;
        for (List<Object> valueList : postData.values())
        {
            Object p = valueList.get(0);
            if (p == null)
            {
                continue;
            }
            paramArray[i] = p.toString();
            i++;
        }
        
        String sign = getSignature(token, timeStamp, nonce, paramArray);
        String signedUrl = url;
        signedUrl = Urls.appendParam(signedUrl, TIMESTAMP, timeStamp);
        signedUrl = Urls.appendParam(signedUrl, NONCE, nonce);
        signedUrl = Urls.appendParam(signedUrl, SIGNATURE, sign);

        return signedUrl;
    }*/

	/**
	 * 生成摘要签名
	 * @param data 待加密的数据
	 * @param key 加密使用的key
	 */
	public static String getSignature(String data, String key) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] keyBytes = key.getBytes("UTF-8");
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte b : rawHmac) {
			sb.append(byteToHexString(b));
		}
		return sb.toString();
	}

	/**
	 * 生成通信签名
	 * @param token token
	 * @param timeStamp 时间戳
	 * @param nonce 随机数
	 * @param 其他参数
	 */
	public static String getSignature(String token, String timeStamp, String nonce, String... params) {
		ArrayList<String> keyList = new ArrayList<String>();
		keyList.add(token);
		keyList.add(timeStamp);
		keyList.add(nonce);

		ArrayList<String> valueList = new ArrayList<String>();
		if (params != null)
			for (Object param : params) {
				if (param == null)
					continue;
				valueList.add(param.toString());
			}
		return SignatureUtil.getMixSignature(valueList, keyList);
	}

	/**
	 * 获取组合签名
	 */
	public static String getMixSignature(List<String> vList, List<String> kList) {
		if (vList.size() > 1)
			Collections.sort(vList);
		if (kList.size() > 1)
			Collections.sort(kList);

		StringBuilder keyBuilder = new StringBuilder();
		for (String s : kList) {
			keyBuilder.append(s);
		}

		StringBuilder valueBuilder = new StringBuilder();
		for (String v : vList) {
			valueBuilder.append(v);
		}
		String key = keyBuilder.toString();
		String value = valueBuilder.toString();

		String sign;
		try {
			sign = SignatureUtil.getSignature(value, key);
		} catch (Exception e) {
			sign = null;
		}
		if (null == sign || sign.trim().length() == 0) {
			throw new IllegalArgumentException("签名生成失败！");
		}
		return sign;
	}

	private static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0f];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	/**
	 * 获取指定才长度的随机字符串
	 */
	public static String getRandomString(int length) {
		// length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
}