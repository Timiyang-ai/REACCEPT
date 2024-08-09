public static Matcher<MethodTree> methodWithClassAndName(
      final String className, final String methodName) {
    return new Matcher<MethodTree>() {
      @Override
      public boolean matches(MethodTree methodTree, VisitorState state) {
        return ASTHelpers.getSymbol(methodTree)
                .getEnclosingElement()
                .getQualifiedName()
                .contentEquals(className)
            && methodTree.getName().contentEquals(methodName);
      }
    };
  }