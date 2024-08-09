public static String combineQueryString(Map<String, String[]> paramsMap,String charsetType){
        if (Validator.isNullOrEmpty(paramsMap)){
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();

        int i = 0;
        int size = paramsMap.size();
        for (Map.Entry<String, String[]> entry : paramsMap.entrySet()){
            String key = entry.getKey();
            String[] paramValues = entry.getValue();

            // **************************************************************
            if (Validator.isNotNullOrEmpty(charsetType)){
                // 统统先强制 decode再 encode,浏览器兼容问题
                key = URIUtil.encode(URIUtil.decode(key, charsetType), charsetType);
            }
            // **************************************************************
            if (Validator.isNullOrEmpty(paramValues)){
                LOGGER.warn("the param key:[{}] value is null", key);
                sb.append(key).append("=").append("");
            }else{
                List<String> paramValueList = null;
                if (Validator.isNotNullOrEmpty(charsetType)){
                    paramValueList = new ArrayList<String>();
                    for (String value : paramValues){
                        if (Validator.isNotNullOrEmpty(value)){
                            // 浏览器传递queryString()参数差别(浏览器兼容问题);chrome会将query进行 encoded再发送请求;而ie原封不动的发送
                            // 由于暂时不能辨别是否encoded过,所以先强制decode再encode;此处不能先转decode(query,charsetType),参数就是想传 =是转义符
                            paramValueList.add(URIUtil.encode(URIUtil.decode(value.toString(), charsetType), charsetType));
                        }else{
                            paramValueList.add("");
                        }
                    }
                }else{
                    paramValueList = ArrayUtil.toList(paramValues);
                }

                for (int j = 0, z = paramValueList.size(); j < z; ++j){
                    String value = paramValueList.get(j);
                    sb.append(key).append("=").append(value);
                    if (j != z - 1){// 最后一个& 不拼接
                        sb.append(URIComponents.AMPERSAND);
                    }
                }
            }
            if (i != size - 1){// 最后一个& 不拼接
                sb.append(URIComponents.AMPERSAND);
            }
            ++i;
        }
        return sb.toString();
    }