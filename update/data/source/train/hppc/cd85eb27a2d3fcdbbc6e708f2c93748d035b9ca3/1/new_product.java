@Override
    public int removeFirst(KType e1)
    {
        final int index = indexOf(e1);
        if (index >= 0) remove(index);
        return index;
    }