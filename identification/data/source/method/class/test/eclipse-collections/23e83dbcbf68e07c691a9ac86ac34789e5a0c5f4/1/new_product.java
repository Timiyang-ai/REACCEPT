public static <V, T> ObjectDoubleMap<V> sumByFloat(T[] array, Function<? super T, ? extends V> groupBy, FloatFunction<? super T> function)
    {
        return InternalArrayIterate.sumByFloat(array, array.length, groupBy, function);
    }