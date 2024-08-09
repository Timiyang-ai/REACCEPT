public static <O, R extends Collection<? super O>> R selectRejected(
            Collection<? extends O> inputCollection, Predicate<? super O> predicate, R outputCollection) {
        if (inputCollection != null && predicate != null) {
            for (O item : inputCollection) {
                if (!predicate.evaluate(item)) {
                    outputCollection.add(item);
                }
            }
        }
        return outputCollection;
    }