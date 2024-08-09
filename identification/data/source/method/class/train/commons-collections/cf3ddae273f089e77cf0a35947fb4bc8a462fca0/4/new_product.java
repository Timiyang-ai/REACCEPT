public static <O> Collection<O> selectRejected(final Iterable<? extends O> inputCollection,
                                                   final Predicate<? super O> predicate) {
        final Collection<O> answer = inputCollection instanceof Collection<?> ?
                new ArrayList<>(((Collection<?>) inputCollection).size()) : new ArrayList<>();
        return selectRejected(inputCollection, predicate, answer);
    }