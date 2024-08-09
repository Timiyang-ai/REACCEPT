public boolean removeValue(String name,Object value)
    {
        Object lo = get(name);
        Object ln=lo;
        int s=LazyList.size(lo);
        if (s>0)
        {
            ln=LazyList.remove(lo,value);
            if (ln==null)
                remove(name);
            else
                put(name, ln);
        }
        return LazyList.size(ln)!=s;
    }