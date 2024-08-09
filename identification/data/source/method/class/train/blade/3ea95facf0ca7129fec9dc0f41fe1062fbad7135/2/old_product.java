public static <T> Set<T> newSets(T... values) {
        return new HashSet<>(Arrays.asList(values));
    }