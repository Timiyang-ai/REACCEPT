@Deprecated
    public static <O> int cardinality(final O obj, final Iterable<? super O> collection) {
        return IterableUtils.frequency(Objects.requireNonNull(collection, "collection"), obj);
    }