@Override
    public void clear()
    {
        assigned = 0;

        // States are always cleared.
        Arrays.fill(states, EMPTY);

        /* removeIf:primitiveVType */
        Arrays.fill(values, null); // Help the GC.
        /* end:removeIf */

        /* removeIf:primitiveKType */
        Arrays.fill(keys, null); // Help the GC.
        /* end:removeIf */
    }