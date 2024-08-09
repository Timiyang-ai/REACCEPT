public static <V, T> ObjectLongMap<V> sumByInt(T[] array, Function<T, V> groupBy, IntFunction<? super T> function)
    {
        return InternalArrayIterate.sumByInt(array, array.length, groupBy, function);
    }