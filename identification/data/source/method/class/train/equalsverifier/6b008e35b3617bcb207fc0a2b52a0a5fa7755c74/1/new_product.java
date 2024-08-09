public <T> Tuple<T> giveTuple(TypeTag tag) {
        realizeCacheFor(tag, emptyStack());
        return cache.getTuple(tag);
    }