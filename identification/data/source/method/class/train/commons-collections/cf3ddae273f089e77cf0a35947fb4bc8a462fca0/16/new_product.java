public static <T> boolean addIgnoreNull(final Collection<T> collection, final T object) {
        Objects.requireNonNull(collection, "collection");
        return object != null && collection.add(object);
    }