public static <T> ArrayList<T> drop(ArrayList<T> list, int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException("Count must be greater than zero, but was: " + count);
        }
        return ArrayListIterate.drop(list, count, new ArrayList<T>(list.size() - Math.min(list.size(), count)));
    }