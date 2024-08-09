public static <I,O> Collection<O> collect(Iterable<I> inputCollection, Transformer<? super I, ? extends O> transformer) {
        ArrayList<O> answer = new ArrayList<O>();
        collect(inputCollection, transformer, answer);
        return answer;
    }