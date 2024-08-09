  @Test
  public void test_withParameterMetadata() {
    DefaultCurveMetadata base = DefaultCurveMetadata.of(CURVE_NAME);
    DefaultCurveMetadata test = base.withParameterMetadata(ParameterMetadata.listOfEmpty(2));
    assertThat(test.getParameterMetadata().isPresent()).isTrue();
    assertThat(test.getParameterMetadata().get()).containsAll(ParameterMetadata.listOfEmpty(2));
    // redo for test coverage
    DefaultCurveMetadata test2 = test.withParameterMetadata(ParameterMetadata.listOfEmpty(3));
    assertThat(test2.getParameterMetadata().isPresent()).isTrue();
    assertThat(test2.getParameterMetadata().get()).containsAll(ParameterMetadata.listOfEmpty(3));
  }