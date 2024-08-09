public final int addAllFirst(Iterator<? extends ObjectCursor<? extends KType>> iterator)
    {
        int count = 0;
        while (iterator.hasNext())
        {
            addFirst(iterator.next().value);
            count++;
        }

        return count;
    }