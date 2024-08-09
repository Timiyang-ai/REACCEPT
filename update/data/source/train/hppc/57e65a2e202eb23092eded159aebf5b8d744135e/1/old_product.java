public final void add(KType e1)
    {
        ensureBufferSpace(1);
        buffer[elementsCount++] = e1;
    }