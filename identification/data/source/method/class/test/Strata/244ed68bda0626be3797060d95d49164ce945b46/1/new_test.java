  @Test
  public void test_equals() {
    Pair<Integer, String> a = Pair.of(1, "Hello");
    Pair<Integer, String> a2 = Pair.of(1, "Hello");
    Pair<Integer, String> b = Pair.of(1, "Goodbye");
    Pair<Integer, String> c = Pair.of(2, "Hello");
    Pair<Integer, String> d = Pair.of(2, "Goodbye");

    assertThat(a)
        .isEqualTo(a)
        .isEqualTo(a2)
        .isNotEqualTo(b)
        .isNotEqualTo(c)
        .isNotEqualTo(d)
        .isNotEqualTo(null)
        .isNotEqualTo(ANOTHER_TYPE)
        .hasSameHashCodeAs(a2);

    assertThat(b)
        .isNotEqualTo(a)
        .isEqualTo(b)
        .isNotEqualTo(c)
        .isNotEqualTo(d);

    assertThat(c)
        .isNotEqualTo(a)
        .isEqualTo(c)
        .isNotEqualTo(b)
        .isNotEqualTo(d);

    assertThat(d)
        .isNotEqualTo(a)
        .isNotEqualTo(b)
        .isNotEqualTo(c)
        .isEqualTo(d);
  }