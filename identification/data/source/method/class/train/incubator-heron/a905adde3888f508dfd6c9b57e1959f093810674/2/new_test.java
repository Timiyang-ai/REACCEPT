@Test
  public void testExtractTopologyTimeout() throws Exception {
    Assert.assertEquals(Duration.ofSeconds(1), PhysicalPlanUtil.extractTopologyTimeout(topology));
  }