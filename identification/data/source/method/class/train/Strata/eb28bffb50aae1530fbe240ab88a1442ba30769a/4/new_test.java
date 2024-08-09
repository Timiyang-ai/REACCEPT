  @Test
  public void test_predicate_success() {
    Predicate<String> a = Unchecked.predicate((t) -> true);
    assertThat(a.test("A")).isEqualTo(true);
  }