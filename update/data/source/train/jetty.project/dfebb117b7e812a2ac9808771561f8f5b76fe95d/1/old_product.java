public void addValues(String name, String[] values) 
    {
        Object lo = get(name);
        Object ln = LazyList.addCollection(lo,Arrays.asList(values));
        if (lo!=ln)
            put(name,ln);
    }