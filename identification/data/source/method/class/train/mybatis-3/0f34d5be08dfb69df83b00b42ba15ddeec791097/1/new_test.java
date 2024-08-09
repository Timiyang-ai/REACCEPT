  @Test
  void findImplementations() {
    ResolverUtil<VFS> resolverUtil = new ResolverUtil<>();
    resolverUtil.findImplementations(VFS.class, "org.apache.ibatis.io");
    Set<Class<? extends VFS>> classSets = resolverUtil.getClasses();
    //org.apache.ibatis.io.VFS
    //org.apache.ibatis.io.DefaultVFS
    //org.apache.ibatis.io.JBoss6VFS
    assertEquals(classSets.size(), 3); //fail if add a new VFS implementation in this package!!!
    classSets.forEach(c -> assertTrue(VFS.class.isAssignableFrom(c)));
  }