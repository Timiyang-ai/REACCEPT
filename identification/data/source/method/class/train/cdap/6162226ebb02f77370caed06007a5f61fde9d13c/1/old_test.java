@Test
  public void testFlowHistory() throws Exception {
    try {
      HttpResponse response = deploy(WordCount.class);
      Assert.assertEquals(200, response.getStatusLine().getStatusCode());
      Assert.assertEquals(200,
                          GatewayFastTestsSuite.doPost("/v2/apps/WordCount/flows/WordCounter/start", null)
                            .getStatusLine().getStatusCode());
      Assert.assertEquals(200,
                          GatewayFastTestsSuite.doPost("/v2/apps/WordCount/flows/WordCounter/stop", null)
                            .getStatusLine().getStatusCode());
      Assert.assertEquals(200,
                          GatewayFastTestsSuite.doPost("/v2/apps/WordCount/flows/WordCounter/start", null)
                            .getStatusLine().getStatusCode());
      Assert.assertEquals(200,
                          GatewayFastTestsSuite.doPost("/v2/apps/WordCount/flows/WordCounter/stop", null)
                            .getStatusLine().getStatusCode());
      Assert.assertEquals(200, GatewayFastTestsSuite.doDelete("/v2/apps/WordCount").getStatusLine().getStatusCode());

      response = GatewayFastTestsSuite.doGet("/v2/apps/WordCount/flows/WordCounter/history");
      Assert.assertEquals(200, response.getStatusLine().getStatusCode());
      String s = EntityUtils.toString(response.getEntity());
      List<Map<String, String>> o = new Gson().fromJson(s, new TypeToken<List<Map<String, String>>>(){}.getType());

      // We started and stopped twice, so we should have 2 entries.
      Assert.assertTrue(o.size() >= 2);

      // For each one, we have 4 fields.
      for (Map<String, String> m : o) {
        Assert.assertEquals(4, m.size());
      }
    } finally {
      Assert.assertEquals(200, GatewayFastTestsSuite.doDelete("/v2/apps").getStatusLine().getStatusCode());
    }
  }