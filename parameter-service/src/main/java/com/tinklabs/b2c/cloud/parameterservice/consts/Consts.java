package com.tinklabs.b2c.cloud.parameterservice.consts;

import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Consts {
    public static class Http {
        public static final String ACCEPT = "Accept";
        public static final String APPLICATION_JSON = "application/json";
        public static final String CHARSET_PREFIX = "charset=";
        public static final String DEFAULT_RESPONSE_ENCODING = "UTF-8";
        public static final String ENCODING_UTF_8 = "UTF-8";
        public static final String KEY_SERVICE_TOKEN = "service-call-token";
        public static final String VALUE_SERVICE_TOKEN = "4d09a5553e772093a7fea071b54cc510";

        public static final String NO_ENCODING = "";
        public static final String NO_RESPONSE_MSG = "";

        public static final String NEW_LINE = "\n";

        public static final String HEADER_ACCESS_TOKEN = "access-token";
        public static final String HEADER_ACCEPT_VALUE = "application/json";

        public static final String HEADER_SERVICE_CALL_TOKEN = "service-call-token";
        public static final String SERVICE_CALL_TOKEN = "4d09a5553e772093a7fea071b54cc510";

        public static final String USER_TYPE_CLIENT = "client";
        public static final String USER_TYPE_EMPLOYEE = "employee";
        public static final Set<String> USER_TYPES = Sets.newHashSet(USER_TYPE_CLIENT, USER_TYPE_EMPLOYEE);
        // share below 2 keys with ACA developer
        public static final String ACCESS_KEY_API_ACA = "fb34e7cbca8bcac576a4ce31c9dc28ba";
        public static final String TOKEN_REFRESH_KEY_API_ACA = "b6f2331b5e252c024e51f9d238207923";
        // share below 2 keys with GIA developer
        public static final String ACCESS_KEY_API_GIA = "02833eeff87a4462bb0bdcac5f4ff837";
        public static final String TOKEN_REFRESH_KEY_API_GIA = "251a795191b04408b7d37b7ae7dc587e";
        // share below 2 keys with netsuite developer, direct access, no need to
        // refresh
        public static final String ACCESS_KEY_API_NETSUITE = "iem8hiramdneih0nn4eomkab1xqrduel";
        public static final String ACCESS_KEY_API_MRPRICE = "05hfzoy821fuvzcs3dilqid2foejt4mz";
        // share below 2 keys with AI IOS developer
        public static final String ACCESS_KEY_API_IOS = "z3b3by7dnj4ryr9aynw3pyph6awaaekx";
        public static final String TOKEN_REFRESH_KEY_API_IOS = "8pewmkphdm2nrfp4bcw2ge3628nxev9b";
        // share below 2 keys with AI Android developer
        public static final String ACCESS_KEY_API_ADRO = "urn59bsrhp9ykt7zh5zv8v75qj4gywcg";
        public static final String TOKEN_REFRESH_KEY_API_ADRO = "dek7ug2ybs2cxebsxshbb9wqprfsdmue";

        // client name lists that can call public api without user/employee
        // token
        // but they can have can only access specific apis, logic is in
        // TokenCheckInteceptor
        public static final String API_CALLER_ID_NETSUITE = "net-suite";
        public static final String API_CALLER_ID_MRPRICE = "mrprice";
        public static final Set<String> API_DIRECT_CALL_CLIENTS = new HashSet<>(Arrays.asList(API_CALLER_ID_NETSUITE, API_CALLER_ID_MRPRICE ));

        public static final String PUBLIC_API_ACCESS_TOKEN_HEADER = "ai-api-access-token";
        public static final String PUBLIC_API_TOKEN_REFRESH_KEY_HEADER = "ai-api-refresh-key";
        public static final String PUBLIC_API_CALLER_ID_KEY = "ai-api-caller-id";
        // all access token allowed
        public static final Map<String, String> PUBLIC_API_ACCESS_TOKENS = new HashMap<String, String>() {
            {
                put(ACCESS_KEY_API_ACA, TOKEN_REFRESH_KEY_API_ACA);
                put(ACCESS_KEY_API_GIA, TOKEN_REFRESH_KEY_API_GIA);
                put(ACCESS_KEY_API_NETSUITE, "");
                put(ACCESS_KEY_API_IOS, TOKEN_REFRESH_KEY_API_IOS);
                put(ACCESS_KEY_API_ADRO, TOKEN_REFRESH_KEY_API_ADRO);
                put(ACCESS_KEY_API_MRPRICE, "");
            }
        };
        // public static final Set<String> PUBLIC_API_ACCESS_TOKENS = new
        // HashSet<>(
        // Arrays.asList(ACCESS_KEY_API_ACA, ACCESS_KEY_API_GIA,
        // ACCESS_KEY_API_NETSUITE,
        // ACCESS_KEY_API_IOS, ACCESS_KEY_API_ADRO));

        public static final String PUBLIC_API_USER_RESOURCE_URL_PREFIX = "/user/";
        public static final String PUBLIC_API_USER_RESOURCE_URL_PREFIX_V2 = "/user/v2/";
        public static final String PUBLIC_API_FINANCE_RESOURCE_URL_PREFIX = "/finance/";
        public static final String PUBLIC_API_MRPRICE_RESOURCE_URL_PREFIX = "/mrprice/";

        public static final String HEADER_SERVICE_CALL_TOKEN_MOBILE = "service-call-token-mob";
        public static final String SERVICE_CALL_TOKEN_MOBILE = "m4d09a5553e772093oa7fea071b54cc510b";

        public static final String HEADER_GRANT_TICKET_MOBILE = "tgt-mob";

    }

    public class PathParam {
        public static final String PATH_PARAM_SEPARATOR = "\\?";
        public static final String EQUAL = "=";
        public static final String PARAM_SEPARATOR = "&";
        public static final String EMPTY_STR = "";
    }

    public static final int UUID_MAX_LEN = 36;

    public static final String UTF_8 = "UTF-8";

    public static final String ORACLE_PUNCT = "[[:punct:]]";

    public static final String SINGLE_QUOTES = "'";

    public static final String TWO_SINGLE_QUOTES = "''";

    public static final String ORACLE_PUNCTUATION_SYMBOLS_PATTERN = "[%.,\"'?!:#$&()*;+-/<>=@\\[\\]\\^_{}|~]";

    public static final String COMMA = ",";
    public static final String COLON = ":";

    public static final String FALSE = "No";
    public static final String TRUE = "Yes";

    public static final String DELETED = "deleted";
    public static final String ACTIVE = "active";

    public static final int SUPPLIER_NOT_NEW_AFTER_X_DAYS = 30;

    public static final String DELIMITER_COMMA = "\\,";

    public static final String SEMICOLON = ";";

    public static final String DASH = "-";

    public static final String DOUBLE_QUOTE = "\"";

    public static final String SLASH = "/";

    public static final String DOT = ".";

    public static final String PERCENT_SIGN = "%";
    public static final String DOUBLE_PERCENT_SIGN = "%%";

    public static final int CONNECTION_TIMEOUT = (int) TimeUnit.SECONDS.toSeconds(60); // 60
    // seconds
    public static final int SO_TIMEOUT = (int) TimeUnit.MINUTES.toSeconds(15);

    public static final String DOWNLOAD_ERROR_FILENAME = "error.txt";
    public static final String DOWNLOAD_ERROR_MSG = "Cannot download the file, please contact the IT department!";

    public static final String UPLOAD_ERROR_MSG = "Error upload the file, please contact the IT department!";
    public static final String DELETE_ERROR_MSG = "Error delete the file, please contact the IT department!";

    public static final String SPACE = " ";

    public static final String YES = "YES";
    public static final String NO = "NO";

    public static final String SYSTEM = "System";

    public static final String EVEN = "even";
    public static final String ODD = "odd";

    public static final String ONE = "1";
    public static final String ZERO = "0";
    public static final String NEGATIVE_ONE = "-1";

    public static final String UNDERLINE = "_";

    public static final String NA = "NA";

    public static final String NULL = "NULL";

    public static final String SUFFIX_START = "_START";

    public static final String SUFFIX_END = "_END";

    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";

    public static final String PAYMENT_PAYPAL = "Paypal";
    public static final String PAYMENT_GLOBAL_PAYMENT = "GlobalPayment";
}
