package com.es.hfuu.common.util.base;

import com.es.hfuu.common.util.array.ArrayUtil;
import com.es.hfuu.common.util.constants.SymbolType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName: StringUtil
 * @Description: 字符串类型的工具类【字符串、字符数组的处理的操作】
 * @author lsx
 * @date 2019/11/7
 */
public class StringUtil {
	/** 正整数的正则表达式 */
	private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]*");
	/** 数组中元素未找到的下标，值为-1 */
	public static final int INDEX_NOT_FOUND = -1;

	/**
	 * 是否空白字符 【空白符包括空格、制表符、全角空格和不间断空格】
	 * @Title: isBlankChar
	 * @param ch 空白字符
	 * @return boolean 【true：空白字符，false：非空白字符】
	 */
	private static boolean isBlankChar(char ch) {
		return Character.isWhitespace(ch) || Character.isSpaceChar(ch);
	}

	/**
	 * 被检测字符为null或者字符中包含空白符【空白符包括空格、制表符、全角空格和不间断空格】
	 * @Title: isBlank
	 * @param str 被检测字符串
	 * @return boolean 【true：字符串为空、字符串中包含空白字符】
	 */
	public static boolean isBlank(CharSequence str) {
		int length;
		if (str == null || (length = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < length; i++) {
			// 只要有一个非空字符即为非空字符串
			if (isBlankChar(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 被检测字符为null或者字符中不包含空白符【空白符包括空格、制表符、全角空格和不间断空格】
	 * @Title: isBlank
	 * @param str 被检测字符串
	 * @return boolean 【false：字符串为空、字符串中包含空白字符】
	 */
	public static boolean isNotBlank(CharSequence str) {
		int length;
		if (str == null || (length = str.length()) == 0) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			// 只要有一个非空字符即为非空字符串
			if (isBlankChar(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串为空
	 * @Title: isEmpty
	 * @param str 字符串
	 * @return boolean 【true：字符串为null、字符串由空白字符组成】
	 */
	public static boolean isEmpty(CharSequence str) {
		return null == str || trim(str, 0).length() == 0;
	}

	/**
	 * 判断字符串不为空
	 * @Title: isNotEmpty
	 * @param str 字符串
	 * @return boolean 【false：字符串为null、字符串由空白字符组成】
	 */
	public static boolean isNotEmpty(CharSequence str) {
		return null != str && !"".equals(trim(str, 0));
	}

	/**
	 * 去除字符串左边、右边、左右两边的字符串
	 * @Title: trim
	 * @param str 被检测字符串
	 * @param mode 0：去除字符串左右两边的空白符串
	 *            <0：去除字符串左边的空白符串
	 *            >1：去除字符串右边的空白符串
	 * @return String 去掉空格后的字符串
	 */
	public static String trim(CharSequence str, int mode) {
		if (str == null) {
			return null;
		}
		int length = str.length();
		int start = 0;
		int end = length;

		// 扫描字符串头部
		if (mode <= 0) {
			while ((start < end) && (isBlankChar(str.charAt(start)))) {
				start++;
			}
		}
		// 扫描字符串尾部
		if (mode >= 0) {
			while ((start < end) && (isBlankChar(str.charAt(end - 1)))) {
				end--;
			}
		}
		if ((start > 0) || (end < length)) {
			return str.toString().substring(start, end);
		}
		return str.toString();
	}

	/**
	 * 将字符串为null的字符串转成空字符串""
	 * @Title: nullToEmpty
	 * @param str 字符串
	 * @return String 返回的字符串
	 */
	public static String nullToEmpty(CharSequence str) {
		return nullToEmpty(str, SymbolType.EMPTY);
	}

	/**
	 * 字符串为null则返回""，否则返回自身。
	 * @Title: nullToEmpty
	 * @param str 要转换的字符串
	 * @param defaultStr 默认字符串
	 * @return String 返回的字符串
	 */
	public static String nullToEmpty(CharSequence str, String defaultStr) {
		return (str == null) ? defaultStr : str.toString();
	}

	/**
	 * 字符串为null或者由空白字符组成则返回null，否则返回自身。
	 * @Title: emptyToNull
	 * @param str 要转换的字符串
	 * @return String 返回的字符串
	 */
	public static String emptyToNull(CharSequence str) {
		return isEmpty(str) ? null : str.toString();
	}

	/*******************************************以上是字符串的空判断***************************************/

	/**
	 * 将字符串两边去空格后，然后在将首字母转成小写的字符串
	 * @Title: firstCharLowCase
	 * @param str 待转字符串
	 * @return String 转后的字符串
	 */
	public static String firstCharLowCase(String str) {
		str = trim(str, 0);
		if(isNotEmpty(str)){
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		} else {
			return str;
		}
	}

	/**
	 * 将字符串两边去空格后，然后在将首字母转成大写的字符串
	 * @Title: firstCharUpCase
	 * @param str 待转字符串
	 * @return String 转后的字符串
	 */
	public static String firstCharUpCase(String str) {
		str = trim(str, 0);
		if(isNotEmpty(str)){
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		} else {
			return str;
		}
	}

	/*******************************************以上是字符串的首字母大小写的转换***************************************/
	/**
	 * 将数组元素用","拼接成字符串返回
	 * @Title: arrayToString
	 * @param arr     引用类型数组
	 * @return String 返回的字符串
	 */
	public static <T> String arrayToString(T... arr) {
		return arrayToString(SymbolType.COMMA, arr);
	}

	/**
	 * 将数组元素用指定字符拼接成字符串返回
	 * @Title: arrayToString
	 * @param linkStr 连接符
	 * @param arr     引用类型数组
	 * @return String 返回的字符串
	 */
	public static <T> String arrayToString(String linkStr, T... arr) {
		if(null == arr || arr.length == 0){
			return SymbolType.EMPTY;
		}
		StringBuilder result = new StringBuilder("");
		if(isEmpty(linkStr)){
			linkStr = SymbolType.COMMA;
		}
		for (int i = 0, length = arr.length; i < length; i++){
			if(null != arr[i]){
				if(result.length() >0 && i > 0){
					result.append(linkStr);
				}
				result.append(arr[i]);
			}
		}
		return result.toString();
	}

	/**
	 * 将数组元素用","拼接成字符串返回
	 * @Title: arrayToString
	 * @param arr     long类型数组
	 * @return String 返回的字符串
	 */
	public static String arrayToString(long[] arr) {
		return arrayToString(SymbolType.COMMA, arr);
	}

	/**
	 * 把数组转成字符串
	 * @Title: arrayToString
	 * @param arr long类型的数组
	 * @return String 数组字符串
	 */
	public static String arrayToString(String linkStr, long[] arr) {
		if(null == arr || arr.length == 0){
			return SymbolType.EMPTY;
		}
		StringBuilder result = new StringBuilder("");
		if(isEmpty(linkStr)){
			linkStr = SymbolType.COMMA;
		}
		for (int i = 0, length = arr.length; i < length; i++){
			if(result.length() >0 && i > 0){
				result.append(linkStr);
			}
			result.append(arr[i]);
		}
		return result.toString();
	}

	/**
	 * 把数组转成字符串
	 * @Title: arrayToString
	 * @param arr int类型的数组
	 * @return String 数组字符串
	 */
	public static String arrayToString(int[] arr) {
		return arrayToString(SymbolType.COMMA, arr);
	}

	/**
	 * 把数组转成字符串
	 * @Title: arrayToString
	 * @param arr int类型的数组
	 * @return String 数组字符串
	 */
	public static String arrayToString(String linkStr, int[] arr) {
		if(null == arr || arr.length == 0){
			return SymbolType.EMPTY;
		}
		StringBuilder result = new StringBuilder("");
		if(isEmpty(linkStr)){
			linkStr = SymbolType.COMMA;
		}
		for (int i = 0, length = arr.length; i < length; i++){
			if(result.length() >0 && i > 0){
				result.append(linkStr);
			}
			result.append(arr[i]);
		}
		return result.toString();
	}

	/*******************************************以上是将数组转成以,隔开的字符串***************************************/

	/**
	 * 将数组元素用","拼接成字符串返回
	 * @Title: arrayToString
	 * @param list     list集合
	 * @return String 返回的字符串
	 */
	public static <T> String arrayToString(List<T> list) {
		return arrayToString(SymbolType.COMMA, list);
	}

	/**
	 * 将数组元素用指定字符拼接成字符串返回
	 * @Title: arrayToString
	 * @param linkStr 连接符
	 * @param list    list集合
	 * @return String 返回的字符串
	 */
	public static <T> String arrayToString(String linkStr, List<T> list) {
		if(null == list || list.size() == 0){
			return SymbolType.EMPTY;
		}
		StringBuilder result = new StringBuilder("");
		if(isEmpty(linkStr)){
			linkStr = SymbolType.COMMA;
		}
		T tmp = null;
		for (int i = 0, length = list.size(); i < length; i++){
			tmp = list.get(i);
			if(null != tmp){
				if(result.length() >0 && i > 0){
					result.append(linkStr);
				}
				result.append(tmp);
			}
		}
		return result.toString();
	}

	/**
	 * 将数组元素用","拼接成字符串返回
	 * @Title: arrayToString
	 * @param set     set集合
	 * @return String 返回的字符串
	 */
	public static <T> String arrayToString(Set<T> set) {
		return arrayToString(SymbolType.COMMA, set);
	}

	/**
	 * 将数组元素用","拼接成字符串返回
	 * @Title: arrayToString
	 * @param set     set集合
	 * @return String 返回的字符串
	 */
	public static <T> String arrayToString(String linkStr, Set<T> set) {
		if(null == set || set.size() == 0){
			return SymbolType.EMPTY;
		}
		StringBuilder result = new StringBuilder("");
		if(isEmpty(linkStr)){
			linkStr = SymbolType.COMMA;
		}
		int i = 0;
		for (T tmp : set){
			i ++;
			if(null != tmp){
				if(result.length() >0 && i > 0){
					result.append(linkStr);
				}
				result.append(tmp);
			}
		}
		return result.toString();
	}

	/*******************************************以上是将集合转成以,隔开的字符串***************************************/

	public static void main(String[] args) {
		System.out.println(arrayToString("_" ,new int[]{1,2,3,4}));
		System.out.println(arrayToString("_" ,new String[]{"123","asd","vbcvb","808"}));
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(null);
		System.out.println(arrayToString(",", list));
	}



	/**
	 * @Title: notSame
	 * @Description: 判断两个字符串的内容是否不相同，不相同返回true；
	 * @param dest
	 * @param scre
	 * @return boolean
	 */
	public static boolean notSame(String dest, String scre) {
		if (null == dest || null == scre) {
			return false;
		} else {
			return !dest.equals(scre);
		}
	}

	/**
	 * @Title: joinSortFieldOrder
	 * @Description: 为排序的查询拼接排序字段和排序类型
	 * @param sortField 参加排序的字段
	 * @param order 排序的类型
	 * @return: String
	 */
	public static String joinSortFieldOrder(String sortField, String order) {
		StringBuffer orderFiled = new StringBuffer("");
		if (isNotEmpty(sortField)) {
			orderFiled.append(sortField);
			if (isNotEmpty(order)) {
				orderFiled.append(" ").append(order);
			}
		}
		return orderFiled.toString();
	}

	/**
	 * @Title: joinSortFieldOrder
	 * @Description: 为多字段排序的查询拼接排序字段和排序类型
	 * @param sortFields 参加排序的字段数组
	 * @param orders 排序的类型数组
	 * @return: String
	 */
	public static String joinSortFieldOrder(String[] sortFields, String[] orders) {
		StringBuffer orderFiled = new StringBuffer("");
		if (ArrayUtil.isNotEmpty(sortFields)) {
			int sortFieldLength = sortFields.length;
			int ordersLength = 0;
			if(ArrayUtil.isNotEmpty(orders)){
				ordersLength = orders.length;
			}
			for (int i = 0; i < sortFieldLength; i++){
				orderFiled.append(sortFields[i]);
				if(ordersLength > 0 && i < ordersLength){
					orderFiled.append(" ").append(orders[i]);
				}
				if(i + 1 < sortFieldLength){
					orderFiled.append(SymbolType.COMMA);
				}
			}
		}
		return orderFiled.toString();
	}

	/**
	 * @Title: splitAndFilterString
	 * @Description: 去掉所有html元素
	 * @param input
	 * @return String
	 */
	public static String splitAndFilterString(String input) {
		if (isEmpty(input)) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
		return str.replaceAll("[(/>)<]", "");
	}

	/**
	 * 判断字符串包含非数字字符
	 *
	 * @param text
	 * @return true (含有非数字字符)
	 */
	public static boolean illegalNum(String text) {
		Matcher numbericMatcher = NUMBER_PATTERN.matcher(text);
		return !numbericMatcher.matches();
	}

	/**
	 * 比较两个字符串（大小写敏感）。
	 *
	 * <pre>
	 * equals(null, null)   = true
	 * equals(null, &quot;abc&quot;)  = false
	 * equals(&quot;abc&quot;, null)  = false
	 * equals(&quot;abc&quot;, &quot;abc&quot;) = true
	 * equals(&quot;abc&quot;, &quot;ABC&quot;) = false
	 * </pre>
	 *
	 * @param str1 要比较的字符串1
	 * @param str2 要比较的字符串2
	 *
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equals(CharSequence str1, CharSequence str2) {
		return equals(str1, str2, false);
	}

	/**
	 * 比较两个字符串（大小写不敏感）。
	 *
	 * <pre>
	 * equalsIgnoreCase(null, null)   = true
	 * equalsIgnoreCase(null, &quot;abc&quot;)  = false
	 * equalsIgnoreCase(&quot;abc&quot;, null)  = false
	 * equalsIgnoreCase(&quot;abc&quot;, &quot;abc&quot;) = true
	 * equalsIgnoreCase(&quot;abc&quot;, &quot;ABC&quot;) = true
	 * </pre>
	 *
	 * @param str1 要比较的字符串1
	 * @param str2 要比较的字符串2
	 *
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 */
	public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
		return equals(str1, str2, true);
	}

	/**
	 * 比较两个字符串是否相等。
	 *
	 * @param str1 要比较的字符串1
	 * @param str2 要比较的字符串2
	 * @param ignoreCase 是否忽略大小写
	 * @return 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
	 * @since 3.2.0
	 */
	public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
		if (null == str1) {
			// 只有两个都为null才判断相等
			return str2 == null;
		}
		if (null == str2) {
			// 字符串2空，字符串1非空，直接false
			return false;
		}

		if (ignoreCase) {
			return str1.toString().equalsIgnoreCase(str2.toString());
		} else {
			return str1.equals(str2);
		}
	}

	/**
	 * @Title: extendIds
	 * @Description: 两个以,拼接的字符串，返回一个去重后的字符串。
	 * @param ids1 以,拼接的字符串1
	 * @param ids2 以,拼接的字符串2
	 * @return String
	 */
	public static String extendIds(String ids1,String ids2){
		if(StringUtil.isEmpty(ids1)){
			return  ids2;
		}else if(StringUtil.isEmpty(ids2)){
			return ids1;
		}
		Set<String> strSet = new HashSet<>(Arrays.asList(ids1.split(",")));
		strSet.addAll(Arrays.asList(ids2.split(",")));
		return String.join(",",strSet.toArray(new String[strSet.size()]));
	}

	/**
	 * @Title: arrStrToLongArray
	 * @Description: 根据分隔符将字符串解析成Long数组
	 * @param arrStr 用分隔符拼接的字符串 eg：1,2,3
	 * @param delimiter 分隔符 eg：,或者_
	 * @return null
	 */
	public static long[] arrStrToLongArray(String arrStr,String delimiter) {
		if(StringUtil.isEmpty(arrStr)){
			return new long[0];
		}
		return Arrays.stream(arrStr.split(delimiter)).mapToLong(s -> Long.parseLong(s)).toArray();
	}

	/**
	 * @Title: arrStrToLongList
	 * @Description: 根据分隔符将字符串解析成LongList
	 * @param arrStr 用分隔符拼接的字符串 eg：1,2,3
	 * @param delimiter 分隔符 eg：,或者_
	 * @return null
	 */
	public static List<Long> arrStrToLongList(String arrStr,String delimiter) {
		if(StringUtil.isEmpty(arrStr)){
			return new ArrayList<Long>();
		}
		return Arrays.asList(arrStr.split(delimiter)).stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());
	}

	/**
	 * 数组中是否包含元素，忽略大小写
	 *
	 * @param array 数组
	 * @param value 被检查的元素
	 * @return 是否包含
	 * @since 3.1.2
	 */
	public static boolean containsIgnoreCase(CharSequence[] array, CharSequence value) {
		return indexOfIgnoreCase(array, value) > INDEX_NOT_FOUND;
	}

	/**
	 * 返回数组中指定元素所在位置，忽略大小写，未找到返回{@link #INDEX_NOT_FOUND}
	 *
	 * @param array 数组
	 * @param value 被检查的元素
	 * @return 数组中指定元素所在位置，未找到返回{@link #INDEX_NOT_FOUND}
	 * @since 3.1.2
	 */
	public static int indexOfIgnoreCase(CharSequence[] array, CharSequence value) {
		if (null != array) {
			for (int i = 0; i < array.length; i++) {
				if (StringUtil.equalsIgnoreCase(array[i], value)) {
					return i;
				}
			}
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * 是否包含字符串
	 * @param str 验证字符串
	 * @param strs 字符串组
	 * @return 包含返回true
	 */
	public static boolean inStringIgnoreCase(String str, String... strs){
		if (str != null && strs != null){
			for (String s : strs){
				if (str.equalsIgnoreCase(s.trim())){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 字符串拼接
	 * @Title: str1SplitStr2
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return String
	 */
	public static String str1SplitStr2(String str1, String str2){
		return String.format("%s_%s", str1, str2);
	}


}
