public RealmResults<E> findAllSortedAsync(String fieldName) {
        return findAllSortedAsync(fieldName, Sort.ASCENDING);
    }