public static <T, V> MutableList<V> collectIf(
            T[] objectArray,
            Predicate<? super T> predicate,
            Function<? super T, ? extends V> function)
    {
        if (objectArray == null)
        {
            throw new IllegalArgumentException("Cannot perform a collectIf on null");
        }

        return ArrayIterate.collectIf(
                objectArray,
                predicate,
                function,
                FastList.newList(objectArray.length));
    }