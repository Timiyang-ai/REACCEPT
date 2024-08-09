@SafeVarargs
    public static <O, V> List<O> select(Collection<O> objectCollection,String propertyName,V...propertyValues){
        return isNullOrEmpty(objectCollection) ? Collections.<O> emptyList() : select(
                        objectCollection,
                        BeanPredicateUtil.<O, V> containsPredicate(propertyName, propertyValues));
    }