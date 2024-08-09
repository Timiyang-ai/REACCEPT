  @Test
  public void test_unaryOperator_success() {
    UnaryOperator<String> a = Unchecked.unaryOperator((t) -> t);
    assertThat(a.apply("A")).isEqualTo("A");
  }