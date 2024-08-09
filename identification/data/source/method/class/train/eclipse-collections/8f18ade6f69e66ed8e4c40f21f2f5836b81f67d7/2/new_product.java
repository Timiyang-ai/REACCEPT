public static <T> Predicates<T> attributeIn(
            Function<? super T, ?> function,
            Iterable<?> iterable)
    {
        return new AttributePredicate<>(function, Predicates.in(iterable));
    }