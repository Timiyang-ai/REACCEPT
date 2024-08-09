public ObjectAccessor<T> getRedAccessor(TypeTag enclosingType) {
        ObjectAccessor<T> result = buildObjectAccessor();
        result.scramble(prefabValues, enclosingType);
        return result;
    }