@SuppressWarnings("unchecked")
    public static int maxSize(final Collection<?> coll) {
        if (coll == null) {
            throw new NullPointerException("The collection must not be null");
        }
        if (coll instanceof BoundedCollection) {
            return ((BoundedCollection<?>) coll).maxSize();
        }
        try {
            final BoundedCollection<?> bcoll =
                    UnmodifiableBoundedCollection.unmodifiableBoundedCollection((Collection<Object>) coll);
            return bcoll.maxSize();
        } catch (final IllegalArgumentException ex) {
            return -1;
        }
    }