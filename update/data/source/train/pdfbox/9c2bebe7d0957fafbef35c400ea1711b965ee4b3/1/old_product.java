@SuppressWarnings("unchecked")
    @Override
    public Collection<V> values()
    {
        if (isEmpty())
        {
            return Collections.emptySet();
        }
        
        List<V> values = new ArrayList<V>(mapArr.length >> 1);
        for (int vIdx = 1; vIdx < mapArr.length; vIdx+=2)
        {
            values.add((V)mapArr[vIdx]);
        }
        return Collections.unmodifiableList( values );
    }