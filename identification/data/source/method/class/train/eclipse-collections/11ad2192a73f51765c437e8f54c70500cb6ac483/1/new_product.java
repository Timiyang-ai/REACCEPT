public static <T> RichIterable<RichIterable<T>> chunk(T[] array, int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Size for groups must be positive but was: " + size);
        }
        int index = 0;
        MutableList<RichIterable<T>> result = Lists.mutable.empty();
        while (index < array.length)
        {
            MutableList<T> batch = Lists.mutable.empty();
            for (int i = 0; i < size && index < array.length; i++)
            {
                batch.add(array[index]);
                index++;
            }
            result.add(batch);
        }
        return result;
    }