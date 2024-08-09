public ObjectAccessor<T> getBlackAccessor() {
        ObjectAccessor<T> result = buildObjectAccessor();
        result.scramble(prefabValues);
        result.scramble(prefabValues);
        return result;
    }