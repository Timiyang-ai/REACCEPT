public Map<K,String[]> toStringArrayMap()
    {
        HashMap<K,String[]> map = new HashMap<K,String[]>(_map.size()*3/2)
        {
            public String toString()
            {
                StringBuilder b=new StringBuilder();
                b.append('{');
                for (K k:keySet())
                {
                    if(b.length()>1)
                        b.append(',');
                    b.append(k);
                    b.append('=');
                    b.append(Arrays.asList(get(k)));
                }

                b.append('}');
                return b.toString();
            }
        };
        
        for(Map.Entry<K,Object> entry: _map.entrySet())
        {
            String[] a = LazyList.toStringArray(entry.getValue());
            map.put(entry.getKey(),a);
        }
        return map;
    }