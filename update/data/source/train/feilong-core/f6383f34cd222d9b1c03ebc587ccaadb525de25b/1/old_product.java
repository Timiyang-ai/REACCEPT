public static Map<String, String> readPrefixAsMap(String baseName,String prefix,String spliter,Locale locale){
        Map<String, String> propertyMap = readAllPropertiesToMap(baseName, locale);
        if (Validator.isNullOrEmpty(propertyMap)){
            return Collections.emptyMap();
        }

        Map<String, String> result = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : propertyMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            // 以 prefix 开头
            if (key.startsWith(prefix)){
                String[] values = key.split(spliter);// 分隔
                if (values.length >= 2){
                    result.put(values[1], value);
                }
            }
        }
        return result;
    }