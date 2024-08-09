  @Test
  public void maybeChooseServiceConfig_failsOnMisspelling() {
    Map<String, Object> bad = new LinkedHashMap<>();
    bad.put("parcentage", 1.0);
    thrown.expectMessage("Bad key");

    DnsNameResolver.maybeChooseServiceConfig(bad, new Random(), "host");
  }