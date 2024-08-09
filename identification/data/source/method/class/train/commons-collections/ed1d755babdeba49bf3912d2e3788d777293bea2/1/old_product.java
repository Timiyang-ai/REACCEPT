public void copyInto(final Collection<? super E> collection) {
        if (collection == null) {
            throw new NullPointerException("Collection must not be null");
        }

        for (final E element : iterable) {
            collection.add(element);
        }
    }