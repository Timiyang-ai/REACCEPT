public static String removeParameterList(URI uri,List<String> paramNameList,String charsetType){
        if (null == uri){
            return StringUtils.EMPTY;
        }
        String uriString = uri.toString();
        if (Validator.isNullOrEmpty(paramNameList)){// 如果 paramNameList是null原样返回
            return uriString;
        }
        String queryString = uri.getRawQuery();// 返回此URI的原始查询组成部分. URI的查询组成部分(如果定义了)只包含合法的 URI字符.
        return removeParameterList(uri.toString(), queryString, paramNameList, charsetType);
    }