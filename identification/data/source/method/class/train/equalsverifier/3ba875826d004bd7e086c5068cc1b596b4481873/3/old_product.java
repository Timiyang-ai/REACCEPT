public ObjectAccessor<T> getRedAccessor() {
        ObjectAccessor<T> result = buildObjectAccessor();
        result.scramble(prefabValues);
        return result;
    }