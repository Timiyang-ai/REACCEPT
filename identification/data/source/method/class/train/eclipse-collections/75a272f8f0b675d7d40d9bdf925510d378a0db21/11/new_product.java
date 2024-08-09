public static <T> ArrayList<T> take(ArrayList<T> list, int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        return ArrayListIterate.take(list, count, new ArrayList<>(Math.min(list.size(), count)));
    }