public static Type arrayComponentType(Type array) {
    return array instanceof GenericArrayType
        ? ((GenericArrayType) array).getGenericComponentType()
        : ((Class<?>) array).getComponentType();
  }