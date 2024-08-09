@SuppressWarnings("unchecked") // OK, empty collection is compatible with any type
    public static <T> Collection<T> emptyIfNull(final Collection<T> collection) {
        return collection == null ? EMPTY_COLLECTION : collection;
    }