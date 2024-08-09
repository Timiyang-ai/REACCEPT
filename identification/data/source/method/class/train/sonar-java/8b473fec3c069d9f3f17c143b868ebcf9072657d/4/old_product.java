@Nullable
  public static Type primitiveType(Type type) {
    if (!(type instanceof JType)) {
      return ((JavaType) type).primitiveType();
    }
    String name = WRAPPER_TO_PRIMITIVE.get(type.fullyQualifiedName());
    if (name == null) {
      return null;
    }
    JSema sema = ((JType) type).sema;
    return sema.type(sema.resolveType(name));
  }