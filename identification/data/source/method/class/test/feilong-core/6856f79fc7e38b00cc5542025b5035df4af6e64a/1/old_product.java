@SuppressWarnings("unchecked")
    public static <O, T> List<T> collect(final Iterable<O> inputIterable,final Transformer<? super O, ? extends T> transformer){
        return null == inputIterable ? (List<T>) Collections.emptyList() : (List<T>) CollectionUtils.collect(inputIterable, transformer);
    }