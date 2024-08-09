public static Type arrayComponentType(Type type) {
    if (type instanceof GenericArrayType) {
      return ((GenericArrayType) type).getGenericComponentType();
    } else if (type instanceof Class) {
      return ((Class<?>) type).getComponentType();
    } else {
      return null;
    }
  }