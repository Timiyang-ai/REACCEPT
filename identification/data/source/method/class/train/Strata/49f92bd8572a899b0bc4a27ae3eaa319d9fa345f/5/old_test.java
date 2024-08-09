  @Test
  public void test_relativeTime() {
    SabrParametersSwaptionVolatilities prov = SabrParametersSwaptionVolatilities.of(NAME, CONV, DATE_TIME, PARAM);
    double test1 = prov.relativeTime(DATE_TIME);
    assertThat(test1).isEqualTo(0d);
    double test2 = prov.relativeTime(DATE_TIME.plusYears(2));
    double test3 = prov.relativeTime(DATE_TIME.minusYears(2));
    assertThat(test2).isCloseTo(-test3, offset(1e-2));
  }