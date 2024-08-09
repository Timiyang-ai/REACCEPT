  @Test
  void addIfMatching() {
    ResolverUtil<VFS> resolverUtil = new ResolverUtil<>();
    resolverUtil.addIfMatching(new ResolverUtil.IsA(VFS.class), "org/apache/ibatis/io/DefaultVFS.class");
    resolverUtil.addIfMatching(new ResolverUtil.IsA(VFS.class), "org/apache/ibatis/io/VFS.class");
    Set<Class<? extends VFS>> classSets = resolverUtil.getClasses();
    assertEquals(classSets.size(), 2);
    classSets.forEach(c -> assertTrue(VFS.class.isAssignableFrom(c)));
  }