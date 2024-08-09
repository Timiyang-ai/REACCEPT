  @Test
  public void test_lambda() {
    DoubleArray cashFlow = DoubleArray.of(1.1342484780379178E8, 178826.75595605336, -1.1353458434950349E8);
    DoubleArray alphaSq = DoubleArray.of(0.0059638289722142215, 0.0069253776359785415, 0.007985436623619701);
    DoubleArray hwH = DoubleArray.of(5.357967757629822, 5.593630711441366, 5.828706853806842);
    double computed = MODEL.lambda(cashFlow, alphaSq, hwH);
    assertThat(computed).isCloseTo(-0.0034407112369635212, offset(TOLERANCE_RATE));
    double value = 0.0;
    for (int loopcf = 0; loopcf < 3; loopcf++) {
      value += cashFlow.get(loopcf) * Math.exp(-0.5 * alphaSq.get(loopcf) - hwH.get(loopcf) * computed);
    }
    assertThat(value).isCloseTo(0d, offset(1.0E-7));
  }