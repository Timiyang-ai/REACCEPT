@Test(enabled = true, description = "Get now block by http")
  public void get1NowBlock() {
    response = HttpMethed.getNowBlock(httpnode);
    logger.info("code is " + response.getStatusLine().getStatusCode());
    Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
    responseContent = HttpMethed.parseResponseContent(response);
    blockContent = responseContent;
    blockId = responseContent.get("blockID").toString();
    HttpMethed.printJsonContent(responseContent);
    Assert.assertTrue(responseContent.size() >= 2);
    responseContent = HttpMethed.parseStringContent(responseContent.get("block_header").toString());
    Assert.assertTrue(responseContent.size() >= 2);
    Assert.assertFalse(responseContent.get("witness_signature").toString().isEmpty());
    HttpMethed.printJsonContent(responseContent);
    responseContent = HttpMethed.parseStringContent(responseContent.get("raw_data").toString());
    HttpMethed.printJsonContent(responseContent);
    Assert.assertTrue(Integer.parseInt(responseContent.get("number").toString()) > 0);
    currentBlockNum = Integer.parseInt(responseContent.get("number").toString());
    Assert.assertTrue(Long.parseLong(responseContent.get("timestamp").toString()) > 1550724114000L);
    Assert.assertFalse(responseContent.get("witness_address").toString().isEmpty());
  }