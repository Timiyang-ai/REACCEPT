@SuppressWarnings("unchecked")
    public static <T> Collection<T> emptyIfNull(final Collection<T> collection) {
        return collection == null ? EMPTY_COLLECTION : collection;
    }