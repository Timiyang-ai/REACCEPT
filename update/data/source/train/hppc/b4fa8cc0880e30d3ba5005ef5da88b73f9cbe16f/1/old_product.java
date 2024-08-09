public final int removeAll(KType e1)
    {
        int to = 0;
        for (int from = 0; from < elementsCount; from++)
        {
            if (Intrinsics.equals(e1, buffer[from]))
            {
                buffer[from] = Intrinsics.<KType>defaultKTypeValue();
                continue;
            }

            if (to != from)
            {
                buffer[to] = buffer[from];
                buffer[from] = Intrinsics.<KType>defaultKTypeValue();
            }
            to++;
        }

        final int deleted = elementsCount - to; 
        this.elementsCount = to;
        return deleted;
    }