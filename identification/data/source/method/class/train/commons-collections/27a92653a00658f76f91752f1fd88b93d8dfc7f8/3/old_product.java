public static <C> String toString(Iterable<C> iterable,
                                      Transformer<? super C, String> transformer,
                                      String delimiter,
                                      String prefix,
                                      String suffix) {
        if (iterable == null) {
            throw new IllegalArgumentException("iterable may not be null");
        }
        if (transformer == null) {
            throw new IllegalArgumentException("transformer may not be null");
        }
        if (delimiter == null) {
            throw new IllegalArgumentException("delimiter may not be null");
        }
        if (prefix == null) {
            throw new IllegalArgumentException("prefix may not be null");
        }
        if (suffix == null) {
            throw new IllegalArgumentException("suffix may not be null");
        }
        final StringBuilder stringBuilder = new StringBuilder(prefix);
        for(final C element : iterable) {
            stringBuilder.append(transformer.transform(element));
            stringBuilder.append(delimiter);
        }
        if(stringBuilder.length() > prefix.length()) {
            stringBuilder.setLength(stringBuilder.length() - delimiter.length());
        }
        stringBuilder.append(suffix);
        return stringBuilder.toString();
    }