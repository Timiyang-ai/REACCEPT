@Override
  @SuppressWarnings("unchecked")
  public Description matchMethod(MethodTree methodTree, VisitorState state) {
    if (!allOf(methodNameStartsWith("test"),
        methodHasParameters(),
        methodHasModifier(Modifier.PUBLIC),
        not(hasJUnitAnnotation),
        enclosingClass(isJUnit4TestClass))
        .matches(methodTree, state)) {
      return Description.NO_MATCH;
    }

    /*
     * Add the @Test annotation.  If the method is static, also make the method non-static.
     *
     * TODO(eaftan): The static case here relies on having tree end positions available.  Come up
     * with a better way of doing this that doesn't require tree end positions.  Maybe we should
     * just not provide suggested fixes for these few cases when the javac infrastructure gets in
     * the way.
     */
    if (methodHasModifier(Modifier.STATIC).matches(methodTree, state)) {
      CharSequence methodSource = state.getSourceForNode((JCMethodDecl) methodTree);
      if (methodSource != null) {
        String methodString = "@Test\n" + methodSource.toString().replaceFirst(" static ", " ");
        SuggestedFix fix = new SuggestedFix()
            .addImport(JUNIT4_TEST_ANNOTATION)
            .replace(methodTree, methodString);
        return describeMatch(methodTree, fix);
      }
    }
    SuggestedFix fix = new SuggestedFix()
        .addImport(JUNIT4_TEST_ANNOTATION)
        .prefixWith(methodTree, "@Test\n");
    return describeMatch(methodTree, fix);
  }