  @Test
  public void test_enqueueDomainBaseTask_sunrise() {
    persistDomainAndEnqueueLordn(newDomainBuilder().setRepoId("A-EXAMPLE").build());
    String expectedPayload =
        "A-EXAMPLE,fleece.example,smdzzzz,1,2010-05-01T10:11:12.000Z";
    assertTasksEnqueued(
        "lordn-sunrise", new TaskMatcher().payload(expectedPayload).tag("example"));
  }