public static <C> boolean exists(Iterable<C> input, Predicate<? super C> predicate) {
        if (input != null && predicate != null) {
            for (C o : input) {
                if (predicate.evaluate(o)) {
                    return true;
                }
            }
        }
        return false;
    }