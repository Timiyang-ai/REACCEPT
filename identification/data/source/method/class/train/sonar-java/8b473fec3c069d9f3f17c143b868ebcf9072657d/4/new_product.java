@Nullable
  public static Type primitiveType(Type type) {
    String name = WRAPPER_TO_PRIMITIVE.get(type.fullyQualifiedName());
    if (name == null) {
      return null;
    }
    JSema sema = ((JType) type).sema;
    return sema.type(sema.resolveType(name));
  }