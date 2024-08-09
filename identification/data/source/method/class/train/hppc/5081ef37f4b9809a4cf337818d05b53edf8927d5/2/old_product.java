@Override
    public VType remove(KType key)
    {
        final int slot = slotFor(key);

        final VType value = values[slot];
        final byte state = states[slot]; 
        if (state == ASSIGNED)
        {
            deleted++;
            assigned--;

            keys[slot] = Intrinsics.<KType>defaultKTypeValue();
            values[slot] = Intrinsics.<VType>defaultVTypeValue();
            states[slot] = DELETED;
        }
        else
        {
            assert (Intrinsics.defaultVTypeValue() == value) : "Default value expected.";
        }

        return value;
    }