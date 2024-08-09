  @Test
  public void test_equals() {
    StandardId d1a = StandardId.of(SCHEME, "d1");
    StandardId d1b = StandardId.of(SCHEME, "d1");
    StandardId d2 = StandardId.of(SCHEME, "d2");
    StandardId d3 = StandardId.of("Different", "d1");
    assertThat(d1a)
        .isEqualTo(d1a)
        .isEqualTo(d1b)
        .isNotEqualTo(d2)
        .isNotEqualTo(d3)
        .isNotEqualTo("")
        .isNotEqualTo(null)
        .hasSameHashCodeAs(d1b);
  }