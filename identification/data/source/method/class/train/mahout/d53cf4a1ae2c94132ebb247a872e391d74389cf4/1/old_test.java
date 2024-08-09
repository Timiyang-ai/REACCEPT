@Test
  public void testForwardAlgorithm() {
    // intialize the expected alpha values
    double alphaExpectedA[][] = {
        {0.02, 0.0392, 0.002438, 0.00035456, 0.0011554672, 7.158497e-04,
            4.614927e-05},
        {0.01, 0.0054, 0.001824, 0.00069486, 0.0007586904, 2.514137e-04,
            1.721505e-05},
        {0.32, 0.0262, 0.002542, 0.00038026, 0.0001360234, 3.002345e-05,
            9.659608e-05},
        {0.03, 0.0000, 0.013428, 0.00951084, 0.0000000000, 0.000000e+00,
            2.428986e-05},};
    // fetch the alpha matrix using the forward algorithm
    Matrix alpha = HmmAlgorithms.forwardAlgorithm(model, sequence, false);
    // first do some basic checking
    Assert.assertNotNull(alpha);
    Assert.assertEquals(alpha.numCols(), 4);
    Assert.assertEquals(alpha.numRows(), 7);
    // now compare the resulting matrices
    for (int i = 0; i < 4; ++i)
      for (int j = 0; j < 7; ++j)
        Assert.assertEquals(alphaExpectedA[i][j], alpha.get(j, i), 0.00001);
  }