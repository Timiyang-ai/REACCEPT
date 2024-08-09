public static <O> Collection<O> selectRejected(Collection<? extends O> inputCollection,
            Predicate<? super O> predicate) {
        return selectRejected(inputCollection, predicate, new ArrayList<O>(inputCollection.size()));
    }