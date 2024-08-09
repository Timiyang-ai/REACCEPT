public static <C> String toString(Iterable<C> iterable, Transformer<? super C, String> transformer) {
        return toString(iterable, transformer, DEFAULT_TOSTRING_DELIMITER,
                        DEFAULT_TOSTRING_PREFIX, DEFAULT_TOSTRING_SUFFIX);
    }