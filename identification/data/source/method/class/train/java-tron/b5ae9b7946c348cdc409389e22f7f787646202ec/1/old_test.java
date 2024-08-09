@Test(enabled = true, description = "GetBrokerage by http")
  public void test01GetBrokerage() {
    response = HttpMethed.getBrokerage(httpnode, witnessAddress);
    responseContent = HttpMethed.parseResponseContent(response);
    HttpMethed.printJsonContent(responseContent);
    Assert.assertEquals("20", responseContent.getString("brokerage"));
  }