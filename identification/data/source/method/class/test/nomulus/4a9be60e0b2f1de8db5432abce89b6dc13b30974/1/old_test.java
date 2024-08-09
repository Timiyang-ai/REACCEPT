  @Test
  public void test_addDomainRefreshTask_success() {
    createTld("tld");
    dnsQueue.addDomainRefreshTask("octopus.tld");
    assertTasksEnqueued(
        "dns-pull",
        new TaskMatcher()
            .param("Target-Type", "DOMAIN")
            .param("Target-Name", "octopus.tld")
            .param("Create-Time", "2010-01-01T10:00:00.000Z")
            .param("tld", "tld"));
  }