public static <T> boolean addIgnoreNull(Collection<T> collection, T object) {
        if (collection == null) {
            throw new NullPointerException("The collection must not be null");
        }
        return object != null && collection.add(object);
    }