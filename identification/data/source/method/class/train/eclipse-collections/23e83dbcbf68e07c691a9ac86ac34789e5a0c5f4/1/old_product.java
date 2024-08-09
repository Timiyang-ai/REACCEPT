public static <V, T> ObjectDoubleMap<V> sumByDouble(T[] array, Function<T, V> groupBy, DoubleFunction<? super T> function)
    {
        return InternalArrayIterate.sumByDouble(array, array.length, groupBy, function);
    }