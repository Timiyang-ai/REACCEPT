@Override
    @Deprecated
    public MutableCollection<T> newEmpty()
    {
        if (this.delegate instanceof Set)
        {
            return UnifiedSet.newSet();
        }
        return Lists.mutable.empty();
    }