public static <O> List<O> selectRejected(Collection<O> objectCollection,Predicate<O> predicate){
        return isNullOrEmpty(objectCollection) ? Collections.<O> emptyList()
                        : (List<O>) CollectionUtils.selectRejected(objectCollection, predicate);
    }