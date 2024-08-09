public static <I,O,T extends O> Collection<O> collect(Iterable<I> inputCollection, final Transformer<? super I,T> transformer, final Collection<O> outputCollection) {
        if (inputCollection != null) {
            return collect(inputCollection.iterator(), transformer, outputCollection);
        }
        return outputCollection;
    }