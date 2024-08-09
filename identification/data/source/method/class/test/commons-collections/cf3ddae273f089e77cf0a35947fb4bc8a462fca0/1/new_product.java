@Deprecated
    public static <C> boolean exists(final Iterable<C> input, final Predicate<? super C> predicate) {
        return predicate == null ? false : IterableUtils.matchesAny(input, predicate);
    }