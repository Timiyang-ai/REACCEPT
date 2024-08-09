  @Test
  public void test_tenor() {
    SabrParametersSwaptionVolatilities prov = SabrParametersSwaptionVolatilities.of(NAME, CONV, DATE_TIME, PARAM);
    double test1 = prov.tenor(DATE, DATE);
    assertThat(test1).isEqualTo(0d);
    double test2 = prov.tenor(DATE, DATE.plusYears(2));
    double test3 = prov.tenor(DATE, DATE.minusYears(2));
    assertThat(test2).isEqualTo(-test3);
    double test4 = prov.tenor(DATE, LocalDate.of(2019, 2, 2));
    double test5 = prov.tenor(DATE, LocalDate.of(2018, 12, 31));
    assertThat(test4).isEqualTo(5d);
    assertThat(test5).isEqualTo(5d);
  }