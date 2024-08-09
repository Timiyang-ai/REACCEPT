public static <O> BigDecimal sum(Collection<O> objectCollection,String propertyName){
        Validate.notBlank(propertyName, "propertyName can't be blank!");
        return sum(objectCollection, propertyName, null);
    }