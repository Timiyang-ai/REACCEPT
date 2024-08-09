public static <I, O> Collection<O> collect(final Iterable<I> inputCollection,
            final Transformer<? super I, ? extends O> transformer) {
        final ArrayList<O> answer = new ArrayList<O>();
        collect(inputCollection, transformer, answer);
        return answer;
    }