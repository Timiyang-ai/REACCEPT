public static <O, T> List<T> collect(final Iterable<O> inputIterable,final Transformer<? super O, ? extends T> transformer){
        return null == inputIterable ? Collections.<T> emptyList() : (List<T>) CollectionUtils.collect(inputIterable, transformer);
    }