  @Test
  void findAnnotated() {
    ResolverUtil<Object> resolverUtil = new ResolverUtil<>();
    resolverUtil.findAnnotated(CacheNamespace.class, this.getClass().getPackage().getName());
    Set<Class<?>> classSets = resolverUtil.getClasses();
    //org.apache.ibatis.io.ResolverUtilTest.TestMapper
    assertEquals(classSets.size(), 1);
    classSets.forEach(c -> assertNotNull(c.getAnnotation(CacheNamespace.class)));
  }