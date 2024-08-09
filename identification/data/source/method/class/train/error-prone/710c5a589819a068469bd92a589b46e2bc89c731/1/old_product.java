public static Matcher<MethodTree> methodReturnsNonPrimitiveType() {
    return new Matcher<MethodTree>() {
      @Override
      public boolean matches(MethodTree methodTree, VisitorState state) {
        Tree returnTree = methodTree.getReturnType();
        return returnTree == null ? false : !((JCTree) returnTree).type.isPrimitive();
      }
    };
  }