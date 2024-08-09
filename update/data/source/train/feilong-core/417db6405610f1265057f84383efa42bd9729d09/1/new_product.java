public static String[] tokenizeToStringArray(String str,String delimiters,boolean trimTokens,boolean ignoreEmptyTokens){
        if (null == str){
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }
        //StringTokenizer implements Enumeration<Object>
        //其在 Enumeration接口的基础上,  定义了 hasMoreTokens nextToken两个方法
        //实现的Enumeration接口中的  hasMoreElements nextElement 调用了  hasMoreTokens nextToken
        StringTokenizer stringTokenizer = new StringTokenizer(str, delimiters);
        List<String> tokens = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()){
            String token = stringTokenizer.nextToken();
            if (trimTokens){
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0){
                tokens.add(token);
            }
        }
        return ConvertUtil.toArray(tokens, String.class);
    }