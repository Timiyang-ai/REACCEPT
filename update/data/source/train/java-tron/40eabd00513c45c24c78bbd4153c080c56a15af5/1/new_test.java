@Test(enabled = true, description = "SendCoin by http")
  public void test1SendCoin() {
    response = HttpMethed.sendCoin(httpnode,fromAddress,receiverAddress,amount,testKey002);
    Assert.assertTrue(HttpMethed.verificationResult(response));
    Assert.assertEquals(HttpMethed.getBalance(httpnode,receiverAddress),amount);

  }