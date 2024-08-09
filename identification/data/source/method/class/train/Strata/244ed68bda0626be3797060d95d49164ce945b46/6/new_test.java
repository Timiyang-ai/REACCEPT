  @Test
  public void test_equals() {
    ObjDoublePair<String> a = ObjDoublePair.of("1", 2.0d);
    ObjDoublePair<String> a2 = ObjDoublePair.of("1", 2.0d);
    ObjDoublePair<String> b = ObjDoublePair.of("1", 3.0d);
    ObjDoublePair<String> c = ObjDoublePair.of("2", 2.0d);
    ObjDoublePair<String> d = ObjDoublePair.of("2", 3.0d);

    assertThat(a)
        .isEqualTo(a)
        .isEqualTo(a2)
        .isNotEqualTo(b)
        .isNotEqualTo(c)
        .isNotEqualTo(d)
        .isNotEqualTo(null)
        .isNotEqualTo(ANOTHER_TYPE)
        .isNotEqualTo(Pair.of(Integer.valueOf(1), Double.valueOf(1.7d)))
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