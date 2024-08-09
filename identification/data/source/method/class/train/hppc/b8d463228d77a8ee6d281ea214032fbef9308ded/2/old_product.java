public boolean removeFirstOccurrence(KType e1)
    {
        final int index = bufferIndexOf(e1);
        if (index >= 0) removeAtBufferIndex(index);
        return index >= 0;
    }