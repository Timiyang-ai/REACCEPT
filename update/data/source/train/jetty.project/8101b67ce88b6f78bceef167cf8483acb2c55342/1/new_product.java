@Override
    public Object put(String name, Object value) 
    {
        return _map.put(name,LazyList.add(null,value));
    }