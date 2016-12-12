/*
 *
 *  *
 *  *  *
 *  *  *  * ===================================
 *  *  *  * Copyright (c) 2016.
 *  *  *  * 作者：安卓猴
 *  *  *  * 微博：@安卓猴
 *  *  *  * 博客：http://sunjiajia.com
 *  *  *  * Github：https://github.com/opengit
 *  *  *  *
 *  *  *  * 注意**：如果您使用或者修改该代码，请务必保留此版权信息。
 *  *  *  * ===================================
 *  *  *
 *  *  *
 *  *
 *
 */

package com.sunjiajia.beautifullife.utils;

import com.rop.RopException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lijing on 2016/12/8.
 */

public class RopUtils {



    public RopUtils() {
    }

    public static String sign(Map<String, Object> paramValues, String secret) {
        return sign(paramValues, (List)null, secret);
    }
    public static String signString(Map<String, String> paramValues, String secret) {
        return signString(paramValues, (List)null, secret);
    }

    public static String sign(Map<String, Object> paramValues, List<String> ignoreParamNames, String secret) {
        try {
            StringBuilder e = new StringBuilder();
            ArrayList paramNames = new ArrayList(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            Iterator sha1Digest;
            String paramName;
            if(ignoreParamNames != null && ignoreParamNames.size() > 0) {
                sha1Digest = ignoreParamNames.iterator();

                while(sha1Digest.hasNext()) {
                    paramName = (String)sha1Digest.next();
                    paramNames.remove(paramName);
                }
            }

            Collections.sort(paramNames);
            e.append(secret);
            sha1Digest = paramNames.iterator();

            while(sha1Digest.hasNext()) {
                paramName = (String)sha1Digest.next();
                e.append(paramName).append((String)paramValues.get(paramName));
            }

            e.append(secret);
            byte[] sha1Digest1 = getSHA1Digest(e.toString());
            return byte2hex(sha1Digest1);
        } catch (IOException var7) {
            throw new RopException(var7);
        }
    }

    public static String signString(Map<String, String> paramValues, List<String> ignoreParamNames, String secret) {
        try {
            StringBuilder e = new StringBuilder();
            ArrayList paramNames = new ArrayList(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            Iterator sha1Digest;
            String paramName;
            if(ignoreParamNames != null && ignoreParamNames.size() > 0) {
                sha1Digest = ignoreParamNames.iterator();

                while(sha1Digest.hasNext()) {
                    paramName = (String)sha1Digest.next();
                    paramNames.remove(paramName);
                }
            }

            Collections.sort(paramNames);
            e.append(secret);
            sha1Digest = paramNames.iterator();

            while(sha1Digest.hasNext()) {
                paramName = (String)sha1Digest.next();
                e.append(paramName).append((String)paramValues.get(paramName));
            }

            e.append(secret);
            byte[] sha1Digest1 = getSHA1Digest(e.toString());
            return byte2hex(sha1Digest1);
        } catch (IOException var7) {
            throw new RopException(var7);
        }
    }

    public static String utf8Encoding(String value, String sourceCharsetName) {
        try {
            return new String(value.getBytes(sourceCharsetName), "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    private static byte[] getSHA1Digest(String data) throws IOException {
        Object bytes = null;

        try {
            MessageDigest gse = MessageDigest.getInstance("SHA-1");
            byte[] bytes1 = gse.digest(data.getBytes("UTF-8"));
            return bytes1;
        } catch (GeneralSecurityException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private static byte[] getMD5Digest(String data) throws IOException {
        Object bytes = null;

        try {
            MessageDigest gse = MessageDigest.getInstance("MD5");
            byte[] bytes1 = gse.digest(data.getBytes("UTF-8"));
            return bytes1;
        } catch (GeneralSecurityException var3) {
            throw new IOException(var3.getMessage());
        }
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();

        for(int i = 0; i < bytes.length; ++i) {
            String hex = Integer.toHexString(bytes[i] & 255);
            if(hex.length() == 1) {
                sign.append("0");
            }

            sign.append(hex.toUpperCase());
        }

        return sign.toString();
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase();
    }
}
