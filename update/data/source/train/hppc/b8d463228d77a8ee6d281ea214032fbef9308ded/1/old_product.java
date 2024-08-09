public boolean removeLastOccurrence(KType e1)
    {
        final int index = lastBufferIndexOf(e1);
        if (index >= 0) removeAtBufferIndex(index);
        return index >= 0;
    }