public static boolean isParametrizedMethod(Symbol.MethodSymbol method) {
    if (method.isUnknown()) {
      return false;
    }
    return ((JMethodSymbol) method).methodBinding().isParameterizedMethod()
      || ((JMethodSymbol) method).methodBinding().isGenericMethod();
  }