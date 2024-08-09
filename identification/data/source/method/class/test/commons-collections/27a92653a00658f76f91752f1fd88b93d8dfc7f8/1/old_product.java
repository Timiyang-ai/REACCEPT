public static <C> String toString(Iterable<C> iterable) {
        return toString(iterable, new Transformer<C, String>() {
            public String transform(C input) {
                return String.valueOf(input);
            }
        }, DEFAULT_TOSTRING_DELIMITER, DEFAULT_TOSTRING_PREFIX, DEFAULT_TOSTRING_SUFFIX);
    }