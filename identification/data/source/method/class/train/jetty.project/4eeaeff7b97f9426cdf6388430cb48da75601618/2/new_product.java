@Override
    public boolean containsKey(Object key)
    {
        if (key==null)
            return false;
        return
            getEntry(key.toString(),0,key==null?0:key.toString().length())!=null;
    }