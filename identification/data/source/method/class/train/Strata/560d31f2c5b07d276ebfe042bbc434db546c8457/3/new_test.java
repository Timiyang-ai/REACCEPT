  @Test
  public void test_and() {
    ObjLongPredicate<String> fn1 = (a, b) -> b > 3;
    ObjLongPredicate<String> fn2 = (a, b) -> a.length() > 3;
    ObjLongPredicate<String> and = fn1.and(fn2);
    assertThat(fn1.test("a", 2L)).isFalse();
    assertThat(fn1.test("a", 4L)).isTrue();
    assertThat(fn2.test("a", 4L)).isFalse();
    assertThat(fn2.test("abcd", 4L)).isTrue();
    assertThat(and.test("a", 2L)).isFalse();
    assertThat(and.test("a", 4L)).isFalse();
    assertThat(and.test("abcd", 2L)).isFalse();
    assertThat(and.test("abcd", 4L)).isTrue();
  }