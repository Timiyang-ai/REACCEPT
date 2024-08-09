public static <T> T getValue(String baseName,String key,Class<T> typeClass){
        String value = getValue(baseName, key);
        return ConvertUtil.convert(value, typeClass);
    }