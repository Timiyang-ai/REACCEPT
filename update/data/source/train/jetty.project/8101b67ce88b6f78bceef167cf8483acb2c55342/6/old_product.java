public void addValues(K name, List<? extends Object> values) 
    {
        Object lo = _map.get(name);
        Object ln = LazyList.addCollection(lo,values);
        if (lo!=ln)
            _map.put(name,ln);
    }