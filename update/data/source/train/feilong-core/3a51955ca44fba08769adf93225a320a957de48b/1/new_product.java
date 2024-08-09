@SuppressWarnings("unchecked")
    public static <T> Map<String, T> toMap(String json,Class<T> rootClass,Map<String, Class<?>> classMap){
        LOGGER.debug("in json:[{}],klass:[{}]", json, rootClass);

        if (Validator.isNullOrEmpty(json)){
            return Collections.emptyMap();
        }

        Map<String, T> map = new HashMap<String, T>();

        JSONObject jsonObject = toJSONObject(json);
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()){
            String key = keys.next();
            Object value = jsonObject.get(key);
            LOGGER.debug("key:[{}], value:[{}],value type is:[{}]", key, value, value.getClass().getName());
            map.put(key, null == rootClass ? (T) value : toBean(value, rootClass, classMap));//如果klass是null,表示不需要转换
        }
        return map;
    }