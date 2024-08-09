public static <T> Predicates<T> attributeNotIn(
            Function<? super T, ?> function,
            Iterable<?> iterable)
    {
        return new AttributePredicate<T, Object>(function, Predicates.notIn(iterable));
    }