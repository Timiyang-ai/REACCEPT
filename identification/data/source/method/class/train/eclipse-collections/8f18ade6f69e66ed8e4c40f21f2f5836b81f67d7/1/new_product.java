public static <T> Predicates<T> attributeNotIn(
            Function<? super T, ?> function,
            Iterable<?> iterable)
    {
        return new AttributePredicate<>(function, Predicates.notIn(iterable));
    }