@Nullable
  public static Type primitiveWrapperType(Type type) {
    if (!(type instanceof JType)) {
      return ((JavaType) type).primitiveWrapperType();
    }
    String name = WRAPPER_TO_PRIMITIVE.inverse().get(type.fullyQualifiedName());
    if (name == null) {
      return null;
    }
    JSema sema = ((JType) type).sema;
    return sema.type(sema.resolveType(name));
  }