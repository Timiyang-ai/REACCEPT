public static <T, O> Map<T, Integer> groupCount(Iterable<O> beanIterable,String propertyName,Predicate<O> includePredicate){
        if (isNullOrEmpty(beanIterable)){
            return emptyMap();
        }
        Validate.notBlank(propertyName, "propertyName can't be null/empty!");

        //---------------------------------------------------------------

        Map<String, Map<T, Integer>> groupCount = groupCount(beanIterable, toArray(propertyName), includePredicate);
        return groupCount.get(propertyName);
    }