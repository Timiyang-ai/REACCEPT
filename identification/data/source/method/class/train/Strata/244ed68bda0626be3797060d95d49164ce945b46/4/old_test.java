  @Test
  public void test_equals() {
    Triple<Integer, String, String> a = Triple.of(1, "Hello", "Triple");
    Triple<Integer, String, String> a2 = Triple.of(1, "Hello", "Triple");
    Triple<Integer, String, String> b = Triple.of(1, "Goodbye", "Triple");
    Triple<Integer, String, String> c = Triple.of(2, "Hello", "Triple");
    Triple<Integer, String, String> d = Triple.of(2, "Goodbye", "Triple");
    Triple<Integer, String, String> e = Triple.of(2, "Goodbye", "Other");

    assertThat(a)
        .isEqualTo(a)
        .isEqualTo(a2)
        .isNotEqualTo(b)
        .isNotEqualTo(c)
        .isNotEqualTo(d)
        .isNotEqualTo(e)
        .isNotEqualTo(null)
        .isNotEqualTo(ANOTHER_TYPE)
        .isNotEqualTo(Pair.of(Integer.valueOf(1), Double.valueOf(1.7d)))
        .hasSameHashCodeAs(a2);

    assertThat(b)
        .isNotEqualTo(a)
        .isEqualTo(b)
        .isNotEqualTo(c)
        .isNotEqualTo(d)
        .isNotEqualTo(e);

    assertThat(c)
        .isNotEqualTo(a)
        .isEqualTo(c)
        .isNotEqualTo(b)
        .isNotEqualTo(d)
        .isNotEqualTo(e);

    assertThat(d)
        .isNotEqualTo(a)
        .isNotEqualTo(b)
        .isNotEqualTo(c)
        .isEqualTo(d)
        .isNotEqualTo(e);

    assertThat(e)
        .isNotEqualTo(a)
        .isNotEqualTo(b)
        .isNotEqualTo(c)
        .isNotEqualTo(d)
        .isEqualTo(e);
  }