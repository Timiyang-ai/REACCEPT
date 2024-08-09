  @Test
  public void addCoverage() {
    client.addCoverage("some_workspace", "some_cvgStore", "some_coverage");
    Mockito.verify(webTarget).path(
        "rest/workspaces/some_workspace/coveragestores/some_cvgStore/coverages");
  }