@Override
    public VType remove(KType key)
    {
        final int mask = states.length - 1;
        int slot = keyHashFunction.hash(key) & mask; 

        while (states[slot] == ASSIGNED)
        {
            if (/* replaceIf:primitiveKType 
                (keys[slot] == key) */ 
                 keyComparator.compare(keys[slot], key) == 0 /* end:replaceIf */ )
             {
                assigned--;
                VType v = values[slot];
                shiftConflictingKeys(slot);
                return v;
             }
             slot = (slot + 1) & mask;
        }

        return Intrinsics.<VType> defaultVTypeValue();
    }