public static WildcardType subtypeOf(Type bound) {
    Type[] upperBounds;
    if (bound instanceof WildcardType) {
      upperBounds = ((WildcardType) bound).getUpperBounds();
    } else {
      upperBounds = new Type[] { bound };
    }
    return new WildcardTypeImpl(upperBounds, EMPTY_TYPE_ARRAY);
  }