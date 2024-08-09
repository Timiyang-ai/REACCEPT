public static <T> Map<T, List<T>> group(T[] array){
        if (null == array){
            return Collections.emptyMap();
        }
        //视需求  可以换成 HashMap 或者TreeMap
        Map<T, List<T>> map = new WeakHashMap<T, List<T>>(array.length);
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