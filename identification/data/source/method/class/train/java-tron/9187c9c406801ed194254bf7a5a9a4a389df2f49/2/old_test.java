@Test(enabled = true, description = "Get account by http")
  public void getAccount() {
    response = HttpMethed.getAccount(httpnode,fromAddress);
    try {
      entity = response.getEntity();
    } catch (Exception e) {
      e.printStackTrace();
    }
    logger.info("code is " + response.getStatusLine().getStatusCode());
    Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
    responseContent = HttpMethed.parseResponseContent(response);
    HttpMethed.printJsonContent(responseContent);
    Assert.assertTrue(responseContent.size() > 3);
  }