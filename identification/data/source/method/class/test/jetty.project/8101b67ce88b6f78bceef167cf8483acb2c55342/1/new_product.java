@Override
    public void putAll(Map<? extends String, ? extends Object> m)
    {
        boolean multi = (m instanceof MultiMap);

        if (multi)
        {
            for (Map.Entry<? extends String, ? extends Object> entry : m.entrySet())
            {
                _map.put(entry.getKey(),LazyList.clone(entry.getValue()));
            }
        }
        else
        {
            _map.putAll(m);
        }
    }