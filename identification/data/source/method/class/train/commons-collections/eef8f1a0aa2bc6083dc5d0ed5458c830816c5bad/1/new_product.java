public static <T> PredicatedCollection<T> predicatedCollection(final Collection<T> coll,
                                                                   final Predicate<? super T> predicate) {
        return new PredicatedCollection<>(coll, predicate);
    }