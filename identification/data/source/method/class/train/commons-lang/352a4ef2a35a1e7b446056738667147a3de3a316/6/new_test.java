    @SafeVarargs
    private static <T> T[] toArrayPropagatingType(final T... items) {
        return ArrayUtils.toArray(items);
    }