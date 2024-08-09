public static <I, O> Collection<O> collect(final Iterator<I> inputIterator,
            final Transformer<? super I, ? extends O> transformer) {
        final ArrayList<O> answer = new ArrayList<O>();
        collect(inputIterator, transformer, answer);
        return answer;
    }