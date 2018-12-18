package com.tinklabs.b2c.cloud.parameterservice.util;

import com.tinklabs.b2c.cloud.parameterservice.consts.Consts;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang.StringUtils {
    private static final Pattern EMPTY_STRING_Pattern = Pattern.compile("\\s*");

    private static String[] strArr = {
            "0","1","2","3","4","5","6","7","8","9",
            "a","b","c","d","e","f","g","h","i","j",
            "k","l","m","n","o","p","q","r","s","t",
            "u","v","w","x","y","z","A","B","C","D"
    };

    /**
     * Decodes a string using UTF-8 encoding scheme.
     *
     * @author Alva Xie
     * @param s
     *            the string to decode
     * @return the newly decoded string
     */
    public static String decode(String s) {
        try {
            s = decode(s, Consts.UTF_8);
        } catch (UnsupportedEncodingException e) {
            // should never come here
            String errorMsg = toString("Decode string:", s,
                    ", using UTF-8, exception:", e.getMessage());
            System.err.println(errorMsg);
        }

        return s;
    }

    /**
     * Decodes a string using a specific encoding scheme.
     *
     * @author Alva Xie
     * @param s
     *            the string to decode
     * @param enc
     *            the name of character encoding
     * @return the newly decoded string
     * @throws UnsupportedEncodingException
     */
    public static String decode(String s, String enc)
            throws UnsupportedEncodingException {
        if (isBlank(enc)) {
            enc = Consts.UTF_8;
        }

        return (isNotEmpty(s)) ? URLDecoder.decode(s, enc) : s;
    }

    /**
     * Encodes a string using UTF-8 encoding scheme.
     *
     * @author Alva Xie
     * @param s
     *            the string to encode
     * @return the newly encoded string
     */
    public static String encode(String s) {
        try {
            s = encode(s, Consts.UTF_8);
        } catch (UnsupportedEncodingException e) {
            // should never come here
            String errorMsg = toString("Encode string:", s,
                    ", using UTF-8, exception:", e.getMessage());
            System.err.println(errorMsg);
        }

        return s;
    }

    /**
     * Encodes a string using a specific encoding scheme.
     *
     * @author Alva Xie
     * @param s
     *            the string to encode
     * @param enc
     *            the name of character encoding
     * @return the newly encoded string
     * @throws UnsupportedEncodingException
     */
    public static String encode(String s, String enc)
            throws UnsupportedEncodingException {
        if (isBlank(enc)) {
            enc = Consts.UTF_8;
        }

        return (isNotEmpty(s)) ? URLEncoder.encode(s, Consts.UTF_8) : s;
    }

    /**
     * Escape oracle punctuation symbols
     *
     * @author Alva Xie
     * @param s
     *            the input string
     * @return the newly escaped string
     */
    public static String escapeOraclePunctuationSymbols(String s) {
        if (isEmpty(s)) {
            return s;
        }

        s = s.replaceAll(Consts.ORACLE_PUNCTUATION_SYMBOLS_PATTERN,
                Consts.ORACLE_PUNCT);

        return s;
    }

    /**
     * Convert a string delimiter list to a set.
     *
     * @author Alva Xie
     * @param s
     *            the input string
     * @param delim
     *            the delimiter to use
     * @return a set of string entries in the list
     */
    public static Set<String> toSet(String s, String delim) {
        if (isEmpty(s)) {
            return Collections.emptySet();
        }

        Set<String> set = new HashSet<String>();

        String[] tokens = s.split(delim);
        for (String token : tokens) {
            set.add(token);
        }

        return set;
    }

    /**
     * Convert a string delimiter list to a list.
     *
     * @author Alva Xie
     * @param s
     *            the input string
     * @param delim
     *            the delimiter to use
     * @return a list of string entries in the list
     */
    public static List<String> toList(String s, String delim) {
        if (isEmpty(s)) {
            return Collections.emptyList();
        }

        List<String> list = new ArrayList<String>();

        String[] tokens = s.split(delim);
        for (String token : tokens) {
            list.add(token);
        }

        return list;
    }

    /**
     * Convert a collection to a SQL in clause string
     *
     * @author Alva Xie
     * @param coll
     *            the input collection
     * @return a SQL in clause string
     */
    public static String collectionToSqlInclauseString(Collection<String> coll) {
        return org.springframework.util.StringUtils
                .collectionToDelimitedString(coll, ", " + "",
                        Consts.SINGLE_QUOTES, Consts.SINGLE_QUOTES);
    }

    /**
     * Outputs an array as a String, treating empty as an empty array.
     *
     * @author Alva Xie
     * @param strs
     *            the array to get a toString for
     * @return a String representation of the array, empty if null array input
     */
    public static String toString(String... strs) {
        if (strs == null) {
            return EMPTY;
        }

        List<String> list = Arrays.asList(strs);

        return org.springframework.util.StringUtils
                .collectionToDelimitedString(list, EMPTY);
    }

    /**
     * Convert a collection to a string
     *
     * @author Alva Xie
     * @param coll
     *            the input collection
     * @return the newly created string from input collection
     */
    public static String toString(Collection<?> coll) {
        return toString(coll, ", ");
    }

    /**
     * Convert a collection to a delimited string
     *
     * @author Alva Xie
     * @param coll
     *            the input collection
     * @param delim
     *            the delimiter to use
     * @return the newly created string from input collection
     */
    public static String toString(Collection<?> coll, String delim) {
        return org.springframework.util.StringUtils
                .collectionToDelimitedString(coll, delim);
    }

    /**
     * Outputs an array as a String, treating empty as an empty array.
     *
     * @author Keson
     * @param delim
     * @param strs
     *            the array to get a toString for
     * @return a String representation of the array, empty if null array input
     */
    public static String toStringWithDelim(String delim, String... strs) {
        if (strs == null) {
            return EMPTY;
        }

        List<String> list = Arrays.asList(strs);

        return org.springframework.util.StringUtils
                .collectionToDelimitedString(list, delim);
    }

    /**
     * Check whether the string array is empty or not
     *
     * @author Keson.Liu
     * @param strs
     * @return true if not all element in the array are empty
     */
    public static boolean isNotAllEmpty(String... strs) {
        for (String str : strs) {
            if (isNotBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether some String in array is empty or not
     *
     * @author Keson.Liu
     * @param strs
     * @return true if not all element in the array are empty
     */
    public static boolean isAllNotEmpty(String... strs) {
        for (String str : strs) {
            if (isBlank(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether the string array is empty or not
     *
     * @author Keson.Liu
     * @param strs
     * @return true if all element in the array are empty
     */
    public static boolean isAllEmpty(String... strs) {
        return !isNotAllEmpty(strs);
    }

    /**
     * Convert String to Set<String> without blank String according to the
     * specific seperator.
     *
     * @author Keson.Liu
     * @param src
     * @param delim
     * @return
     */
    public static Set<String> toNoBlankSet(String src, String delim) {
        Set<String> set = new HashSet<String>();
        if (isBlank(src)) {
            System.err.println("input src is empty");
        } else if (delim == null) {
            set.add(src);
        } else {
            String[] elements = src.split(delim);
            for (String element : elements) {
                if (isNotBlank(element)) {
                    set.add(element.trim());
                }
            }
        }
        return set;
    }

    /**
     * If the input string has single quotes, then convert one single quotes to
     * two.
     *
     * @author Alva Xie
     * @param s
     *            the input string
     * @return the newly converted string
     */
    public static String convertSingleQuotesToTwo(String s) {
        if (isEmpty(s)) {
            return s;
        }

        s = s.replaceAll(Consts.SINGLE_QUOTES, Consts.TWO_SINGLE_QUOTES);

        return s;
    }

    public static <T> String arraytoString(T[] arr) {
        if (arr == null) {
            return "null";
        } else if (arr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T obj : arr) {
            sb.append(String.valueOf(obj)).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String[] getSqlBindingVariables(String sql) {
        if (isBlank(sql)) {
            return null;
        } else {
            List<String> bindingVariableList = new ArrayList<String>();
            Pattern pattern = Pattern.compile(":[_a-zA-Z\\-]+");
            Matcher matcher = pattern.matcher(sql);
            while (matcher.find()) {
                bindingVariableList.add(matcher.group().substring(1));
            }
            String[] bindingVariableArray = new String[bindingVariableList
                    .size()];
            return bindingVariableList.toArray(bindingVariableArray);
        }
    }

    /**
     * To test whether a string is null or empty.
     *
     * @param str
     *            : The string to be tested.
     * @return: return true if str is null or is empty or is composed by
     *          white-spaces.
     */
    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        } else if (EMPTY_STRING_Pattern.matcher(str).matches()) {
            return true;
        } else if ("null".equalsIgnoreCase(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param s
     * @return
     */
    public static String trim(String s) {
        if (s == null) {
            return null;
        }
        return s.trim();
    }

    /**
     * To upper case.
     *
     * @param s
     * @return
     */
    public static String upper(String s) {
        if (s == null) {
            return null;
        }
        return s.toUpperCase();
    }

    /**
     * To upper case.
     *
     * @param s
     * @return
     */
    public static String lower(String s) {
        if (s == null) {
            return null;
        }
        return s.toLowerCase();
    }

    /**
     * @param val
     * @return
     */
    public static boolean isTrue(String val) {
        if (isNotEmpty(val)
                && (val.equalsIgnoreCase("Yes") || val.equalsIgnoreCase("True")
                || val.equals("1") || val.equalsIgnoreCase("Y"))) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Return Yes/No
     * @param check
     * @return
     */
    public static String getYesNo(String check){
        return check.equalsIgnoreCase("true") ? "Yes" : "No";
    }

    /**
     * Return Y/N
     * @return
     */
    public static String getYN(String val){
        if (isNotEmpty(val)
                && (val.equalsIgnoreCase("Yes") || val.equalsIgnoreCase("True")
                || val.equals("1") || val.equalsIgnoreCase("Y"))) {
            return "Y";
        } else {
            return "N";
        }
    }


    /**
     * Return Y/N
     * @param val
     * @return
     */
    public static String getYN(boolean val){
        return val ? "Y" : "N";
    }

    /**
     * Return Yes/No
     * @param check
     * @return
     */
    public static String getYesNo(boolean check){
        return check ? "Yes" : "No";
    }

    /**
     * Return 1 or 0
     * @param check
     * @return
     */
    public static String getOneZero(String check) {
        return check.equalsIgnoreCase("true") ? "1" : "0";
    }

    /**
     * Remove unused decimal, e.g. 3.50 -> 3.5, 3.0400->3.04
     *
     * @param str
     * @return
     */
    public static String trimUnusedDecimal(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        if (str.indexOf(".") > 0) {
            str = str.replaceAll("0+?$", "");
            str = str.replaceAll("[.]$", "");
        }
        return str;
    }

    /**
     * Encode input string with charset 'utf-8', and replace + sign
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodePlus(String str)
            throws UnsupportedEncodingException {
        if (str != null) {
            str = URLEncoder.encode(str, "UTF-8").replace("+", "%20");
        }

        return str;
    }

    /**
     * @param array
     * @return
     */
    public static String[] removeDuplicateStrings(String[] array) {
        if (isEmptyArray(array)) {
            return array;
        }
        Set<String> set = new TreeSet<String>();
        for (String element : array) {
            set.add(element);
        }
        return toStringArray(set);
    }

    public static boolean isEmptyArray(Object[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * Copy the given Collection into a String array. The Collection must
     * contain String elements only.
     *
     * @param collection
     *            the Collection to copy
     * @return the String array ({@code null} if the passed-in Collection was
     *         {@code null})
     */
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new String[collection.size()]);
    }

    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder())
                    .append(Character.toUpperCase(s.charAt(0)))
                    .append(s.substring(1)).toString();
        }
    }

    /**
     * Return String
     * @param val
     * @author : Clover Pang
     * @purpose : remove the input string all blank, special word and trim it
     */
    public static String removeSpecialCharacter(String val){
        if (isNotEmpty(val)){
            //remove special word
            String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(val);
            //remove all blank and uppercase
            return m.replaceAll("").replaceAll("\\s*", "").trim();
        } else {
            return "";
        }
    }

    public static String characterEncode (String str){
        if(!isEmpty(str))str = str.replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9`~!@#$%^&*()-_+= |{}':;,\\[\\].<>?！￥…（）—【】‘；：”“’《》·，、？\"]", "");
        return str;
    }

    /**
     * 获取一个随机字符
     * @return
     */
    public static String getRandomChar() {
        return strArr[(int)(Math.random()*strArr.length)];
    }

    /**
     * 获取一个随机字符串
     * @param length 字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            sb.append(getRandomChar());
        }
        return sb.toString();
    }
}
