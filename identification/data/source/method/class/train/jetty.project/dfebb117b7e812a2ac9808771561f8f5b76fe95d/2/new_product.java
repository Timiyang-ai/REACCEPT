public void add(String name, V value) 
    {
        List<V> lo = get(name);
        if(lo == null) {
            lo = new ArrayList<>();
        }
        lo.add(value);
        super.put(name,lo);
    }