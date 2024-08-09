  @Test
  public void test_isFrozen() throws Throwable {
    DynamicObject o = new DynamicObject();
    assertThat(o.isFrozen(), is(false));
    o.freeze();
    assertThat(o.isFrozen(), is(true));
  }