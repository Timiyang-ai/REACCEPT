  @Test
  public void estimatePreference() throws Exception {
    DataModel dataModel = EasyMock.createMock(DataModel.class);
    Factorizer factorizer = EasyMock.createMock(Factorizer.class);
    Factorization factorization = EasyMock.createMock(Factorization.class);

    EasyMock.expect(factorizer.factorize()).andReturn(factorization);
    EasyMock.expect(factorization.getUserFeatures(1L)).andReturn(new double[] { 0.4, 2 });
    EasyMock.expect(factorization.getItemFeatures(5L)).andReturn(new double[] { 1, 0.3 });
    EasyMock.replay(dataModel, factorizer, factorization);

    SVDRecommender svdRecommender = new SVDRecommender(dataModel, factorizer);

    float estimate = svdRecommender.estimatePreference(1L, 5L);
    assertEquals(1, estimate, EPSILON);

    EasyMock.verify(dataModel, factorizer, factorization);
  }