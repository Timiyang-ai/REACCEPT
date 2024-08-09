@SafeVarargs
    public static <T> Vector<T> ofAll(T... elements) {
        Objects.requireNonNull(elements, "elements is null");
        HashArrayMappedTrie<Integer, T> result = HashArrayMappedTrie.empty();
        for (T element : elements) {
            result = result.put(result.size(), element);
        }
        return elements.length == 0 ? empty() : new Vector<>(result);
    }