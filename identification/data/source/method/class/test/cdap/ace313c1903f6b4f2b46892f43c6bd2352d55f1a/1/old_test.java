@Test
  public void testDeploy() throws Exception {
    HttpResponse response = deploy("WordCount.jar");
    Assert.assertEquals(200, response.getStatusLine().getStatusCode());
  }