public final int addAll(ObjectContainer<? extends KType> container)
    {
        int count = 0;
        for (ObjectCursor<? extends KType> cursor : container)
        {
            if (add(cursor.value)) count++;
        }

        return count;
    }