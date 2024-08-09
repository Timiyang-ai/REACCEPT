@Test(enabled = true, description = "Get accountNet by http")
  public void getAccountNet() {
    response = HttpMethed.getAccountNet(httpnode,fromAddress);
    try {
      entity = response.getEntity();
    } catch (Exception e) {
      e.printStackTrace();
    }
    logger.info("code is " + response.getStatusLine().getStatusCode());
    Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
    responseContent = HttpMethed.parseResponseContent(response);
    HttpMethed.printJsonContent(responseContent);
    for (String str:responseContent.keySet()) {
      if (str.equals("freeNetLimit")) {
        Assert.assertEquals(responseContent.get(str),5000);
      }
      if (str.equals("TotalNetLimit")) {
        Assert.assertEquals(responseContent.get(str),43200000000L);
      }
    }
    Assert.assertTrue(responseContent.size() >= 4);
  }