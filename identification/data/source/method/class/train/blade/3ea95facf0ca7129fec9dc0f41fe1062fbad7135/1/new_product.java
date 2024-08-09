@SafeVarargs
    public static <T> List<T> newLists(T... values) {
        if(null == values || values.length == 0){
            Assert.notNull(values, "values not is null.");
        }
        return Arrays.asList(values);
    }