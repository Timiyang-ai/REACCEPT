  @Test
  public void test_compareKey() {
    SwaptionSabrSensitivity a1 = SwaptionSabrSensitivity.of(
        NAME, EXPIRY, TENOR, SabrParameterType.ALPHA, GBP, 32d);
    SwaptionSabrSensitivity a2 = SwaptionSabrSensitivity.of(
        NAME, EXPIRY, TENOR, SabrParameterType.ALPHA, GBP, 32d);
    SwaptionSabrSensitivity b = SwaptionSabrSensitivity.of(
        NAME, EXPIRY, TENOR, SabrParameterType.ALPHA, USD, 32d);
    SwaptionSabrSensitivity c = SwaptionSabrSensitivity.of(
        NAME, EXPIRY + 1, TENOR, SabrParameterType.ALPHA, GBP, 32d);
    SwaptionSabrSensitivity d = SwaptionSabrSensitivity.of(
        NAME, EXPIRY, TENOR + 1, SabrParameterType.ALPHA, GBP, 32d);
    ZeroRateSensitivity other = ZeroRateSensitivity.of(GBP, 2d, 32d);
    assertThat(a1.compareKey(a2)).isEqualTo(0);
    assertThat(a1.compareKey(b) < 0).isTrue();
    assertThat(a1.compareKey(b) < 0).isTrue();
    assertThat(a1.compareKey(c) < 0).isTrue();
    assertThat(a1.compareKey(d) < 0).isTrue();
    assertThat(a1.compareKey(other) < 0).isTrue();
    assertThat(other.compareKey(a1) > 0).isTrue();
  }