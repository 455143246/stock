package util;

import com.google.gson.Gson;

public class JsonUtil {
	public static <T> T jsonToObj(String json ,Class<T> t){
		Gson gosn=new Gson();
		return gosn.fromJson(json, t);
	}
}
