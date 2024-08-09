@SafeVarargs
    public final List<V> putValues(String name, V... values) 
    {
        List<V> list = new ArrayList<>();
        list.addAll(Arrays.asList(values));
        return super.put(name,list);
    }