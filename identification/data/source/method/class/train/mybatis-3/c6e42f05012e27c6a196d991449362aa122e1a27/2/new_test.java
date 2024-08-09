  @Test
  void find() {
    ResolverUtil<VFS> resolverUtil = new ResolverUtil<>();
    resolverUtil.find(new ResolverUtil.IsA(VFS.class), "org.apache.ibatis.io");
    Set<Class<? extends VFS>> classSets = resolverUtil.getClasses();
    //org.apache.ibatis.io.VFS
    //org.apache.ibatis.io.DefaultVFS
    //org.apache.ibatis.io.JBoss6VFS
    assertEquals(classSets.size(), 3);
    classSets.forEach(c -> assertTrue(VFS.class.isAssignableFrom(c)));
  }