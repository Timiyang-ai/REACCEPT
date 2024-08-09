  @Test
  public void transformTest() {
    for (int n = 2; n < 13; n++) {
      double[] from = new double[n - 1];
      for (int j = 0; j < n - 1; j++) {
        from[j] = RANDOM.nextDouble() * Math.PI / 2;
      }
      SumToOne trans = new SumToOne(n);
      DoubleArray to = trans.transform(DoubleArray.copyOf(from));
      assertThat(n).isEqualTo(to.size());
      double sum = 0;
      for (int i = 0; i < n; i++) {
        sum += to.get(i);
      }
      assertThat(sum).as("vector length " + n).isCloseTo(1.0, offset(1e-9));
    }
  }