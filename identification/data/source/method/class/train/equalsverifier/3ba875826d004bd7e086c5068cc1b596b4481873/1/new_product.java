public ObjectAccessor<T> getDefaultValuesAccessor(TypeTag enclosingType, Set<String> nonnullFields, AnnotationCache annotationCache) {
        ObjectAccessor<T> result = buildObjectAccessor();
        for (Field field : FieldIterable.of(type)) {
            if (NonnullAnnotationVerifier.fieldIsNonnull(field, annotationCache) || nonnullFields.contains(field.getName())) {
                FieldAccessor accessor = result.fieldAccessorFor(field);
                accessor.changeField(prefabValues, enclosingType);
            }
        }
        return result;
    }