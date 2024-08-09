  @Test
  public void test_negate() {
    ObjDoublePredicate<String> fn1 = (a, b) -> b > 3;
    ObjDoublePredicate<String> negate = fn1.negate();
    assertThat(fn1.test("a", 2.3d)).isFalse();
    assertThat(fn1.test("a", 3.2d)).isTrue();
    assertThat(negate.test("a", 2.3d)).isTrue();
    assertThat(negate.test("a", 3.2d)).isFalse();
  }