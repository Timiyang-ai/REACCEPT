public final int addAll(Iterator<? extends ObjectCursor<? extends KType>> iterator)
    {
        int count = 0;
        while (iterator.hasNext())
        {
            add(iterator.next().value);
            count++;
        }

        return count;
    }