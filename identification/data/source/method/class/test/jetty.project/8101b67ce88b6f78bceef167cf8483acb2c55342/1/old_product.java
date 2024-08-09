public void putAll(Map<? extends K, ? extends Object> m)
    {
        boolean multi = (m instanceof MultiMap);

        if (multi)
        {
            for (Map.Entry<? extends K, ? extends Object> entry : m.entrySet())
            {
                _map.put(entry.getKey(),LazyList.clone(entry.getValue()));
            }
        }
        else
        {
            _map.putAll(m);
        }
    }