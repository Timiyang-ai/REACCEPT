public static boolean isFull(final Collection<? extends Object> collection) {
        Objects.requireNonNull(collection, "collection");
        if (collection instanceof BoundedCollection) {
            return ((BoundedCollection<?>) collection).isFull();
        }
        try {
            final BoundedCollection<?> bcoll =
                    UnmodifiableBoundedCollection.unmodifiableBoundedCollection(collection);
            return bcoll.isFull();
        } catch (final IllegalArgumentException ex) {
            return false;
        }
    }