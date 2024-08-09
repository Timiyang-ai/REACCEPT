public static Set<? extends Annotation> getFieldJsonQualifierAnnotations(Class<?> clazz,
      String fieldName) {
    try {
      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
      Set<Annotation> annotations = new LinkedHashSet<>(fieldAnnotations.length);
      for (Annotation annotation : fieldAnnotations) {
        if (annotation.annotationType().isAnnotationPresent(JsonQualifier.class)) {
          annotations.add(annotation);
        }
      }
      return Collections.unmodifiableSet(annotations);
    } catch (NoSuchFieldException e) {
      throw new IllegalArgumentException("Could not access field "
          + fieldName
          + " on class "
          + clazz.getCanonicalName(),
          e);
    }
  }