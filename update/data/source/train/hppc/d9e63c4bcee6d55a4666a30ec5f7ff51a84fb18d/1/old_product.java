public final int removeAllIn(Iterator<? extends ObjectCursor<? extends KType>> iterator)
    {
        int count = 0;
        while (iterator.hasNext())
        {
            if (remove((KType) iterator.next().value)) count++;
        }

        return count;
    }