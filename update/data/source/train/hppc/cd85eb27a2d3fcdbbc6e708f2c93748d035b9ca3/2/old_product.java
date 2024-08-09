@Override
    public int removeAll(KTypePredicate<? super KType> predicate)
    {
        final KType [] keys = this.keys;
        final boolean [] allocated = this.allocated;

        int before = assigned;
        for (int i = 0; i < allocated.length;)
        {
            if (allocated[i])
            {
                if (predicate.apply(keys[i]))
                {
                    assigned--;
                    shiftConflictingKeys(i);
                    // Repeat the check for the same i.
                    continue;
                }
            }
            i++;
        }

        return before - this.assigned;
    }