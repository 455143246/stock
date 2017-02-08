package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import po.Holiday;

public class HttpUtil {
	/**为url后面添加param
	 * @param url
	 * @param params
	 * @return
	 */
	public static String addParam(String url,Map params){
		StringBuffer sf=new StringBuffer();
		if(UtilTool.empty(params))return url;
		sf.append(url).append("?");
		Object[] keyArr = params.keySet().toArray();
		for( int i=0;i<keyArr.length;i++){
			String key=keyArr[i].toString();
			sf.append(key).append("=").append(params.get(key).toString()).append("&");
		}
		sf.deleteCharAt(sf.length()-1);
		return sf.toString();
	}
	/**发送get方式的http请求返回一个inputStream
	 * @param httpUrl
	 * @return
	 */
	public static  InputStream sendHttpGet(String httpUrl){
        HttpURLConnection connection=null;
        InputStream is=null;
        try {
            URL url = new URL(httpUrl);
            //http代理
            InetSocketAddress addr = new InetSocketAddress("isa06",8008);             
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); 
            
            connection = (HttpURLConnection) url
                    .openConnection(proxy);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(3000);             
            connection.setReadTimeout(3000);             
            connection.connect();
            is = connection.getInputStream();
        }catch(Exception e){
        	e.printStackTrace();
        }
        return  is;
	}
}
