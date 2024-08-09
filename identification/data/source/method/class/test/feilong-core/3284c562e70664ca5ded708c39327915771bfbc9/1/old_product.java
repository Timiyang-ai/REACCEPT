public static <O, V> O find(Iterable<O> iterable,String propertyName,V propertyValue){
        return find(iterable, new BeanPropertyValueEqualsPredicate<O>(propertyName, propertyValue));
    }