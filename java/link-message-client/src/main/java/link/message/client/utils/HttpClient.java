package link.message.client.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 无依赖的HttpClient工具类
 * @author kael.
 */
public class HttpClient {
    
    public static String postJsonBody(String url, String body, Map<String, String> headers) throws HttpRequestException{
        HttpURLConnection conn = openConnection(url);
        headers.put("Content-Type","application/json; Charset=UTF-8");
        preProcessConnection(conn,headers,"POST");
        return sendAndGetContent(conn,body);
    }
    public static String post(String url, Map<String, String> params, Map<String, String> headers) throws HttpRequestException{
        HttpURLConnection conn = openConnection(url);
        preProcessConnection(conn,headers,"POST");
        return sendAndGetContent(conn,urlencoded(params));
    }
    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws HttpRequestException{
        String ps = urlencoded(params);
        int i = url.indexOf("?");
        if(i < 0){
            // url = http://example.com
            url = url+"?"+ps;
        }else if(i < url.length()-1){
            // url = http://example.com?a=1
            url = url + "&" + ps;
        }else {
            // url = http://example.com?
            url = url + ps;
        }
        HttpURLConnection conn = openConnection(url);
        preProcessConnection(conn,headers,"GET");
        return sendAndGetContent(conn,null);
    }
    /**=========================== 内部方法 ==================================**/
    protected static final SSLSocketFactory ignoreCerSSLSocketFactory;
    protected static final SSLSocketFactory defaultSSLSocketFactory;
    static {
        defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
        // Create a trust manager that does not validate certificate chains  
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }
            public void checkClientTrusted(X509Certificate[] chain, String authType)  {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) {}
        }
        };
        // Install the all-trusting trust manager  
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            ignoreCerSSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    protected static String urlencoded(Map<String, String> params){
        if(null != params && params.size()>0){
            StringBuilder encoded = new StringBuilder();
            for(Map.Entry<String,String> entry:params.entrySet()){
                encoded.append(entry.getKey());
                encoded.append("=");
                try {
                    encoded.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                encoded.append("&");
            }
            if(encoded.length() > 0){
                encoded.deleteCharAt(encoded.length()-1);
            }
            return encoded.toString();
        }
        return "";
    }
    
    protected static void preProcessConnection(HttpURLConnection conn, Map<String, String> headers, String method) {
        preProcessConnection(conn,headers,method,10000);
    }
    protected static void preProcessConnection(HttpURLConnection conn, Map<String, String> headers, String method,int timeout) {
        conn.setDoInput(true);
        conn.setDoOutput(true);
        try {
            conn.setRequestMethod(method);
        } catch (ProtocolException e) {
            throw new HttpRequestException(e);
        }
        conn.setConnectTimeout(timeout);
        if(null != headers && headers.size() > 0){
            for(Map.Entry<String, String> entry : headers.entrySet()){
                conn.setRequestProperty(entry.getKey(),entry.getValue());
            }
        }
    }
    
    protected static String sendAndGetContent(HttpURLConnection conn,String body) {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuilder content = new StringBuilder();

        OutputStream os = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        
        try {
            conn.connect();
            if(null != body && !body.trim().isEmpty()){
                os = conn.getOutputStream();
                osw = new OutputStreamWriter(os,"UTF-8");
                bw = new BufferedWriter(osw);
                bw.write(body);
                bw.flush();
                osw.flush();
                os.flush();
            }
            int code = conn.getResponseCode();
            try {
                is = conn.getInputStream();
            } catch (IOException e) {
                is = conn.getErrorStream();
            }
            if(null == is){
                throw new HttpRequestException("error request:"+code).setStatus(code);
            }
            isr = new InputStreamReader(is,"UTF-8");
            br = new BufferedReader(isr);
            do{
                String line = br.readLine();
                if(null == line){
                    break;
                }
                content.append(line);
                content.append('\n');
            }while (true);
            if(content.length() > 0){
                content.deleteCharAt(content.length()-1);
            }
            if(code>=400){
                throw new HttpRequestException(String.join("", "error status: ", String.valueOf(code), ", url: ", conn.getURL().toString(), "\n", content.toString())).setStatus(code);
            }
            return content.toString();
        }catch (HttpRequestException e){
            throw e;
        } catch (Exception e){
            throw new HttpRequestException(e);
        } finally {
            try {
                if(null != bw){
                    bw.close();
                }
                if(null != osw){
                    osw.close();
                }
                if(null != os){
                    os.close();
                }
                if(null != br){
                    br.close();
                }
                if(null != isr){
                    isr.close();
                }
                if(null != is){
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            conn.disconnect();
        }
    }
    
    private HttpClient() {}
    
    protected static HttpURLConnection openConnection(String url){
        URI uri = URI.create(url);
        try {
            URL u = uri.toURL();
            HttpURLConnection conn = openIgnoreCerConnection(u);
            return conn;
        } catch (MalformedURLException e) {
            throw new HttpRequestException(e);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }
    
    protected static HttpURLConnection openIgnoreCerConnection(URL url) throws IOException {
        if("https".equalsIgnoreCase(url.getProtocol())){
            HttpsURLConnection.setDefaultSSLSocketFactory(ignoreCerSSLSocketFactory);
            URLConnection connection;
            try {
                connection = url.openConnection();
            } finally {
                HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
            }
            return (HttpsURLConnection)connection;
        }else if("http".equalsIgnoreCase(url.getProtocol())){
            return (HttpURLConnection)url.openConnection();
        }else {
            throw new ProtocolException("not support protocol:"+url.getProtocol());
        }
    }
    @SuppressWarnings("serial")
	public static class HttpRequestException extends RuntimeException {

        private int status;

        public HttpRequestException(String message) {
            super(message);
        }

        public HttpRequestException(String message, Throwable cause) {
            super(message, cause);
        }

        public HttpRequestException(Throwable cause) {
            super(cause);
        }

        public int getStatus() {
            return status;
        }

        public HttpRequestException setStatus(int status) {
            this.status = status;
            return this;
        }
    }
}
