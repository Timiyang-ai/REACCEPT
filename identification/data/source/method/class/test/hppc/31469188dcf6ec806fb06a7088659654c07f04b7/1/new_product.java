@Override
    public KType remove(int index)
    {
        assert (index >= 0 && index < size()) :
            "Index " + index + " out of bounds [" + 0 + ", " + size() + ").";

        final KType v = Intrinsics.<KType> cast(buffer[index]);
        if (index + 1 < elementsCount) {
            System.arraycopy(buffer, index + 1, buffer, index, elementsCount - index - 1);
        }
        elementsCount--;
        buffer[elementsCount] = Intrinsics.empty();
        return v;
    }