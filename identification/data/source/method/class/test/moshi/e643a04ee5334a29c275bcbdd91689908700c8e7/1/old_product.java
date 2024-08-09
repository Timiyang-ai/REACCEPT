@CheckReturnValue public static WildcardType supertypeOf(Type bound) {
    return new WildcardTypeImpl(new Type[] { Object.class }, new Type[] { bound });
  }