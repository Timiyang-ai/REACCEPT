public static <O> Collection<O> selectRejected(final Collection<? extends O> inputCollection,
            final Predicate<? super O> predicate) {
        return selectRejected(inputCollection, predicate, new ArrayList<O>(inputCollection.size()));
    }