<T> Tuple<T> giveTuple(TypeTag tag, LinkedHashSet<TypeTag> typeStack) {
        if (!cache.contains(tag)) {
            Tuple<T> tuple = createTuple(tag, typeStack);
            addToCache(tag, tuple);
        }
        return cache.getTuple(tag);
    }