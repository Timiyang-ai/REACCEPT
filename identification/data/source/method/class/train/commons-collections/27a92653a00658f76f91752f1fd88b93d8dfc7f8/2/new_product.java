public static <E> String toString(final Iterable<E> iterable,
                                      final Transformer<? super E, String> transformer) {
        if (transformer == null) {
            throw new NullPointerException("transformer may not be null");
        }
        return IteratorUtils.toString(emptyIteratorIfNull(iterable), transformer);
    }