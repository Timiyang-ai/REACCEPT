public ObjectAccessor<T> getDefaultValuesAccessor() {
        ObjectAccessor<T> result = buildObjectAccessor();
        for (Field field : FieldIterable.of(type)) {
            if (NonnullAnnotationChecker.fieldIsNonnull(this, field)) {
                FieldAccessor accessor = result.fieldAccessorFor(field);
                accessor.changeField(prefabValues);
            }
        }
        return result;
    }