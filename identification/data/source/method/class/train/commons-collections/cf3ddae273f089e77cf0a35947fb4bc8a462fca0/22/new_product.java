public static <C> int countMatches(Iterable<C> input, Predicate<? super C> predicate) {
        int count = 0;
        if (input != null && predicate != null) {
            for (C o : input) {
                if (predicate.evaluate(o)) {
                    count++;
                }
            }
        }
        return count;
    }