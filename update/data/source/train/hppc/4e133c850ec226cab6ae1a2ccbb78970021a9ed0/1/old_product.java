public final int removeAllIn(Iterator<? extends ObjectCursor<? extends KType>> iterator)
    {
        int count = 0;
        while (iterator.hasNext())
        {
            count += removeAllOccurrences((KType) iterator.next().value);
        }

        return count;
    }