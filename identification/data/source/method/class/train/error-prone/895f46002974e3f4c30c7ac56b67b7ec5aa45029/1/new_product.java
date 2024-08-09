@Override
  public Description matchMethod(MethodTree methodTree, VisitorState state) {
    @SuppressWarnings("unchecked")
    Matcher<MethodTree> methodMatcher = allOf(
        not(methodNameStartsWith("test")),
        Matchers.<MethodTree>hasModifier(Modifier.PUBLIC),
        methodReturns(VOID_TYPE),
        methodHasParameters(),
        enclosingClass(isJUnit3TestClass));
    if (!methodMatcher.matches(methodTree, state)) {
      return Description.NO_MATCH;
    }

    String name = methodTree.getName().toString();
    String fixedName;
    // regex.Matcher class name collides with errorprone.Matcher
    java.util.regex.Matcher matcher = MISSPELLED_NAME.matcher(name);
    if (matcher.lookingAt()) {
      fixedName = matcher.replaceFirst("test");
    } else if (wouldRunInJUnit4.matches(methodTree, state)) {
      fixedName = "test" + name.substring(0, 1).toUpperCase() + name.substring(1);
    } else {
      return Description.NO_MATCH;
    }
    // We don't have start position for a method symbol, so we replace everything between result
    // type and body.
    JCMethodDecl decl = (JCMethodDecl) methodTree;
    Fix fix = new SuggestedFix().replace(
        decl.restype.getStartPosition() + 4, decl.body.getStartPosition(), " " + fixedName + "() ");
    return describeMatch(methodTree, fix);
  }