@Override
  public Description describe(MethodTree methodTree, VisitorState state) {
    if (methodHasModifier(Modifier.STATIC).matches(methodTree, state)) {
      CharSequence methodSource = state.getSourceForNode((JCMethodDecl) methodTree);
      if (methodSource != null) {
        String methodString = "@Test\n" + methodSource.toString().replaceFirst(" static ", " ");
        SuggestedFix fix = new SuggestedFix()
            .addImport(JUNIT4_TEST_ANNOTATION)
            .replace(methodTree, methodString);
        return new Description(methodTree, getDiagnosticMessage(), fix);
      }
    }
    SuggestedFix fix = new SuggestedFix()
        .addImport(JUNIT4_TEST_ANNOTATION)
        .prefixWith(methodTree, "@Test\n");
    return new Description(methodTree, getDiagnosticMessage(), fix);
  }