@Override
    public int removeLastOccurrence(KType e1)
    {
        final int index = lastIndexOf(e1);
        if (index >= 0) remove(index);
        return index;
    }