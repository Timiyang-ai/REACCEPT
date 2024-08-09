public static <O, V> List<O> removeAll(Collection<O> objectCollection,String propertyName,V value){
        Collection<O> removeCollection = select(objectCollection, propertyName, value);
        return removeAll(objectCollection, removeCollection);
    }