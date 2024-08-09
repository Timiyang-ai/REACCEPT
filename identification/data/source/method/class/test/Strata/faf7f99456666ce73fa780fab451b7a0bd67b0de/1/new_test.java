  @Test
  public void test_equals_hashCode() {
    Country a1 = Country.GB;
    Country a2 = Country.of("GB");
    Country b = Country.EU;
    assertThat(a1)
        .isEqualTo(a1)
        .isEqualTo(a2)
        .isNotEqualByComparingTo(b)
        .isNotEqualTo("")
        .isNotEqualTo(null)
        .hasSameHashCodeAs(a2);
  }