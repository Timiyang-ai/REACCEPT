static IdResolver fromAnnotation(Class<? extends Annotation> annotation) {
    Objects.requireNonNull(annotation, "annotation");
    IdResolver idResolver = fromPredicate(a -> a instanceof AnnotatedElement && ((AnnotatedElement) a).isAnnotationPresent(annotation));
    return type -> {
      try {
        return idResolver.resolve(type);
      } catch (IllegalArgumentException e) {
        // provide better exception message showing annotation name
        throw new IllegalArgumentException(String.format("ID annotation @%s (%s) not found in %s: %s", annotation.getSimpleName(), annotation.getName(), type, e.getMessage()));
      }
    };
  }