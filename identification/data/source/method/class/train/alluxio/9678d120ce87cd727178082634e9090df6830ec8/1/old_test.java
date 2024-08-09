  @Test
  public void uniqPath() {
    assertNotEquals(PathUtils.uniqPath(), PathUtils.uniqPath());
  }