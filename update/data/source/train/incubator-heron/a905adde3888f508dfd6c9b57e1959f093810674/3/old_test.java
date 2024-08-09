@Test
  public void testExtractTopologyTimeout() throws Exception {
    Assert.assertEquals(1, PhysicalPlanUtil.extractTopologyTimeout(topology));
  }