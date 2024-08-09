public static <E extends Enum<E>> Map<String, Enum<E>> getEnumMap(Class<E> enumClass) {
        Map<String, Enum<E>> map = new LinkedHashMap<String, Enum<E>>();

        for (E e: EnumSet.allOf(enumClass)) {
            map.put(e.name(), e);
        }

        return map;
    }