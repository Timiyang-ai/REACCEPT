public static <I, O> Collection<O> collect(final Iterable<I> inputCollection,
                                               final Transformer<? super I, ? extends O> transformer) {
        final Collection<O> answer = inputCollection instanceof Collection<?> ?
                new ArrayList<>(((Collection<?>) inputCollection).size()) : new ArrayList<>();
        return collect(inputCollection, transformer, answer);
    }