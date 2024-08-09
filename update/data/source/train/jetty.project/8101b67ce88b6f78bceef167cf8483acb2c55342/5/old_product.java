public void add(K name, Object value) 
    {
        Object lo = _map.get(name);
        Object ln = LazyList.add(lo,value);
        if (lo!=ln)
            _map.put(name,ln);
    }