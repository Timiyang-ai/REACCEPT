  @Test
  public void isDynamicExpression() {
    assertFalse(TaglibUtil.isDynamicExpression(""));

    assertTrue(TaglibUtil.isDynamicExpression("%{any"));
    assertTrue(TaglibUtil.isDynamicExpression("any%{"));
    assertTrue(TaglibUtil.isDynamicExpression("{ 'one', 'two' }"));
  }