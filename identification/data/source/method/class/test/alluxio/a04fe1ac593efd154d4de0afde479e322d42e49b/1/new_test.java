@Test
  public void getConfiguration() throws Exception {
    Configuration.set(PropertyKey.METRICS_CONF_FILE, "abc");
    Assert.assertEquals("abc",
        getInfo().getConfiguration().get(PropertyKey.METRICS_CONF_FILE.toString()));
  }