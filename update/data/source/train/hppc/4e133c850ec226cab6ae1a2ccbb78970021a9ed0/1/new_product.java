public final int removeAll(Iterator<? extends ObjectCursor<? extends KType>> iterator)
    {
        int count = 0;
        while (iterator.hasNext())
        {
            count += removeAll((KType) iterator.next().value);
        }

        return count;
    }