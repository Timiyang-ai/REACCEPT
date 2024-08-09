@Override
    public int removeAll(int value)
    {
        if (value >= 0 && value < sparse.length)
        {
            final int slot = sparse[value];
            final int n = elementsCount - 1;
            if (slot <= n && dense[slot] == value)
            {
                // Swap the last value with the removed value.
                final int lastValue = dense[n];
                elementsCount--;
                dense[slot] = lastValue;
                sparse[lastValue] = slot;
                return 1;
            }
        }
        return 0;
    }