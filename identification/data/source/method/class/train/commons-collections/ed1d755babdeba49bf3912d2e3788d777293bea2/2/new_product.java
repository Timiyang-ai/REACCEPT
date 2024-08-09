public void copyInto(final Collection<? super E> collection) {
        Objects.requireNonNull(collection, "collection");
        CollectionUtils.addAll(collection, iterable);
    }