  @Test
  public void getClasspathDeps() {
    assertEquals(
        ImmutableSet.of(a, c, e),
        JavaLibraryClasspathProvider.getClasspathDeps(ImmutableSet.of(a)));

    assertEquals(
        ImmutableSet.of(d, c, e),
        JavaLibraryClasspathProvider.getClasspathDeps(ImmutableSet.of(d, c)));
  }