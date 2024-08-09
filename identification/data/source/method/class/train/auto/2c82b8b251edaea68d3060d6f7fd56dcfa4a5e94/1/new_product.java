public static boolean isAnnotationPresent(Element element,
      Class<? extends Annotation> annotationClass) {
    return getAnnotationMirror(element, annotationClass).isPresent();
  }