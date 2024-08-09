@Deprecated
    public static <C> boolean exists(final Iterable<C> input, final Predicate<? super C> predicate) {
        return predicate != null && IterableUtils.matchesAny(input, predicate);
    }