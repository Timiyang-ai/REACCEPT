@SuppressWarnings("unchecked")
    public static <K, T extends Number> T getMinValue(Map<K, T> map,K...keys){
        Map<K, T> subMap = getSubMap(map, keys);

        if (Validator.isNullOrEmpty(subMap)){
            return null;
        }

        Collection<T> values = subMap.values();
        Object[] array = values.toArray();
        Arrays.sort(array);
        return (T) array[0];
    }