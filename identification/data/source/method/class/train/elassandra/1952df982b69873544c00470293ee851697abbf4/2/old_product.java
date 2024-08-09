public static <T> Key<T> get(Class<T> type) {
        return new Key<T>(type, NullAnnotationStrategy.INSTANCE);
    }