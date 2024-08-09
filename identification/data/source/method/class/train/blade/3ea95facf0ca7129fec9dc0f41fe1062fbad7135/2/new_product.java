@SafeVarargs
    public static <T> Set<T> newSets(T... values) {
        if(null == values || values.length == 0){
            Assert.notNull(values, "values not is null.");
        }
        return new HashSet<>(Arrays.asList(values));
    }