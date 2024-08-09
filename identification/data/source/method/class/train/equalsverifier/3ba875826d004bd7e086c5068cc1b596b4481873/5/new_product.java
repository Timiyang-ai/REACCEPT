public ObjectAccessor<T> getDefaultValuesAccessor(TypeTag enclosingType) {
        ObjectAccessor<T> result = buildObjectAccessor();
        for (Field field : FieldIterable.of(type)) {
            if (NonnullAnnotationChecker.fieldIsNonnull(this, field)) {
                FieldAccessor accessor = result.fieldAccessorFor(field);
                accessor.changeField(prefabValues, enclosingType);
            }
        }
        return result;
    }