@Deprecated
    public static <O> int cardinality(final O obj, final Iterable<? super O> coll) {
        if (coll == null) {
            throw new NullPointerException("coll must not be null.");
        }
        return IterableUtils.cardinality(coll, obj);
    }