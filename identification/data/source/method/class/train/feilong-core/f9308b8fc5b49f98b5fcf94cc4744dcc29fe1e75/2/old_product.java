public static <T> Map<String, T> toMap(String json,Class<T> clazz,Map<String, Class<?>> classMap){
        LOGGER.debug("in json:{}", json);

        Map<String, T> map = new HashMap<String, T>();

        JSONObject jsonObject = toJSONObject(json);
        @SuppressWarnings("unchecked")
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()){
            String key = keys.next();
            Object value = jsonObject.get(key);
            LOGGER.debug("key:[{}], value:{}", key, value);
            map.put(key, toBean(value, clazz, classMap));
        }
        return map;
    }