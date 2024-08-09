  @Test
  public void getParentTests() {
    assertEquals(null, new AlluxioURI("/").getParent());
    assertEquals(null,
        new AlluxioURI("alluxio://localhost/").getParent());
    assertEquals(new AlluxioURI("alluxio://localhost/"),
        new AlluxioURI("alluxio://localhost/a").getParent());
    assertEquals(new AlluxioURI("/a"), new AlluxioURI("/a/b/../c").getParent());
    assertEquals(new AlluxioURI("alluxio:/a"),
        new AlluxioURI("alluxio:/a/b/../c").getParent());
    assertEquals(new AlluxioURI("alluxio://localhost:80/a"), new AlluxioURI(
        "alluxio://localhost:80/a/b/../c").getParent());
  }