public static boolean isParametrizedMethod(Symbol.MethodSymbol method) {
    if (!(method instanceof JMethodSymbol)) {
      return ((JavaSymbol.MethodJavaSymbol) method).isParametrized();
    }
    return ((JMethodSymbol) method).methodBinding().isParameterizedMethod()
      || ((JMethodSymbol) method).methodBinding().isGenericMethod();
  }