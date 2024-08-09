@SuppressWarnings("unchecked")
    public static <T> LinkedHashSet<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof LinkedHashSet) {
            return (LinkedHashSet<T>) elements;
        } else {
            final LinkedHashMap<T, T> mao = addAll(LinkedHashMap.empty(), elements);
            return mao.isEmpty() ? empty() : new LinkedHashSet<>(mao);
        }
    }