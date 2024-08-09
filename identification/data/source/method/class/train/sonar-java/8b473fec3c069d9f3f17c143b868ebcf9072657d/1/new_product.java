public static List<Type> typeArguments(Type type) {
    if (type.isUnknown()) {
      return Collections.emptyList();
    }
    JType t = (JType) type;
    ITypeBinding[] typeArguments = t.typeBinding.getTypeArguments();
    Type[] result = new Type[typeArguments.length];
    for (int i = 0; i < typeArguments.length; i++) {
      result[i] = t.sema.type(typeArguments[i]);
    }
    return Arrays.asList(result);
  }