public static <T> Collection<T> emptyIfNull(final Collection<T> collection) {
        return collection == null ? CollectionUtils.<T>emptyCollection() : collection;
    }