static void addError(AnnotationHolder holder, SpecModelValidationError error) {
    PsiElement errorElement = (PsiElement) error.element;
    holder.createErrorAnnotation(
        Optional.of(errorElement)
            .filter(element -> element instanceof PsiClass || element instanceof PsiMethod)
            .map(PsiNameIdentifierOwner.class::cast)
            .map(PsiNameIdentifierOwner::getNameIdentifier)
            .orElse(errorElement),
        error.message);
  }