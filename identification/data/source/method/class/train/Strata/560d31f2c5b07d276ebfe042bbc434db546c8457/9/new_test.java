  @Test
  public void test_negate() {
    ObjIntPredicate<String> fn1 = (a, b) -> b > 3;
    ObjIntPredicate<String> negate = fn1.negate();
    assertThat(fn1.test("a", 2)).isFalse();
    assertThat(fn1.test("a", 4)).isTrue();
    assertThat(negate.test("a", 2)).isTrue();
    assertThat(negate.test("a", 4)).isFalse();
  }