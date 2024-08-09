public boolean removeValue(String name,Object value)
    {
        Object lo = _map.get(name);
        Object ln=lo;
        int s=LazyList.size(lo);
        if (s>0)
        {
            ln=LazyList.remove(lo,value);
            if (ln==null)
                _map.remove(name);
            else
                _map.put(name, ln);
        }
        return LazyList.size(ln)!=s;
    }