package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;




/**
 * 字符串工具
 * @author wengxf
 *
 */
public class StringUtil {

	
	/**
	 * 把字符串转换为整数（如果有错，结果为0）
	 * @param s
	 * @return
	 */
	public static int parseInt(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		int i = 0; 
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 把字符串转换为整数（如果有错，结果为0�?
	 * @param s
	 * @return
	 */
	public static long parseLong(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		long i = 0; 
		try {
			i = Long.parseLong(s);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return i;
	}
	/**
	 * 把字符串数组转换为整数数组（如果有错，结果为0）
	 * @param ss
	 * @return
	 */
	public static int[] parseInts(String[] ss) {
		if (ss == null) {
			return new int[0];
		}
		int[] is = new int[ss.length]; 
		try {
			for (int i = 0; i < ss.length; i++) {
				is[i] = Integer.parseInt(ss[i]);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return is;
	}
	
	
	


	/**
	 * 将整数字符串根据“,”分割为整形数组
	 * @param columnIds
	 * @return
	 */
	public static int[] stringToIntegers(String columnIds) {
		String[] set = columnIds.split(",");
		int[] ids = new int[set.length];;
		for (int i = 0; i < set.length; i++) {
			String e = set[i].trim();
			ids[i] = Integer.parseInt(e);
		}
		return ids;
	}

	/**
	 * 判断字符串是否为空（空字符串也为空）
	 * @param s
	 * @return
	 */
	public static boolean isNull(String s){
		if(s == null)return true;
		if(s.trim().length() == 0)return true;
		return false;
	}
	
	/**
	 * js的请求含中文字符的处理
	 * @param str
	 * @return
	 */
	public static String decodeStr(String str) {
		try {
			if(UtilTool.empty(str)) return str;
			return  URLDecoder.decode(str.replaceAll("%", "%25"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 编码中文字符串
	 * @param str
	 * @return
	 */
	public static String encodeStr(String str) {
		try {
			if(UtilTool.empty(str)) return str;
			return URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 编码中文字符串
	 * @param str
	 * @param enc
	 * @return
	 */
	public static String encodeStr(String str,String enc) {
		try {
			if(UtilTool.empty(str)) return str;
			return URLEncoder.encode(str,enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 屏蔽字符串中的部分字段
	 * @param str 原字符串
	 * @param startIndex 屏蔽的其实位置，从0开始，包含此位置
	 * @param filtLength 屏蔽长度,
	 * @param shortOpt 长度不足时方式，0不屏蔽,1屏蔽到最后
	 * @param filterChar 屏蔽的字符串
	 * @return
	 */
	public static String getFiltedStr(String str,int startIndex,int filtLength,
			String shortOpt,Character filterChar){
		if(str != null && str.length() >= startIndex +1 ){
			if(filterChar == null){
				filterChar = new Character('*');
			}
			
			if(str.length() >= startIndex + filtLength){
				String filterStr = "";
				for(int i=0;i < filtLength; i++){
					filterStr = filterStr + filterChar;
				}
				return str.substring(0, startIndex) + filterStr + str.substring(startIndex + filtLength);
			}else if(shortOpt.equals("1")){
				filtLength = str.length() - startIndex;
				if(str.length() >= startIndex + filtLength){
					String filterStr = "";
					for(int i=0;i < filtLength; i++){
						filterStr = filterStr + filterChar;
					}
					return str.substring(0, startIndex) + filterStr;
				}
			}else{
				return str;
			}
		}if(str != null){
			return str;
		}
		return null;
	}
	
	/**
	 * 将字符串List转成字符串
	 * @param stringList
	 * @param spiltStr 间隔字符串
	 * @param isAddLast 最后是否添加间隔字符串。
	 * @return
	 */
	public static String getStrByStrList(List<String> stringList,
			String spiltStr,boolean isAddLast){
		if(UtilTool.empty(stringList)){
			return "";
		}else{
			String string = ""; 
			for(int i=0;i<stringList.size();i++){
				String curStr = stringList.get(i);
				if(!isNull(curStr)){
					if(i == stringList.size()-1&&!isAddLast){
						string += curStr;
					}else{
						string += curStr + spiltStr;
					}
				}
			}
			return string;
		}
	}

	/**
	 * 将字符串List转成字符串
	 * @param stringList
	 * @param spiltStr 间隔字符串
	 * @param isAddLast 最后是否添加间隔字符串。
	 * @return
	 */
	public static String getStrByStrSet(Set<String> stringSet,
			String spiltStr,boolean isAddLast){
		if(UtilTool.empty(stringSet)){
			return "";
		}else{
			String string = ""; 
			int i=0;
			for(String curStr:stringSet){
				if(!isNull(curStr)){
					if(i == stringSet.size()-1&&!isAddLast){
						string += curStr;
					}else{
						string += curStr + spiltStr;
					}
				}
			}
			return string;
		}
	}

	/**
	 * 
	 * @param errMessages
	 * @return
	 */
	public static String getStrByStrListForPage(List<String> errMessages){
		return getStrByStrList(errMessages,"",false);
	}
	
	/**
	 * 
	 * @param errMessages
	 * @return
	 */
	public static String getStrByStrSetForPage(Set<String> errMessages){
		return getStrByStrSet(errMessages,"",false);
	}
}
