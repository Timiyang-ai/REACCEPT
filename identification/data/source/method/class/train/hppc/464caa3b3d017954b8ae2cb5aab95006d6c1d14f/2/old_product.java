@Override
    public int indexOf(KType e1)
    {
        for (int i = 0; i < elementsCount; i++)
            if (Intrinsics.equalsKType(e1, buffer[i]))
                return i;

        return -1;
    }