public static <E> String toString(final Iterable<E> iterable,
                                      final Transformer<? super E, String> transformer,
                                      final String delimiter,
                                      final String prefix,
                                      final String suffix) {
        return IteratorUtils.toString(emptyIteratorIfNull(iterable),
                                      transformer, delimiter, prefix, suffix);
    }