@Test
  public void testBackwardAlgorithm() {
    // intialize the expected beta values
    double betaExpectedA[][] = {
        {0.0015730559, 0.003543656, 0.00738264, 0.040692, 0.0848, 0.17, 1},
        {0.0017191865, 0.002386795, 0.00923652, 0.052232, 0.1018, 0.17, 1},
        {0.0003825772, 0.001238558, 0.00259464, 0.012096, 0.0664, 0.66, 1},
        {0.0004390858, 0.007076994, 0.01063512, 0.013556, 0.0304, 0.17, 1}};
    // fetch the beta matrix using the backward algorithm
    Matrix beta = HmmAlgorithms.backwardAlgorithm(model, sequence, false);
    // first do some basic checking
    Assert.assertNotNull(beta);
    Assert.assertEquals(beta.numCols(), 4);
    Assert.assertEquals(beta.numRows(), 7);
    // now compare the resulting matrices
    for (int i = 0; i < 4; ++i)
      for (int j = 0; j < 7; ++j)
        Assert.assertEquals(betaExpectedA[i][j], beta.get(j, i), 0.00001);
  }