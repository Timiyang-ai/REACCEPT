@SafeVarargs
    public static <T> LinkedHashSet<T> of(T... elements) {
        Objects.requireNonNull(elements, "elements is null");
        LinkedHashMap<T, T> map = LinkedHashMap.empty();
        for (T element : elements) {
            map = map.put(element, element);
        }
        return new LinkedHashSet<>(map);
    }