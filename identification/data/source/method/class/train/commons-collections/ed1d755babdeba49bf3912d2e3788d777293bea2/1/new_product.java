public void copyInto(final Collection<? super E> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null");
        }
        CollectionUtils.addAll(collection, iterable);
    }