public boolean remove(T item) {
        throwIfMerging();
        return remove(item, true);
    }