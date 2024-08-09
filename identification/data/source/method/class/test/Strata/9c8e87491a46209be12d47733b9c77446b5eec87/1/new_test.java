  @Test
  public void test_adjustPaymentDate() {
    NotionalExchange test = NotionalExchange.of(GBP_1000, DATE_2014_06_30);
    NotionalExchange expected = NotionalExchange.of(GBP_1000, DATE_2014_06_30.plusDays(2));
    assertThat(test.adjustPaymentDate(TemporalAdjusters.ofDateAdjuster(d -> d.plusDays(0)))).isEqualTo(test);
    assertThat(test.adjustPaymentDate(TemporalAdjusters.ofDateAdjuster(d -> d.plusDays(2)))).isEqualTo(expected);
  }