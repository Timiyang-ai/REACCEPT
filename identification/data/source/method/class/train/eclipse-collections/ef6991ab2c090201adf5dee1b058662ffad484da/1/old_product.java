public static <T> Optional<T> detectOptional(Iterable<T> iterable, Predicate<? super T> predicate)
    {
        return Optional.ofNullable(detect(iterable, predicate));
    }