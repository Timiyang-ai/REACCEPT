public final int addAll(ObjectContainer<? extends KType> container)
    {
        final int size = container.size();
        ensureBufferSpace(size);

        for (ObjectCursor<? extends KType> cursor : container)
        {
            add(cursor.value);
        }

        return size;
    }