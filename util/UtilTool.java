package util;


import java.util.List;
import java.util.Map;
import java.util.Set;
@SuppressWarnings("unchecked")
public class UtilTool {
	/**
	 * 判断字符串是否存在
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isExist(String str) {
		return (str != null && !"".equals(str.trim())) ? true : false;
	}

	/**
	 * 对象转换成String
	 * 
	 * @param obj
	 * @return
	 */
	public static String valueOf(Object obj) {
		return ((obj != null) ? obj.toString() : "");
	}
	/**
	 * 判断对象为空
	 * @param obj
	 * @return
	 */
	
	public static boolean empty(Object obj) {

		if (obj == null) {
			return true;
		} else if (obj instanceof String && obj.equals("") ) {
			return true;
		} else if (obj instanceof Number && ((Number) obj).doubleValue() == -1 ) {
			return true;
		} else if (obj instanceof Long && (((Long) obj).longValue() == -1 || ((Long) obj).longValue() == 0) ) {
			return true;
		} else if (obj instanceof Integer && ((Integer) obj).intValue() == -1  ) {
			return true;
		} else if (obj instanceof Boolean && !((Boolean) obj)) {
			return true;
		} else if (obj instanceof Map && ((Map) obj).isEmpty()) {
			return true;
		} else if (obj instanceof List &&((List) obj).isEmpty()){
			return true ;
		}else if (obj instanceof Set &&((Set) obj).isEmpty()){
			return true ;
		} else if (obj instanceof Object[] && ((Object[]) obj).length == 0) {
			return true;
		}
		
		return false;
	}
	
	public static String toStrArr(Object[] obj){
		String s = "" ;
		if(empty(obj)){
			return s ;
		}
		for(int i = 0 ; i < obj.length ; i++){
			s += obj[i] + "," ;
		}
		if(s.lastIndexOf(",")!= -1){
			s = s.substring(0,s.lastIndexOf(",") ) ;
		}
		return s; 
	}
	
	/**
	 * 字符串操作，是否为空，空返回null, 不为空去空格返回字符串
	 */
	public static String isEmptyNull(Object obj){
		if(obj == null || obj.equals("")){
			return null;
		}else{
			return obj.toString().trim();
		}
	}
}
