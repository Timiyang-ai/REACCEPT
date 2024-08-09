  @Test
  public void test_equals() {
    ObjIntPair<String> a = ObjIntPair.of("1", 2);
    ObjIntPair<String> a2 = ObjIntPair.of("1", 2);
    ObjIntPair<String> b = ObjIntPair.of("1", 3);
    ObjIntPair<String> c = ObjIntPair.of("2", 2);
    ObjIntPair<String> d = ObjIntPair.of("2", 3);

    assertThat(a)
        .isEqualTo(a)
        .isEqualTo(a2)
        .isNotEqualTo(b)
        .isNotEqualTo(c)
        .isNotEqualTo(d)
        .isNotEqualTo(null)
        .isNotEqualTo(ANOTHER_TYPE)
        .isNotEqualTo(Pair.of(Integer.valueOf(1), Integer.valueOf(1)))
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