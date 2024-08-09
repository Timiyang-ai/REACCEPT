  @ParameterizedTest
  @MethodSource("data_implicit")
  public void test_toImplicit(
      StubConvention conv, boolean initialStub, boolean finalStub, StubConvention expected) {
    if (expected == null) {
      assertThatIllegalArgumentException().isThrownBy(() -> conv.toImplicit(null, initialStub, finalStub));
    } else {
      assertThat(conv.toImplicit(null, initialStub, finalStub)).isEqualTo(expected);
    }
  }