public boolean remove(T item) {
        throwIfInMutationOperation();
        return remove(item, true);
    }