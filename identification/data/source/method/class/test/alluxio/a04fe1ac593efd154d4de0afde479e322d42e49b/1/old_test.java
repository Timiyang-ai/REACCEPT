@Test
  public void getConfiguration() throws Exception {
    Configuration.set(PropertyKey.METRICS_CONF_FILE, "abc");
    String result = new TestCase(mHostname, mPort,
        getEndpoint(AlluxioWorkerRestServiceHandler.GET_CONFIGURATION), NO_PARAMS, HttpMethod.GET,
        null).call();
    @SuppressWarnings("unchecked")
    Map<String, String> config =
        (Map<String, String>) new ObjectMapper().readValue(result, Map.class);
    Assert.assertEquals("abc", Configuration.get(PropertyKey.METRICS_CONF_FILE));
  }