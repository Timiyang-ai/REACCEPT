public Object putValues(K name, String... values) 
    {
        Object list=null;
        for (int i=0;i<values.length;i++)
            list=LazyList.add(list,values[i]);
        return _map.put(name,list);
    }