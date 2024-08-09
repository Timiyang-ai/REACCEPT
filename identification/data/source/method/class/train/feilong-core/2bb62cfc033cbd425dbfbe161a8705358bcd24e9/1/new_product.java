public static <T> Map<T, List<T>> group(T[] array){
        if (null == array){
            return Collections.emptyMap();
        }
        Map<T, List<T>> map = new LinkedHashMap<T, List<T>>(array.length);
        for (T t : array){
            List<T> valueList = map.get(t);
            if (null == valueList){
                valueList = new ArrayList<T>();
            }
            valueList.add(t);
            map.put(t, valueList);
        }
        return map;
    }