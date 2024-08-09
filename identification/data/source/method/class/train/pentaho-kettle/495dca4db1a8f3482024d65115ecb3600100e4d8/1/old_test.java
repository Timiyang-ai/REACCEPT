  @Test
  public void testNVL() {
    assertNull( Const.NVL( null, null ) );
    assertEquals( "test", Const.NVL( "test", "test1" ) );
    assertEquals( "test", Const.NVL( "test", null ) );
    assertEquals( "test1", Const.NVL( null, "test1" ) );
  }