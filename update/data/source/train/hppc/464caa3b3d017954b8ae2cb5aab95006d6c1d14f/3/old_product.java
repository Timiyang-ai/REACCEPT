@Override
    public int lastIndexOf(KType e1)
    {
        for (int i = elementsCount - 1; i >= 0; i--)
            if (Intrinsics.equalsKType(e1, buffer[i]))
                return i;

        return -1;
    }