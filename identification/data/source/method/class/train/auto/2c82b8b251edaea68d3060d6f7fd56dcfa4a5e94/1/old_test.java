  @Test
  public void isAnnotationPresent() {
    TypeElement annotatedAnnotationElement =
        compilation.getElements().getTypeElement(AnnotatedAnnotation.class.getCanonicalName());
    assertThat(MoreElements.isAnnotationPresent(annotatedAnnotationElement, Documented.class))
        .isTrue();
    assertThat(MoreElements.isAnnotationPresent(annotatedAnnotationElement, InnerAnnotation.class))
        .isTrue();
    assertThat(MoreElements.isAnnotationPresent(annotatedAnnotationElement, SuppressWarnings.class))
        .isFalse();
  }