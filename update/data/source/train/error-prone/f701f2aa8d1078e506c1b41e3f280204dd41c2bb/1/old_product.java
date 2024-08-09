@Override
  @SuppressWarnings("unchecked")    // matchers + varargs cause this
  public Description matchMethod(MethodTree methodTree, VisitorState state) {
    if (!allOf(
        methodHasVisibility(Visibility.PUBLIC),
        methodIsNamed("equals"),
        methodReturns(state.getSymtab().booleanType),
        methodHasParameters(variableType(isSameType(findEnclosingClass(state)))),
        enclosingClass(not(hasMethod(Matchers.<MethodTree>allOf(
            methodIsNamed("equals"),
            methodReturns(state.getSymtab().booleanType),
            methodHasParameters(variableType(isSameType(state.getSymtab().objectType)))))))
    ).matches(methodTree, state)) {
      return Description.NO_MATCH;
    }

    SuggestedFix fix = new SuggestedFix();
    JCClassDecl cls = (JCClassDecl) EnclosingClass.findEnclosingClass(state);

    if ((cls.getModifiers().flags & ENUM) != 0) {
      /* If the enclosing class is an enum, then just delete the equals method since enums
       * should always be compared for reference equality. Enum defines a final equals method for
       * just this reason. */
      fix.delete(methodTree);
    } else {
      /* Otherwise, change the covariant equals method to override Object.equals. */
      JCTree parameterType = (JCTree) methodTree.getParameters().get(0).getType();
      Name parameterName = ((JCVariableDecl) methodTree.getParameters().get(0)).getName();

      // Add @Override annotation if not present.
      boolean hasOverrideAnnotation = false;
      List<JCAnnotation> annotations = ((JCMethodDecl) methodTree).getModifiers().getAnnotations();
      for (JCAnnotation annotation : annotations) {
        if (annotation.annotationType.type.tsym == state.getSymtab().overrideType.tsym) {
          hasOverrideAnnotation = true;
        }
      }
      if (!hasOverrideAnnotation) {
        fix.prefixWith(methodTree, "@Override\n");
      }

      // Change method signature, substituting Object for parameter type.
      fix.replace(parameterType, "Object");

      // If there is a method body...
      if (methodTree.getBody() != null) {

        // Add type check at start
        String typeCheckStmt = "if (!(" + parameterName + " instanceof " + parameterType + ")) {\n"
            + "  return false;\n"
            + "}\n";
        fix.prefixWith(methodTree.getBody().getStatements().get(0), typeCheckStmt);

        // Cast all uses of the parameter name using a recursive TreeScanner.
        new CastScanner().scan(methodTree.getBody(), new CastState(parameterName,
            parameterType.toString(), fix));
      }
    }

    return describeMatch(methodTree, fix);
  }