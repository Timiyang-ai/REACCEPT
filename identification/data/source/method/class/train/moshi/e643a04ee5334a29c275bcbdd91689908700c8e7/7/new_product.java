public static WildcardType supertypeOf(Type bound) {
    Type[] lowerBounds;
    if (bound instanceof WildcardType) {
      lowerBounds = ((WildcardType) bound).getLowerBounds();
    } else {
      lowerBounds = new Type[] { bound };
    }
    return new WildcardTypeImpl(new Type[] { Object.class }, lowerBounds);
  }