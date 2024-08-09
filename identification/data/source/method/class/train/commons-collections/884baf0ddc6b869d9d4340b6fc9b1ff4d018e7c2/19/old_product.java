public static int maxSize(Collection coll) {
        if (coll == null) {
            throw new NullPointerException("The collection must not be null");
        }
        if (coll instanceof BoundedCollection) {
            return ((BoundedCollection) coll).maxSize();
        }
        try {
            BoundedCollection bcoll = UnmodifiableBoundedCollection.decorateUsing(coll);
            return bcoll.maxSize();
            
        } catch (IllegalArgumentException ex) {
            return -1;
        }
    }