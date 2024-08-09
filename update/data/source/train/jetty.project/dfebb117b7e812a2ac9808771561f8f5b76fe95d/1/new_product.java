public void addValues(String name, V[] values) 
    {
        List<V> lo = get(name);
        if(lo == null) {
            lo = new ArrayList<>();
        }
        lo.addAll(Arrays.asList(values));
        put(name,lo);
    }