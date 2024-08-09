@Test(enabled = true, description = "UpdateBrokerage by http")
  public void test03UpdateBrokerage() {
    response = HttpMethed.sendCoin(httpnode, fromAddress, witnessAddress, amount, testKey002);
    Assert.assertTrue(HttpMethed.verificationResult(response));
    HttpMethed.waitToProduceOneBlock(httpnode);

    //update brokerage
    response = HttpMethed.updateBrokerage(httpnode, witnessAddress, 11L, witnessKey);
    Assert.assertTrue(HttpMethed.verificationResult(response));
    HttpMethed.waitToProduceOneBlock(httpnode);

    response = HttpMethed.sendCoin(httpnode, fromAddress, witnessAddress2, amount, testKey002);
    Assert.assertTrue(HttpMethed.verificationResult(response));
    HttpMethed.waitToProduceOneBlock(httpnode);

//    //update brokerage onvisible
//    response = HttpMethed
//        .updateBrokerageOnVisible(httpnode, witnessAddress2, 24L, witnessKey2, "true");
//    Assert.assertTrue(HttpMethed.verificationResult(response));
//    HttpMethed.waitToProduceOneBlock(httpnode);
//
//    //update brokerage onvisible
//    response = HttpMethed
//        .updateBrokerageOnVisible(httpnode, witnessAddress, 88L, witnessKey, "false");
//    Assert.assertTrue(HttpMethed.verificationResult(response));
//    HttpMethed.waitToProduceOneBlock(httpnode);
//
//    //update brokerage onvisible
//    response = HttpMethed
//        .updateBrokerageOnVisible(httpnode, fromAddress, 78L, testKey002, "true");
////    Assert.assertTrue(HttpMethed.verificationResult(response));
//    HttpMethed.waitToProduceOneBlock(httpnode);
  }