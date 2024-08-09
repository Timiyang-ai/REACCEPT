public static int maxSize(final Collection<? extends Object> collection) {
        Objects.requireNonNull(collection, "collection");
        if (collection instanceof BoundedCollection) {
            return ((BoundedCollection<?>) collection).maxSize();
        }
        try {
            final BoundedCollection<?> bcoll =
                    UnmodifiableBoundedCollection.unmodifiableBoundedCollection(collection);
            return bcoll.maxSize();
        } catch (final IllegalArgumentException ex) {
            return -1;
        }
    }