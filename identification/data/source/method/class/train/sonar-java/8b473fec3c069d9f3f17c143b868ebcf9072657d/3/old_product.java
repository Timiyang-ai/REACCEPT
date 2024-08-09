public static boolean isIntersectionType(Type type) {
    if (!(type instanceof JType)) {
      return ((JavaType) type).isTagged(JavaType.INTERSECTION);
    }
    return ((JType) type).typeBinding.isIntersectionType();
  }