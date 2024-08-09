  @Test
  public void test_convertedTo_singleCurrency() {
    double rate = 1.5d;
    FxMatrix matrix = FxMatrix.of(CurrencyPair.of(GBP, USD), rate);
    PointSensitivities base = PointSensitivities.of(Lists.newArrayList(CS3, CS2, CS1));
    PointSensitivities test1 = base.convertedTo(USD, matrix);
    PointSensitivity c1Conv = CS1.convertedTo(USD, matrix);
    PointSensitivity c2Conv = CS2.convertedTo(USD, matrix);
    PointSensitivity c3Conv = CS3.convertedTo(USD, matrix);
    PointSensitivities expected = PointSensitivities.of(Lists.newArrayList(c3Conv, c2Conv, c1Conv));
    assertThat(test1.normalized()).isEqualTo(expected.normalized());
    PointSensitivities test2 = base.convertedTo(GBP, matrix);
    assertThat(test2.normalized()).isEqualTo(base.normalized());
  }