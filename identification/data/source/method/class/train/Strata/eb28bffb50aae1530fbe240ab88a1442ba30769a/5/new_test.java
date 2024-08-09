  @Test
  public void test_binaryOperator_success() {
    BinaryOperator<String> a = Unchecked.binaryOperator((t, u) -> t + u);
    assertThat(a.apply("A", "B")).isEqualTo("AB");
  }