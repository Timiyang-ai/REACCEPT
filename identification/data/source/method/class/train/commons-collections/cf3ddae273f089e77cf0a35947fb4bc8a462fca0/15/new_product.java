public static <I, O> Collection<O> collect(final Iterator<I> inputIterator,
            final Transformer<? super I, ? extends O> transformer) {
        return collect(inputIterator, transformer, new ArrayList<O>());
    }