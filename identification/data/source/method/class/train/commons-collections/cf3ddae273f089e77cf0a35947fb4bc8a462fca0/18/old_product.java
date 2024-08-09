public static <T> boolean addIgnoreNull(Collection<T> collection, T object) {
        return (object != null && collection.add(object));
    }