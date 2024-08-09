@SafeVarargs
    public static <O, V> List<O> removeAll(Collection<O> objectCollection,String propertyName,V...propertyValues){
        Collection<O> removeCollection = select(objectCollection, propertyName, propertyValues);
        return removeAll(objectCollection, removeCollection);
    }