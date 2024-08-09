@Test
  public void testDeploy() throws Exception {
    HttpResponse response = deploy(WordCount.class);
    Assert.assertEquals(200, response.getStatusLine().getStatusCode());
  }