public final static Map<String,String> normalize(Attributes att)
    {
        Set<Map.Entry<Object,Object>> entries = att.entrySet();
        Map<String,String> pristine = new HashMap<String,String>(entries.size());

        for(Map.Entry<Object,Object> entry : entries) {
            String key = entry.getKey().toString();
            String value = decode(entry.getValue().toString());
            pristine.put(key, value);
        }

        return pristine;
    }