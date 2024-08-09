public static ParameterizedType newParameterizedType(Type rawType, Type... typeArguments) {
    return new ParameterizedTypeImpl(null, rawType, typeArguments);
  }