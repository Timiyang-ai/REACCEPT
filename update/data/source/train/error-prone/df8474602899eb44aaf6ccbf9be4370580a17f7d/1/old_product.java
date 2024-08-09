@Override
  @SuppressWarnings("unchecked")
  public Description matchMethod(MethodTree methodTree, VisitorState state) {
    boolean matches = allOf(
        methodMatcher(),
        not(hasAnnotationOnAnyOverriddenMethod(JUNIT_TEST)),
        enclosingClass(isJUnit4TestClass))
        .matches(methodTree, state);
    if (!matches) {
      return Description.NO_MATCH;
    }

    // For each annotationReplacement, replace the first annotation that matches. If any of them
    // matches, don't try and do the rest of the work.
    Description description;
    for (AnnotationReplacements replacement : annotationReplacements()) {
      description = tryToReplaceAnnotation(
          methodTree, state, replacement.badAnnotation, replacement.goodAnnotation);
      if (description != null) {
        return description;
      }
    }

    // Search for another @Before annotation on the method and replace the import
    // if we find one
    String correctAnnotation = correctAnnotation();
    String unqualifiedClassName = getUnqualifiedClassName(correctAnnotation);
    for (AnnotationTree annotationNode : methodTree.getModifiers().getAnnotations()) {
      String annotationClassName =
          ASTHelpers.getSymbol(annotationNode).getQualifiedName().toString();
      if (annotationClassName.endsWith("." + unqualifiedClassName)) {
        SuggestedFix.Builder suggestedFix = SuggestedFix.builder()
            .removeImport(annotationClassName)
            .addImport(correctAnnotation);
        if (makeProtectedPublic(methodTree, state, unqualifiedClassName, suggestedFix, false)
            == null) {
          // No source position available, don't suggest a fix
          return describeMatch(annotationNode, Fix.NO_FIX);
        }
        suggestedFix.replace(annotationNode, "@" + unqualifiedClassName);
        return describeMatch(annotationNode, suggestedFix.build());
      }
    }

    // Add correctAnnotation() to the unannotated method
    // (and convert protected to public if it is)
    SuggestedFix.Builder suggestedFix = SuggestedFix.builder().addImport(correctAnnotation);

    // The makeProtectedPublic will take care of adding the annotation for us
    Boolean annotationAdded =
        makeProtectedPublic(methodTree, state, unqualifiedClassName, suggestedFix, true);
    //
    if (annotationAdded == null) {
      // No source position available, don't suggest a fix
      return describeMatch(methodTree, Fix.NO_FIX);
    }

    if (!annotationAdded) {
      suggestedFix.prefixWith(methodTree, "@" + unqualifiedClassName + "\n");
    }

    return describeMatch(methodTree, suggestedFix.build());
  }