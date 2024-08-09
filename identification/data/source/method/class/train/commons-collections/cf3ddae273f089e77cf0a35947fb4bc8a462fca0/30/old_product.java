public static <I, O> Collection<O> collect(Iterator<I> inputIterator,
            Transformer<? super I, ? extends O> transformer) {
        ArrayList<O> answer = new ArrayList<O>();
        collect(inputIterator, transformer, answer);
        return answer;
    }