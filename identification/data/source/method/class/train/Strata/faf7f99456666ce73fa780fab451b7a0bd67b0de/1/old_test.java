  @Test
  public void test_equals_hashCode() {
    Frequency a1 = P1D;
    Frequency a2 = Frequency.ofDays(1);
    Frequency b = P3M;
    assertThat(a1)
        .isEqualTo(a1)
        .isEqualTo(a2)
        .isNotEqualTo(b)
        .isNotEqualTo("")
        .isNotEqualTo(null)
        .hasSameHashCodeAs(a2);
  }