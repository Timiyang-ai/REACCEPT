  @Test
  public void test_and() {
    ObjIntPredicate<String> fn1 = (a, b) -> b > 3;
    ObjIntPredicate<String> fn2 = (a, b) -> a.length() > 3;
    ObjIntPredicate<String> and = fn1.and(fn2);
    assertThat(fn1.test("a", 2)).isFalse();
    assertThat(fn1.test("a", 4)).isTrue();
    assertThat(fn2.test("a", 4)).isFalse();
    assertThat(fn2.test("abcd", 4)).isTrue();
    assertThat(and.test("a", 2)).isFalse();
    assertThat(and.test("a", 4)).isFalse();
    assertThat(and.test("abcd", 2)).isFalse();
    assertThat(and.test("abcd", 4)).isTrue();
  }