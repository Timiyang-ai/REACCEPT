@Override
    public boolean containsAll(int... values)
    {
        for (int value : values)
        {
            if (!this.contains(value))
            {
                return false;
            }
        }
        return true;
    }