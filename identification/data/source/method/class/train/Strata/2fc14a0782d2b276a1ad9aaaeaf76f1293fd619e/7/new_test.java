  @Test
  public void jacobianTest() {
    final int n = 5;

    final SumToOne trans = new SumToOne(n);
    Function<DoubleArray, DoubleArray> func = new Function<DoubleArray, DoubleArray>() {
      @Override
      public DoubleArray apply(DoubleArray theta) {
        return trans.transform(theta);
      }
    };

    Function<DoubleArray, DoubleMatrix> jacFunc = new Function<DoubleArray, DoubleMatrix>() {
      @Override
      public DoubleMatrix apply(DoubleArray theta) {
        return trans.jacobian(theta);
      }
    };

    Function<DoubleArray, DoubleMatrix> fdJacFunc = DIFFER.differentiate(func);

    for (int tries = 0; tries < 10; tries++) {
      DoubleArray vTheta = DoubleArray.of(n - 1, i -> RANDOM.nextDouble());
      DoubleMatrix jac = jacFunc.apply(vTheta);
      DoubleMatrix fdJac = fdJacFunc.apply(vTheta);
      for (int j = 0; j < n - 1; j++) {
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
          sum += jac.get(i, j);
          assertThat(jac.get(i, j)).as("element " + i + " " + j).isCloseTo(fdJac.get(i, j), offset(1e-6));
        }
        assertThat(sum).as("wrong sum of sensitivities").isCloseTo(0.0, offset(1e-15));
      }

    }
  }