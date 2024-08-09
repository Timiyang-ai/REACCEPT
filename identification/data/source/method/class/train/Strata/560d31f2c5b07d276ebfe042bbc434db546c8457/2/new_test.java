  @Test
  public void test_and() {
    ObjDoublePredicate<String> fn1 = (a, b) -> b > 3;
    ObjDoublePredicate<String> fn2 = (a, b) -> a.length() > 3;
    ObjDoublePredicate<String> and = fn1.and(fn2);
    assertThat(fn1.test("a", 2.3d)).isFalse();
    assertThat(fn1.test("a", 3.2d)).isTrue();
    assertThat(fn2.test("a", 3.2d)).isFalse();
    assertThat(fn2.test("abcd", 3.2d)).isTrue();
    assertThat(and.test("a", 2.3d)).isFalse();
    assertThat(and.test("a", 3.2d)).isFalse();
    assertThat(and.test("abcd", 2.3d)).isFalse();
    assertThat(and.test("abcd", 3.2d)).isTrue();
  }