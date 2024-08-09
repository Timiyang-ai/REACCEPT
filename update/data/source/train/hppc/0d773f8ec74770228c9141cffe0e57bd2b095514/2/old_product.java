@Override
    public int removeLastOccurrence(KType e1)
    {
        final int index = lastBufferIndexOf(e1);
        if (index >= 0) removeAtBufferIndex(index);
        return index;
    }