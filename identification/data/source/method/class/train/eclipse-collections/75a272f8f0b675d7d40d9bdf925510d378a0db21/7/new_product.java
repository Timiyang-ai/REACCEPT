public static <T> ArrayList<T> distinct(ArrayList<T> list, HashingStrategy<? super T> hashingStrategy)
    {
        int size = list.size();
        MutableSet<T> seenSoFar = UnifiedSetWithHashingStrategy.newSet(hashingStrategy);
        ArrayList<T> result = new ArrayList<>();
        if (ArrayListIterate.isOptimizableArrayList(list, size))
        {
            T[] elements = ArrayListIterate.getInternalArray(list);
            for (int i = 0; i < size; i++)
            {
                if (seenSoFar.add(elements[i]))
                {
                    result.add(elements[i]);
                }
            }
        }
        else
        {
            for (int i = 0; i < size; i++)
            {
                T item = list.get(i);
                if (seenSoFar.add(item))
                {
                    result.add(item);
                }
            }
        }
        return result;
    }