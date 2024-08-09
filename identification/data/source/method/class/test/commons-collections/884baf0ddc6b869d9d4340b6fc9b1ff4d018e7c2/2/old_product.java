@SuppressWarnings("unchecked")
    public static boolean isFull(Collection<?> coll) {
        if (coll == null) {
            throw new NullPointerException("The collection must not be null");
        }
        if (coll instanceof BoundedCollection) {
            return ((BoundedCollection<?>) coll).isFull();
        }
        try {
            BoundedCollection<?> bcoll = UnmodifiableBoundedCollection.unmodifiableBoundedCollection((Collection<Object>) coll);
            return bcoll.isFull();
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }