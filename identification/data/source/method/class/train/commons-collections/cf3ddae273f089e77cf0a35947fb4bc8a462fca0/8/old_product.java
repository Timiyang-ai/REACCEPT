public static <C> int countMatches(final Iterable<C> input, final Predicate<? super C> predicate) {
        int count = 0;
        if (input != null && predicate != null) {
            for (final C o : input) {
                if (predicate.evaluate(o)) {
                    count++;
                }
            }
        }
        return count;
    }