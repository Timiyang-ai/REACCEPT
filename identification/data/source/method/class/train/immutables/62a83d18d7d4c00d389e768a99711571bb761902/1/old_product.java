static IdResolver fromAnnotation(Class<? extends Annotation> annotation) {
    Objects.requireNonNull(annotation, "annotation");
    return fromPredicate(a -> a instanceof AnnotatedElement && ((AnnotatedElement) a).isAnnotationPresent(annotation));
  }