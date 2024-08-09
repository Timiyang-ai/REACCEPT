@Override
    public int removeAll(KTypePredicate<? super KType> predicate)
    {
        final KType [] buffer = Intrinsics.<KType[]> cast(this.buffer);
        int removed = 0;
        final int last = tail;
        final int bufLen = buffer.length;
        int from, to;
        from = to = head;
        try
        {
            for (from = to = head; from != last; from = oneRight(from, bufLen))
            {
                if (predicate.apply(buffer[from]))
                {
                    buffer[from] = Intrinsics.empty();
                    removed++;
                    continue;
                }
    
                if (to != from)
                {
                    buffer[to] = buffer[from];
                    buffer[from] = Intrinsics.empty();
                }
        
                to = oneRight(to, bufLen);
            }
        }
        finally
        {
            // Keep the deque in consistent state even if the predicate throws an exception.
            for (; from != last; from = oneRight(from, bufLen))
            {
                if (to != from)
                {
                    buffer[to] = buffer[from];
                    buffer[from] = Intrinsics.empty();
                }
        
                to = oneRight(to, bufLen);
            }
            tail = to;
        }

        return removed;
    }