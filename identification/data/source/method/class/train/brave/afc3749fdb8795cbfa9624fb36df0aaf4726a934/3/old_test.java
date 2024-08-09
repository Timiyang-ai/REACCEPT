  @Test public void assertRunIsUnloadable_threadLocalWithSystemClassIsUnloadable() {
    assertRunIsUnloadable(PresentThreadLocalWithSystemType.class, getClass().getClassLoader());
  }