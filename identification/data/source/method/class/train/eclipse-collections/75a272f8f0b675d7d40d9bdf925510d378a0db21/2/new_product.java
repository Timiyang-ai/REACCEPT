public static <T> PartitionMutableList<T> partition(ArrayList<T> list, Predicate<? super T> predicate)
    {
        int size = list.size();
        if (ArrayListIterate.isOptimizableArrayList(list, size))
        {
            PartitionFastList<T> partitionFastList = new PartitionFastList<>();
            MutableList<T> selected = partitionFastList.getSelected();
            MutableList<T> rejected = partitionFastList.getRejected();

            T[] elements = ArrayListIterate.getInternalArray(list);
            for (int i = 0; i < size; i++)
            {
                T each = elements[i];
                MutableList<T> bucket = predicate.accept(each) ? selected : rejected;
                bucket.add(each);
            }
            return partitionFastList;
        }
        return RandomAccessListIterate.partition(list, predicate);
    }