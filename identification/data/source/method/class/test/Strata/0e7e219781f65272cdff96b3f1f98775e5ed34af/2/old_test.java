  @Test
  public void getLowerBoundIndexTest() {
    int i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-2., -1.), -0.);
    assertThat(i).isEqualTo(1);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(1., 2.), -0.);
    assertThat(i).isEqualTo(0);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(1., 2., 3.), 2.5);
    assertThat(i).isEqualTo(1);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(1., 2., 3.), 2.);
    assertThat(i).isEqualTo(1);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(1., 2., 3.), -2.);
    assertThat(i).isEqualTo(0);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-2., -1., 0.), -0.);
    assertThat(i).isEqualTo(2);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-2., -1., 0.), 0.);
    assertThat(i).isEqualTo(2);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-2., -1., 0.), -0.);
    assertThat(i).isEqualTo(2);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-2., -1., -0.), -0.);
    assertThat(i).isEqualTo(2);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-1., 0., 1.), -0.);
    assertThat(i).isEqualTo(1);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-1., 0., 1.), 0.);
    assertThat(i).isEqualTo(1);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-1., -0., 1.), 0.);
    assertThat(i).isEqualTo(1);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-1., -0., 1.), -0.);
    assertThat(i).isEqualTo(1);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(0., 1., 2.), -0.);
    assertThat(i).isEqualTo(0);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(0., 1., 2.), 0.);
    assertThat(i).isEqualTo(0);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-0., 1., 2.), 0.);
    assertThat(i).isEqualTo(0);
    i = FunctionUtils.getLowerBoundIndex(DoubleArray.of(-0., 1., 2.), -0.);
    assertThat(i).isEqualTo(0);
  }