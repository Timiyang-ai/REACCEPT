@CheckReturnValue public static WildcardType subtypeOf(Type bound) {
    return new WildcardTypeImpl(new Type[] { bound }, EMPTY_TYPE_ARRAY);
  }