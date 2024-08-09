public static <E> long countMatches(final Iterable<E> input, final Predicate<? super E> predicate) {
        Objects.requireNonNull(predicate, "predicate");
        return size(filteredIterable(emptyIfNull(input), predicate));
    }