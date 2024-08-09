public static <O> Collection<O> select(Collection<? extends O> inputCollection,
            Predicate<? super O> predicate) {
        return select(inputCollection, predicate, new ArrayList<O>(inputCollection.size()));
    }