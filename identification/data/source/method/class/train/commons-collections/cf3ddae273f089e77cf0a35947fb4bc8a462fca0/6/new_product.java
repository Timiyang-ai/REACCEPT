public static <I,O,T extends O> Collection<O> collect(Iterator<I> inputIterator, final Transformer<? super I,T> transformer, final Collection<O> outputCollection) {
        if (inputIterator != null && transformer != null) {
            while (inputIterator.hasNext()) {
                I item = inputIterator.next();
                T value = transformer.transform(item);
                outputCollection.add(value);
            }
        }
        return outputCollection;
    }