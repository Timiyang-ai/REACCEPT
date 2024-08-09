public Description describe(MethodInvocationTree methodInvocationTree, VisitorState state) {
    // Find the root of the field access chain, i.e. a.intern().trim() ==> a.
    ExpressionTree identifierExpr = ASTHelpers.getRootAssignable(methodInvocationTree);
    String identifierStr = null;
    Type identifierType = null;
    if (identifierExpr != null) {
      identifierStr = identifierExpr.toString();
      if (identifierExpr instanceof JCIdent) {
        identifierType = ((JCIdent) identifierExpr).sym.type;
      } else if (identifierExpr instanceof JCFieldAccess) {
        identifierType = ((JCFieldAccess) identifierExpr).sym.type;
      } else {
        throw new IllegalStateException("Expected a JCIdent or a JCFieldAccess");
      }
    }

    Type returnType = ASTHelpers.getReturnType(
        ((JCMethodInvocation) methodInvocationTree).getMethodSelect());

    SuggestedFix fix;
    if (identifierStr != null && !"this".equals(identifierStr) && returnType != null &&
        state.getTypes().isAssignable(returnType, identifierType)) {
      // Fix by assigning the assigning the result of the call to the root receiver reference.
      fix = new SuggestedFix().prefixWith(methodInvocationTree, identifierStr + " = ");
    } else {
      // Unclear what the programmer intended.  Delete since we don't know what else to do.
      Tree parent = state.getPath().getParentPath().getLeaf();
      fix = new SuggestedFix().delete(parent);
    }
    return describeMatch(methodInvocationTree, fix);
  }