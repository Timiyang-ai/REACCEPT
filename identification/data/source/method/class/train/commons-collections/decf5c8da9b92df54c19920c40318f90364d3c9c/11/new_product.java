public static <K> Number getNumber(Map<? super K, ?> map, K key, Number defaultValue) {
        Number answer = getNumber(map, key);
        if (answer == null) {
            answer = defaultValue;
        }
        return answer;
    }