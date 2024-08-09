public static <T> SerializableComparator<T> reverse(Comparator<T> comparator)
    {
        if (comparator == null)
        {
            throw new NullPointerException();
        }
        return new ReverseComparator<>(comparator);
    }