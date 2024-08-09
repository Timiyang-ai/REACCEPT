  @Test
  void fromAnnotation() {
    IdResolver resolver = IdResolver.fromAnnotation(MyAnnotation.class);

    Throwable ex = Assertions.assertThrows(IllegalArgumentException.class, () -> resolver.resolve(WithoutAnnotation.class));
    check(ex.getMessage()).contains(MyAnnotation.class.getSimpleName());
    check(resolver.resolve(WithAnnotation.class).getName()).is("id123");
  }