@SuppressWarnings("unchecked")
    public static boolean isFull(final Collection<?> coll) {
        if (coll == null) {
            throw new NullPointerException("The collection must not be null");
        }
        if (coll instanceof BoundedCollection) {
            return ((BoundedCollection<?>) coll).isFull();
        }
        try {
            final BoundedCollection<?> bcoll =
                    UnmodifiableBoundedCollection.unmodifiableBoundedCollection((Collection<Object>) coll);
            return bcoll.isFull();
        } catch (final IllegalArgumentException ex) {
            return false;
        }
    }