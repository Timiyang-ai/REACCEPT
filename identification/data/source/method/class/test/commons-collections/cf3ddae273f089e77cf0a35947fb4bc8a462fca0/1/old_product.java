public static <C> boolean exists(final Iterable<C> input, final Predicate<? super C> predicate) {
        if (input != null && predicate != null) {
            for (final C o : input) {
                if (predicate.evaluate(o)) {
                    return true;
                }
            }
        }
        return false;
    }