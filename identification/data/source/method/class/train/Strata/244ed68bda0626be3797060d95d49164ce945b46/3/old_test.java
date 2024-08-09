  @Test
  public void test_equals_hashCode() {
    FxRate a1 = FxRate.of(AUD, GBP, 1.25d);
    FxRate a2 = FxRate.of(AUD, GBP, 1.25d);
    FxRate b = FxRate.of(USD, GBP, 1.25d);
    FxRate c = FxRate.of(USD, GBP, 1.35d);

    assertThat(a1.equals(a1)).isEqualTo(true);
    assertThat(a1.equals(a2)).isEqualTo(true);
    assertThat(a1.equals(b)).isEqualTo(false);
    assertThat(a1.equals(c)).isEqualTo(false);

    assertThat(b.equals(a1)).isEqualTo(false);
    assertThat(b.equals(a2)).isEqualTo(false);
    assertThat(b.equals(b)).isEqualTo(true);
    assertThat(b.equals(c)).isEqualTo(false);

    assertThat(c.equals(a1)).isEqualTo(false);
    assertThat(c.equals(a2)).isEqualTo(false);
    assertThat(c.equals(b)).isEqualTo(false);
    assertThat(c.equals(c)).isEqualTo(true);

    assertThat(a1.hashCode()).isEqualTo(a2.hashCode());
  }