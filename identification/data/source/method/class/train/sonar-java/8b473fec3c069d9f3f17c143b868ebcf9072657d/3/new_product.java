public static boolean isIntersectionType(Type type) {
    return !type.isUnknown() && ((JType) type).typeBinding.isIntersectionType();
  }