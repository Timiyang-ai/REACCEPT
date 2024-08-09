public static <T, O> Map<T, Integer> groupCount(Iterable<O> beanIterable,String propertyName,Predicate<O> includePredicate){
        if (isNullOrEmpty(beanIterable)){
            return emptyMap();
        }
        Validate.notBlank(propertyName, "propertyName can't be null/empty!");

        Map<T, Integer> map = new LinkedHashMap<>();
        for (O obj : beanIterable){
            if (null != includePredicate && !includePredicate.evaluate(obj)){
                continue;
            }
            MapUtil.putSumValue(map, PropertyUtil.<T> getProperty(obj, propertyName), 1);
        }
        return map;
    }