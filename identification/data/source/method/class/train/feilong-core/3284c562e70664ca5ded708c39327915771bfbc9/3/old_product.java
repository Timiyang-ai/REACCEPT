@SafeVarargs
    public static <O, V> List<O> select(Collection<O> objectCollection,String propertyName,V...propertyValues){
        return Validator.isNullOrEmpty(objectCollection) ? Collections.<O> emptyList()
                        : select(objectCollection, new ArrayContainsPredicate<O>(propertyName, propertyValues));
    }