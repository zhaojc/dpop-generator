package org.mybatis.newgenerator.utils;

/**
 * 
 * ClassName: ColumnConvertUtil <br/>
 * date: 2014-8-6 上午3:40:44 <br/>
 *
 * @author huhailiang
 * @version 
 * @since JDK 1.6
 */
public abstract class ColumnConvertUtil {

	/**
	 * 数据库列名转属性
	 * @param columnName 数据库列名
	 * @return
	 */
	public static String columnName2FieldName(String columnName){
		String result = columnName.toLowerCase();
		boolean upcaseFlag = false;
		char[] charArr = result.toCharArray();
		result = "";
		for(char ch :charArr){
			if(ch=='_'){
				upcaseFlag = true;
				continue;
			}else if(upcaseFlag){
				result += (""+ch).toUpperCase();
				upcaseFlag = false;
			}else{
				result += ch;
				upcaseFlag = false;
			}
		}
		return result;
	}
	
	/**
	 * 属性转数据库列名
	 * @param str 属性名
	 * @return
	 */
	public static String fieldName2ColumnName(String str){
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			 return str;
		}
		StringBuffer buffer = new StringBuffer(strLen);

		char ch = 0;
		for (int i = 0; i < strLen; i++) {
			ch = str.charAt(i);
			if (Character.isUpperCase(ch)) {
				ch = Character.toLowerCase(ch);
				buffer.append("_");
			}
			if(Character.isDigit(ch)){//数字
				buffer.append("_");
			}
			buffer.append(ch);
		}
		return buffer.toString();
	}
}
