  @Test
  public void convertTest() throws Exception {
    checkConvert("refs/foo/bar", "refs/foo/bar", "refs/foo/bar");
    checkConvert("refs/foo:refs/bar", "refs/foo", "refs/bar");
    checkConvert("refs/heads/*:refs/heads/*", "refs/heads/master", "refs/heads/master");
    checkConvert("refs/heads/*:refs/origin/heads/*", "refs/heads/master",
        "refs/origin/heads/master");
    checkConvert("*/heads/master:*/origin/heads/master", "refs/heads/master",
        "refs/origin/heads/master");
    checkConvert("refs/*/master:refs/origin/*/master", "refs/heads/master",
        "refs/origin/heads/master");
  }