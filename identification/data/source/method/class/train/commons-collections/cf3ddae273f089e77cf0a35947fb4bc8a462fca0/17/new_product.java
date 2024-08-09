@Deprecated
    public static <C> int countMatches(final Iterable<C> input, final Predicate<? super C> predicate) {
        return predicate == null ? 0 : (int) IterableUtils.countMatches(input, predicate);
    }