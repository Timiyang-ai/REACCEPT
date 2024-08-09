@SuppressWarnings("unchecked")
    public static <T> Map<String, T> toMap(String json,Class<T> clazz,Map<String, Class<?>> classMap){
        LOGGER.debug("in json:{}", json);

        if (Validator.isNullOrEmpty(json)){
            return Collections.emptyMap();
        }

        Map<String, T> map = new HashMap<String, T>();

        JSONObject jsonObject = toJSONObject(json);
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()){
            String key = keys.next();
            Object value = jsonObject.get(key);
            LOGGER.debug("key:[{}], value:{}", key, value);

            if (null != clazz){
                map.put(key, toBean(value, clazz, classMap));
            }else{//如果clazz是null,表示不需要转换
                map.put(key, (T) value);
            }
        }
        return map;
    }