public static Map<String, Object> toMap(String json){
        LOGGER.debug("in json:{}", json);

        Map<String, Object> map = new HashMap<String, Object>();

        JSONObject jsonObject = toJSONObject(json);
        @SuppressWarnings("unchecked")
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()){
            String key = keys.next();
            Object value = jsonObject.get(key);
            LOGGER.debug("key:[{}], value:{}", key, value);
            map.put(key, value);
        }
        return map;
    }