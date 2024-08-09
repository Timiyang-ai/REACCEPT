public static <V, T> ObjectLongMap<V> sumByLong(T[] array, Function<T, V> groupBy, LongFunction<? super T> function)
    {
        return InternalArrayIterate.sumByLong(array, array.length, groupBy, function);
    }