public static Map<String, Object> toMap(String json){
        JSONObject jsonObject = toJSONObject(json);

        //TODO 处理不了key 是 null 的情况
        //java.lang.ClassCastException: net.sf.json.JSONNull cannot be cast to java.lang.String
        //Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(jsonObject, Map.class);

        Map<String, Object> map = new HashMap<String, Object>();

        @SuppressWarnings("unchecked")
        Iterator<String> keys = jsonObject.keys();

        while (keys.hasNext()){
            String key = keys.next();
            Object value = jsonObject.get(key);
            map.put(key, value);
        }
        return map;
    }