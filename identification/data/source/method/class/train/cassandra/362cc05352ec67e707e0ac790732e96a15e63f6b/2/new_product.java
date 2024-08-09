public RangeTombstone search(Composite name)
    {
        int idx = searchInternal(name, 0);
        return idx < 0 ? null : rangeTombstone(idx);
    }