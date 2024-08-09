public void add(String name, Object value) 
    {
        Object lo = get(name);
        Object ln = LazyList.add(lo,value);
        if (lo!=ln)
            put(name,ln);
    }