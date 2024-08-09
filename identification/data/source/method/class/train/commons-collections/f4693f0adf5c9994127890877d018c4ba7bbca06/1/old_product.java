public static <E> long countMatches(final Iterable<E> input, final Predicate<? super E> predicate) {
        if (predicate == null) {
            throw new NullPointerException("Predicate must not be null.");
        }
        return size(filteredIterable(emptyIfNull(input), predicate));
    }