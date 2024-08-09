public static <V, T> ObjectLongMap<V> sumByLong(T[] array, Function<? super T, ? extends V> groupBy, LongFunction<? super T> function)
    {
        return InternalArrayIterate.sumByLong(array, array.length, groupBy, function);
    }