  @Test
  public void inverseTransformTest() {
    for (int n = 2; n < 13; n++) {
      double[] theta = new double[n - 1];
      for (int j = 0; j < n - 1; j++) {
        theta[j] = RANDOM.nextDouble() * Math.PI / 2;
      }
      SumToOne trans = new SumToOne(n);
      DoubleArray w = trans.transform(DoubleArray.copyOf(theta));

      DoubleArray theta2 = trans.inverseTransform(w);
      for (int j = 0; j < n - 1; j++) {
        assertThat(theta[j]).as("element " + j + ", of vector length " + n).isCloseTo(theta2.get(j), offset(1e-9));
      }
    }
  }