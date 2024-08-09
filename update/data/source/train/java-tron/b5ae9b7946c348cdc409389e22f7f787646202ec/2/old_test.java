@Test(enabled = true, description = "UpdateBrokerage by http")
  public void test03UpdateBrokerage() {
    response = HttpMethed.sendCoin(httpnode, fromAddress, witnessAddress, amount, testKey002);
    Assert.assertTrue(HttpMethed.verificationResult(response));
    HttpMethed.waitToProduceOneBlock(httpnode);

    //update brokerage
    response = HttpMethed.updateBrokerage(httpnode, witnessAddress, 30L, witnessKey);
    Assert.assertTrue(HttpMethed.verificationResult(response));
    HttpMethed.waitToProduceOneBlock(httpnode);
  }