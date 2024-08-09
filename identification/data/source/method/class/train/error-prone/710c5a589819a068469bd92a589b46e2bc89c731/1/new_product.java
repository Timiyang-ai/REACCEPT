public static Matcher<MethodTree> methodReturnsNonPrimitiveType() {
    return methodReturns(not(isPrimitiveType()));
  }