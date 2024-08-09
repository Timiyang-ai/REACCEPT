public static <T> Map<T, T> arrayToMap(T[] list) {
        final HashMap<T, T> map = new HashMap<T, T>();
        if (list == null) {
            return map;
        }
        if (list.length > 0 && (list.length % 2) == 1) {
            throw new IllegalArgumentException("Array must be even in length, representing a series of Key, Value pairs.");
        }

        for (int i = 0; i < list.length; i++) {
            map.put(list[i], list[++i]);
        }

        return map;
    }