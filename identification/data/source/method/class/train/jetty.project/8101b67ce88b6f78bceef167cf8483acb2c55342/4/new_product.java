public Map<String,String[]> toStringArrayMap()
    {
        HashMap<String,String[]> map = new HashMap<String,String[]>(_map.size()*3/2)
        {
            @Override
            public String toString()
            {
                StringBuilder b=new StringBuilder();
                b.append('{');
                for (String k:super.keySet())
                {
                    if(b.length()>1)
                        b.append(',');
                    b.append(k);
                    b.append('=');
                    b.append(Arrays.asList(super.get(k)));
                }

                b.append('}');
                return b.toString();
            }
        };
        
        for(Map.Entry<String,Object> entry: _map.entrySet())
        {
            String[] a = LazyList.toStringArray(entry.getValue());
            map.put(entry.getKey(),a);
        }
        return map;
    }