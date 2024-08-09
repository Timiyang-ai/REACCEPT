public Object put(K name, Object value) 
    {
        return _map.put(name,LazyList.add(null,value));
    }