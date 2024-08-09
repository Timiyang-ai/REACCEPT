  @Test public void getFieldJsonQualifierAnnotations_privateFieldTest() {
    Set<? extends Annotation> annotations = Types.getFieldJsonQualifierAnnotations(ClassWithAnnotatedFields.class,
        "privateField");

    assertThat(annotations).hasSize(1);
    assertThat(annotations.iterator().next()).isInstanceOf(FieldAnnotation.class);
  }