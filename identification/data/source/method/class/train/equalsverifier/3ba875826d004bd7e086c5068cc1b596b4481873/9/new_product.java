public ObjectAccessor<T> getBlackAccessor(TypeTag enclosingType) {
        ObjectAccessor<T> result = buildObjectAccessor();
        result.scramble(prefabValues, enclosingType);
        result.scramble(prefabValues, enclosingType);
        return result;
    }