public static Matcher<MethodTree> methodWithClassAndName(String className, String methodName) {
    return (methodTree, state) ->
        ASTHelpers.getSymbol(methodTree)
                .getEnclosingElement()
                .getQualifiedName()
                .contentEquals(className)
            && methodTree.getName().contentEquals(methodName);
  }