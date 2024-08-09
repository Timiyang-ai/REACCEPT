private <T> Tuple<T> giveTuple(TypeTag tag, LinkedHashSet<TypeTag> typeStack) {
        realizeCacheFor(tag, typeStack);
        return cache.getTuple(tag);
    }