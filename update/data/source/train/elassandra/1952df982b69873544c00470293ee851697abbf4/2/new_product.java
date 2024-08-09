public static <T> Key<T> get(Class<T> type) {
        return new Key<>(type, NullAnnotationStrategy.INSTANCE);
    }