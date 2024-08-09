public static <O> Collection<O> select(final Collection<? extends O> inputCollection,
            final Predicate<? super O> predicate) {
        return select(inputCollection, predicate, new ArrayList<O>(inputCollection.size()));
    }