  @Test
  public void test_equals_hashCode() {
    Object a1 = Currency.GBP;
    Object a2 = Currency.of("GBP");
    Object b = Currency.EUR;
    assertThat(a1.equals(a1)).isEqualTo(true);
    assertThat(a1.equals(b)).isEqualTo(false);
    assertThat(a1.equals(a2)).isEqualTo(true);

    assertThat(a2.equals(a1)).isEqualTo(true);
    assertThat(a2.equals(a2)).isEqualTo(true);
    assertThat(a2.equals(b)).isEqualTo(false);

    assertThat(b.equals(a1)).isEqualTo(false);
    assertThat(b.equals(a2)).isEqualTo(false);
    assertThat(b.equals(b)).isEqualTo(true);

    assertThat(a1.hashCode()).isEqualTo(a2.hashCode());
  }