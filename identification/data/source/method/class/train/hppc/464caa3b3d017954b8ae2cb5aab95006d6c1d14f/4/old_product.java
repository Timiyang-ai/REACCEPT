@Override
    public int removeAll(KType e1)
    {
        int removed = 0;
        final int last = tail;
        final int bufLen = buffer.length;
        int from, to;
        for (from = to = head; from != last; from = oneRight(from, bufLen))
        {
            if (Intrinsics.equalsKType(e1, buffer[from]))
            {
                buffer[from] = Intrinsics.<KType>defaultKTypeValue();
                removed++;
                continue;
            }
    
            if (to != from)
            {
                buffer[to] = buffer[from];
                buffer[from] = Intrinsics.<KType>defaultKTypeValue();
            }
    
            to = oneRight(to, bufLen);
        }
    
        tail = to;
        return removed;
    }