package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class InputStreamUtil {
	/**json类型的inputStream转为map返回
	 * @param is
	 * @return
	 */
	public static Map<String,String> jsonInputStreamToMap(InputStream is){
        String strRead = null;
        StringBuffer sbf = new StringBuffer();
        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        	while ((strRead = reader.readLine()) != null) {
        		sbf.append(strRead);
        		sbf.append("\r\n");
        	}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return JsonUtil.jsonToObj(sbf.toString(),Map.class);
	}
}
