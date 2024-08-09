@Test(enabled = true, description = "SendCoin by http")
  public void test1SendCoin() {
    response = HttpMethed.sendCoin(httpnode,fromAddress,receiverAddress,amount,testKey002);
    Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
    responseContent = HttpMethed.parseResponseContent(response);
    HttpMethed.printJsonContent(responseContent);
    Assert.assertTrue(Boolean.valueOf(responseContent.get("result").toString()).booleanValue());
    Assert.assertEquals(HttpMethed.getBalance(httpnode,receiverAddress),amount);

  }