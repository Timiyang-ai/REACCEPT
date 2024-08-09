  @Test
  public void test_negate() {
    ObjLongPredicate<String> fn1 = (a, b) -> b > 3;
    ObjLongPredicate<String> negate = fn1.negate();
    assertThat(fn1.test("a", 2L)).isFalse();
    assertThat(fn1.test("a", 4L)).isTrue();
    assertThat(negate.test("a", 2L)).isTrue();
    assertThat(negate.test("a", 4L)).isFalse();
  }