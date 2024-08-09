  @Test
  public void isMissingKey() {
    Assert.assertTrue( GlobalMessageUtil.isMissingKey( null ) );
    Assert.assertFalse( GlobalMessageUtil.isMissingKey( "" ) );
    Assert.assertFalse( GlobalMessageUtil.isMissingKey( " " ) );
    Assert.assertTrue( GlobalMessageUtil.isMissingKey( "!foo!" ) );
    Assert.assertTrue( GlobalMessageUtil.isMissingKey( "!foo! " ) );
    Assert.assertTrue( GlobalMessageUtil.isMissingKey( " !foo!" ) );
    Assert.assertFalse( GlobalMessageUtil.isMissingKey( "!foo" ) );
    Assert.assertFalse( GlobalMessageUtil.isMissingKey( "foo!" ) );
    Assert.assertFalse( GlobalMessageUtil.isMissingKey( "foo" ) );
    Assert.assertFalse( GlobalMessageUtil.isMissingKey( "!" ) );
    Assert.assertFalse( GlobalMessageUtil.isMissingKey( " !" ) );
  }